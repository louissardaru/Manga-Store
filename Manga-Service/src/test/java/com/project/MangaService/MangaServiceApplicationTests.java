package com.project.MangaService;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import com.project.MangaService.dto.MangaRequest;
import com.project.MangaService.repository.MangaRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;


@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
class MangaServiceApplicationTests {

	@Container
	static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:7.0.2");
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Autowired
	private MangaRepository mangaRepository;
	
	@DynamicPropertySource
	static void setProperties(DynamicPropertyRegistry dymDynamicPropertyRegistry) {
		dymDynamicPropertyRegistry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
	}
	
	@Test
	void shouldCreateMangaProduct() throws Exception  {
		MangaRequest mangaRequest = getMangaRequest();
		String mangaRequestString = objectMapper.writeValueAsString(mangaRequest);
		
		mockMvc.perform(MockMvcRequestBuilders.post("/api/manga")
				.contentType(MediaType.APPLICATION_JSON)
				.content(mangaRequestString))
				.andExpect(status().isCreated());
		Assertions.assertEquals(1, mangaRepository.findAll().size());
	}

	private MangaRequest getMangaRequest() {
		return MangaRequest.builder()
		       .name("One Piece vol. 1")
		       .description("Best Manga")
		       .price(BigDecimal.valueOf(10))
		       .build();
	}

}
