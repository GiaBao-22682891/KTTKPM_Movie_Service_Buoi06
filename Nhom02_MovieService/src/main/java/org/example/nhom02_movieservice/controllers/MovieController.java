package org.example.nhom02_movieservice.controllers;

import org.example.nhom02_movieservice.dto.MovieRequest;
import org.example.nhom02_movieservice.models.Movie;
import org.example.nhom02_movieservice.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/movie")
@CrossOrigin (origins = "*")
public class MovieController {
    @Autowired
    private MovieService movieService;

    @GetMapping
    public ResponseEntity<List<Movie>> getAll() {
        return ResponseEntity.ok(movieService.getAllMovies());
    }

    @PostMapping
    public ResponseEntity<Movie> create(@RequestBody MovieRequest movieRequest) {
        Movie savedMovie = movieService.addMovie(movieRequest);
        return ResponseEntity.ok(savedMovie);
    }
}
