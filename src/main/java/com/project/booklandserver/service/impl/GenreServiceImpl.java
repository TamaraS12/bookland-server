package com.project.booklandserver.service.impl;

import com.project.booklandserver.dto.GenreDto;
import com.project.booklandserver.mapper.GenreMapper;
import com.project.booklandserver.model.Genre;
import com.project.booklandserver.repository.GenreRepository;
import com.project.booklandserver.service.GenreService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class GenreServiceImpl implements GenreService {
    private final GenreRepository genreRepository;
    private final GenreMapper genreMapper;

    public GenreServiceImpl(GenreRepository genreRepository, GenreMapper genreMapper) {
        this.genreRepository = genreRepository;
        this.genreMapper = genreMapper;
    }

    @Override
    public List<GenreDto> getAll() {
        List<Genre> genres = genreRepository.findAll();

        return genres.stream()
                .map(genre -> genreMapper.toDto(genre))
                .toList();
    }
}
