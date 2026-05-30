package com.project.booklandserver.mapper;

import com.project.booklandserver.dto.AuthorDto;
import com.project.booklandserver.model.Author;
import org.springframework.stereotype.Component;

@Component
public class AuthorMapper implements GenericMapper<AuthorDto, Author> {
    @Override
    public Author toEntity(AuthorDto dto) {
        return new Author(dto.name());
    }

    @Override
    public AuthorDto toDto(Author entity) {
        return new AuthorDto(entity.getId(), entity.getName());
    }
}
