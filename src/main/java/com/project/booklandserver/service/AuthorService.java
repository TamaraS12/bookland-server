package com.project.booklandserver.service;

import com.project.booklandserver.dto.AuthorDto;

import java.util.List;

public interface AuthorService {
    List<AuthorDto> getAll();
    AuthorDto add(AuthorDto authorDto);
    AuthorDto update(Long id, AuthorDto authorDto);
    void delete(Long id);
}
