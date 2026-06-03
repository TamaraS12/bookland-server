package com.project.booklandserver.controller;

import com.project.booklandserver.dto.BookDto;
import com.project.booklandserver.dto.BookSearchRequest;
import com.project.booklandserver.dto.BookSearchResponse;
import com.project.booklandserver.service.BookService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/books")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/search")
    public BookSearchResponse search(BookSearchRequest request,
                                     Pageable pageable) {
        return bookService.search(request, pageable);
    }

    @GetMapping("/{id}")
    public BookDto getById(@PathVariable Long id) {
        return bookService.getById(id);
    }

    @PostMapping
    public BookDto add(@RequestPart("book") BookDto bookDto,
                       @RequestPart("image") MultipartFile image) {
        return bookService.add(bookDto, image);
    }

    @PutMapping("/{id}")
    public BookDto update(@PathVariable Long id,
                          @RequestPart("book") BookDto bookDto,
                          @RequestPart(name = "image", required = false) MultipartFile image) {
        return bookService.update(id, bookDto, image);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        bookService.delete(id);
    }
}



