package com.project.booklandserver.service.impl;

import com.project.booklandserver.dto.BookDto;
import com.project.booklandserver.dto.BookSearchRequest;
import com.project.booklandserver.mapper.BookMapper;
import com.project.booklandserver.repository.BookRepository;
import com.project.booklandserver.service.BookService;
import com.project.booklandserver.specification.BookSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    public BookServiceImpl(BookRepository bookRepository, BookMapper bookMapper) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
    }

    @Override
    public List<BookDto> search(BookSearchRequest request, Pageable pageable) {
        Page<BookDto> page = bookRepository.findAll(BookSpecification.search(request), pageable)
                .map(book -> bookMapper.toDto(book));

        return page.getContent();
    }
}
