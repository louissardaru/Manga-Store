package com.project.MangaService.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MangaResponse {
	private String id;
	private String name;
	private String description;
	private BigDecimal price;
}
