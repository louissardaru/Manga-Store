package com.project.OrderService.service;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import com.project.OrderService.dto.InventoryResponse;
import com.project.OrderService.dto.OrderLineItemsDto;
import com.project.OrderService.dto.OrderRequest;
import com.project.OrderService.event.OrderPlacedEvent;
import com.project.OrderService.model.Order;
import com.project.OrderService.model.OrderLineItems;
import com.project.OrderService.repository.OrderRepository;

import io.micrometer.observation.Observation;
import io.micrometer.observation.ObservationRegistry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class OrderService {
	
	private final OrderRepository orderRepository;
	private final WebClient.Builder webClientBuilder;
	private final ObservationRegistry observationRegistry;
	private final ApplicationEventPublisher applicationEventPublisher;
	
	public String placeOrder(OrderRequest orderRequest) {
		Order order = new Order();
		order.setOrderNumber(UUID.randomUUID().toString());
		
		List<OrderLineItems> orderLineItems = orderRequest.getOrderLineItemsDtoList()
				.stream()
				.map(this::mapToDto)
				.toList();
		
		order.setOrderLineItemsList(orderLineItems);
		
		List<String> skuCodes = order.getOrderLineItemsList()
				.stream()
				.map(OrderLineItems::getSkuCode)
				.toList();
		
		//call InventoryService to see if the manga volume is in stock
		Observation inventoryServiceObservation = Observation.createNotStarted("inventory-service-lookup",
                this.observationRegistry);
        inventoryServiceObservation.lowCardinalityKeyValue("call", "inventory-service");
        return inventoryServiceObservation.observe(() -> {
		InventoryResponse[] inventoryResponseList = webClientBuilder.build().get()
		         .uri("http://inventory-service/api/inventory", 
		        		 uriBuilder -> uriBuilder.queryParam("skuCode", skuCodes).build())
		         .retrieve()
		         .bodyToMono(InventoryResponse[].class)
		         .block();
		
		boolean checkTheStock = Arrays.stream(inventoryResponseList).allMatch(InventoryResponse::isInStock);
		
		if(checkTheStock) {
			orderRepository.save(order);
			applicationEventPublisher.publishEvent(new OrderPlacedEvent(this, order.getOrderNumber()));
			return "The order was placed successfully.";
		}else {
			throw new IllegalArgumentException("Sorry! Manga volume is not in stock.");
		}
        });
	}
	
	private OrderLineItems mapToDto(OrderLineItemsDto orderLineItemsDto) {
		OrderLineItems orderLineItems = new OrderLineItems();
		orderLineItems.setPrice(orderLineItemsDto.getPrice());
		orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
		orderLineItems.setSkuCode(orderLineItemsDto.getSkuCode());
		return orderLineItems;
	}
}
