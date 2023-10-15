package com.project.MangaService.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.project.MangaService.model.Manga;

public interface MangaRepository extends MongoRepository<Manga, String> {

}
