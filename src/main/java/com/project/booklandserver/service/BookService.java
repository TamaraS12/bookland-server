package com.project.booklandserver.service;


import com.project.booklandserver.dto.BookDto;
import com.project.booklandserver.dto.BookSearchRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface BookService {
    List<BookDto> search(BookSearchRequest request, Pageable pageable);
    BookDto getById(Long id);
    BookDto add(BookDto bookDto, MultipartFile image);
    BookDto update(Long id, BookDto bookDto, MultipartFile image);
    void delete(Long id);
}
