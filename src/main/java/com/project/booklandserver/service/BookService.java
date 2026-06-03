package com.project.booklandserver.service;


import com.project.booklandserver.dto.BookDto;
import com.project.booklandserver.dto.BookSearchRequest;
import com.project.booklandserver.dto.BookSearchResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

public interface BookService {
    BookSearchResponse search(BookSearchRequest request, Pageable pageable);
    BookDto getById(Long id);
    BookDto add(BookDto bookDto, MultipartFile image);
    BookDto update(Long id, BookDto bookDto, MultipartFile image);
    void delete(Long id);
}
