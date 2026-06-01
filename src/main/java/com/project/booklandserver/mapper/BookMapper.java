package com.project.booklandserver.mapper;

import com.project.booklandserver.dto.BookDto;
import com.project.booklandserver.model.Book;
import com.project.booklandserver.model.Genre;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class BookMapper implements GenericMapper<BookDto, Book> {
    @Override
    public Book toEntity(BookDto dto) {
        return new Book(dto.title(), dto.imageUrl(), dto.description(), dto.price());
    }

    @Override
    public BookDto toDto(Book entity) {
        List<Genre> genres = entity.getGenres();

        Set<Long> genreIds = genres.stream()
                .map(genre -> genre.getId())
                .collect(Collectors.toSet());

        Set<String> genreNames = genres.stream()
                .map(genre -> genre.getName())
                .collect(Collectors.toSet());

        return new BookDto(entity.getId(),
                entity.getTitle(),
                entity.getImageUrl(),
                entity.getPrice(),
                entity.getDescription(),
                entity.getAuthor().getId(),
                entity.getAuthor().getName(),
                genreIds,
                genreNames);
    }
}
