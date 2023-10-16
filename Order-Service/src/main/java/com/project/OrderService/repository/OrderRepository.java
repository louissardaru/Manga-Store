package com.project.OrderService.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.OrderService.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{

}
