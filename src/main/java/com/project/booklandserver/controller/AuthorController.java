package com.project.booklandserver.controller;

import com.project.booklandserver.dto.AuthorDto;
import com.project.booklandserver.service.AuthorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {
    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public List<AuthorDto> getAll() {
        return authorService.getAll();
    }

    @PostMapping
    public AuthorDto add(@RequestBody AuthorDto authorDto) {
        return authorService.add(authorDto);
    }

    @PutMapping("/{id}")
    public AuthorDto update(@PathVariable Long id,
                            @RequestBody AuthorDto authorDto) {
        return authorService.update(id, authorDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        authorService.delete(id);
    }
}
