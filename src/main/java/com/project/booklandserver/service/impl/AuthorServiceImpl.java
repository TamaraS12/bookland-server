package com.project.booklandserver.service.impl;

import com.project.booklandserver.dto.AuthorDto;
import com.project.booklandserver.mapper.AuthorMapper;
import com.project.booklandserver.model.Author;
import com.project.booklandserver.repository.AuthorRepository;
import com.project.booklandserver.service.AuthorService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;

    public AuthorServiceImpl(AuthorRepository authorRepository, AuthorMapper authorMapper) {
        this.authorRepository = authorRepository;
        this.authorMapper = authorMapper;
    }

    @Override
    public List<AuthorDto> getAll() {
        List<Author> authors = authorRepository.findAll();

        return authors.stream()
                .map(author -> authorMapper.toDto(author))
                .toList();
    }
}
