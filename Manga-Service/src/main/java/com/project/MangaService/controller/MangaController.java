package com.project.MangaService.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import org.springframework.http.HttpStatus;

import com.project.MangaService.dto.MangaRequest;
import com.project.MangaService.dto.MangaResponse;
import com.project.MangaService.service.MangaService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/manga")
@RequiredArgsConstructor
public class MangaController {
	
	private final MangaService mangaService;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void createManga(@RequestBody MangaRequest mangaRequest) {
		mangaService.createManga(mangaRequest);
	}
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<MangaResponse> getAllManga(){
		return mangaService.getAllManga();
	}
}
