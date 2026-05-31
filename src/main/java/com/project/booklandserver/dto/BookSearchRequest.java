package com.project.booklandserver.dto;

public record BookSearchRequest(String title,
                                Long authorId,
                                Long genreId,
                                String sort) {
}
