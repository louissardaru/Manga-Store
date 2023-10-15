package com.project.MangaService.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.MangaService.dto.MangaRequest;
import com.project.MangaService.dto.MangaResponse;
import com.project.MangaService.model.Manga;
import com.project.MangaService.repository.MangaRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class MangaService {
	private final MangaRepository mangaRepository;
	
	public void createManga(MangaRequest mangaRequest) {
		Manga manga = Manga.builder()
				.name(mangaRequest.getName())
				.description(mangaRequest.getDescription())
				.price(mangaRequest.getPrice())
				.build();
		
		mangaRepository.save(manga);
		log.info("Manga {} is saved.", manga.getId());
	}

	public List<MangaResponse> getAllManga() {
		List<Manga> allManga = mangaRepository.findAll();
		
		return allManga.stream().map(this::mapToMangaResponse).toList();	
		}
	
	private MangaResponse mapToMangaResponse(Manga manga) {
		return MangaResponse.builder()
				.id(manga.getId())
				.name(manga.getName())
				.description(manga.getDescription())
				.price(manga.getPrice())
				.build();
		}
}
