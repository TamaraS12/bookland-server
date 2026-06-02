package com.project.booklandserver.controller;

import com.project.booklandserver.dto.GenreDto;
import com.project.booklandserver.model.Genre;
import com.project.booklandserver.service.GenreService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/genres")
public class GenreController {
    private final GenreService genreService;

    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    @GetMapping
    public List<GenreDto> getAll() {
        return genreService.getAll();
    }

    @PostMapping
    public GenreDto add(@RequestBody GenreDto genreDto) {
        return genreService.add(genreDto);
    }

    @PutMapping("/{id}")
    public GenreDto update(@PathVariable Long id, @RequestBody GenreDto genreDto) {
        return genreService.update(id, genreDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        genreService.delete(id);
    }
}
