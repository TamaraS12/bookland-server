package com.project.booklandserver.service;


import com.project.booklandserver.dto.BookDto;
import com.project.booklandserver.dto.BookSearchRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BookService {
    List<BookDto> search(BookSearchRequest request, Pageable pageable);
}
