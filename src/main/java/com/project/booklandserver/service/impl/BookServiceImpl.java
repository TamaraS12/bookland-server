package com.project.booklandserver.service.impl;

import com.project.booklandserver.dto.BookDto;
import com.project.booklandserver.dto.BookSearchRequest;
import com.project.booklandserver.dto.BookSearchResponse;
import com.project.booklandserver.mapper.BookMapper;
import com.project.booklandserver.model.Author;
import com.project.booklandserver.model.Book;
import com.project.booklandserver.model.Genre;
import com.project.booklandserver.repository.AuthorRepository;
import com.project.booklandserver.repository.BookRepository;
import com.project.booklandserver.repository.GenreRepository;
import com.project.booklandserver.service.BookService;
import com.project.booklandserver.service.FileStorageService;
import com.project.booklandserver.specification.BookSpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;
    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;
    private final FileStorageService fileStorageService;

    public BookServiceImpl(BookRepository bookRepository,
                           BookMapper bookMapper,
                           AuthorRepository authorRepository,
                           GenreRepository genreRepository,
                           FileStorageService fileStorageService) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
        this.authorRepository = authorRepository;
        this.genreRepository = genreRepository;
        this.fileStorageService = fileStorageService;
    }

    @Override
    public BookSearchResponse search(BookSearchRequest request, Pageable pageable) {
        Page<BookDto> page = bookRepository.findAll(BookSpecification.search(request), pageable)
                .map(book -> bookMapper.toDto(book));

        return new BookSearchResponse(
                page.getContent(),
                page.getNumber(),
                page.getSize(),
                page.getTotalElements()
        );
    }

    @Override
    public BookDto getById(Long id) {
        Optional<Book> book = bookRepository.findById(id);

        if (book.isPresent()) {
            return bookMapper.toDto(book.get());
        }
        throw new RuntimeException("Book not found");
    }

    @Override
    @Transactional
    public BookDto add(BookDto bookDto, MultipartFile image) {
        Book book = bookMapper.toEntity(bookDto);
        Author author = authorRepository.findById(bookDto.authorId())
                .orElseThrow(() -> new RuntimeException("Author not found"));
        List<Genre> genres = genreRepository.findAllById(bookDto.genreIds());

        book.setAuthor(author);
        book.setGenres(genres);

        if (image != null && !image.isEmpty()) {

            String fileName =
                    fileStorageService.save(image);

            book.setImageName(fileName);
        }

        Book savedBook = bookRepository.save(book);

        return bookMapper.toDto(savedBook);
    }

    @Override
    @Transactional
    public BookDto update(Long id, BookDto bookDto, MultipartFile image) {

        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        Author author = authorRepository.findById(bookDto.authorId())
                .orElseThrow(() -> new RuntimeException("Author not found"));

        List<Genre> genres = genreRepository.findAllById(bookDto.genreIds());

        book.setTitle(bookDto.title());
        book.setPrice(bookDto.price());
        book.setDescription(bookDto.description());

        book.setAuthor(author);
        book.setGenres(genres);

        if (image != null && !image.isEmpty()) {

            fileStorageService.delete(book.getImageName());

            String fileName =
                    fileStorageService.save(image);

            book.setImageName(fileName);
        }


        Book updatedBook = bookRepository.save(book);

        return bookMapper.toDto(updatedBook);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        fileStorageService.delete(book.getImageName());
        bookRepository.delete(book);
    }
}




