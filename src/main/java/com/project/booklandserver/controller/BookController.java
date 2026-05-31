package com.project.booklandserver.controller;

import com.project.booklandserver.dto.BookDto;
import com.project.booklandserver.dto.BookSearchRequest;
import com.project.booklandserver.service.BookService;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/search")
    public List<BookDto> search(BookSearchRequest request,
                                Pageable pageable) {
        return bookService.search(request, pageable);
    }

}
