package com.project.booklandserver.dto;

import java.util.List;

public record BookSearchResponse(List<BookDto> content,
                                 int page,
                                 int size,
                                 long totalElements) {
}
