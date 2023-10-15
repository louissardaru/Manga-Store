package com.project.MangaService.model;

import java.math.BigDecimal;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(value="manga")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Manga {
	@Id
	private String id;
	private String name;
	private String description;
	private BigDecimal price;
}
