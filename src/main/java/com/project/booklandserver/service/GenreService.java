package com.project.booklandserver.service;

import com.project.booklandserver.dto.GenreDto;

import java.util.List;

public interface GenreService {
    List<GenreDto> getAll();
    GenreDto add(GenreDto genreDto);
    GenreDto update(Long id, GenreDto genreDto);
    void delete(Long id);
}
