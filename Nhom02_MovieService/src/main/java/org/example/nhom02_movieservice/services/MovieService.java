package org.example.nhom02_movieservice.services;

import org.example.nhom02_movieservice.dto.MovieRequest;
import org.example.nhom02_movieservice.models.Movie;

import java.util.List;

public interface MovieService {
    List<Movie> getAllMovies();
    Movie addMovie(MovieRequest movieRequest);
}
