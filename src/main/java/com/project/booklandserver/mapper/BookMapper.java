package com.project.booklandserver.mapper;

import com.project.booklandserver.dto.BookDto;
import com.project.booklandserver.model.Book;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class BookMapper implements GenericMapper<BookDto, Book> {
    @Override
    public Book toEntity(BookDto dto) {
        return new Book(dto.title(), dto.imageUrl());
    }

    @Override
    public BookDto toDto(Book entity) {
        Set<String> genres = entity.getGenres().stream()
                .map(genre -> genre.getName())
                .collect(Collectors.toSet());

        return new BookDto(entity.getId(),
                entity.getTitle(),
                entity.getImageUrl(),
                entity.getPrice(),
                entity.getDescription(),
                entity.getAuthor().getName(),
                genres);
    }
}
