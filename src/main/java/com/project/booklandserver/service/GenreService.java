package com.project.booklandserver.service;

import com.project.booklandserver.dto.GenreDto;

import java.util.List;

public interface GenreService {
    List<GenreDto> getAll();
}
