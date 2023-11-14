package com.project.MangaService.util;

import com.project.MangaService.model.Manga;
import com.project.MangaService.repository.MangaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final MangaRepository mangaRepository;

    @Override
    public void run(String... args) throws Exception {
        if (mangaRepository.count() < 1) {
            Manga manga = new Manga();
            manga.setName("One_Piece_volume_1");
            manga.setDescription("Number 1 manga");
            manga.setPrice(BigDecimal.valueOf(1000));

            mangaRepository.save(manga);
        }
    }
}
