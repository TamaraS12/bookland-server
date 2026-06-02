package com.project.booklandserver.service.impl;

import com.project.booklandserver.dto.GenreDto;
import com.project.booklandserver.mapper.GenreMapper;
import com.project.booklandserver.model.Genre;
import com.project.booklandserver.repository.GenreRepository;
import com.project.booklandserver.service.GenreService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Override
    @Transactional
    public GenreDto add(GenreDto genreDto) {
        Genre genre = genreMapper.toEntity(genreDto);
        genre = genreRepository.save(genre);
        return genreMapper.toDto(genre);
    }

    @Override
    @Transactional
    public GenreDto update(Long id, GenreDto genreDto) {
        Genre genre = genreRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Genre not found"));
        genre.setName(genreDto.name());
        genre = genreRepository.save(genre);
        return genreMapper.toDto(genre);
    }

    @Override
    public void delete(Long id) {
        Genre genre = genreRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Genre not found"));
        genreRepository.delete(genre);
    }
}
