package org.example.nhom02_movieservice.services.impl;

import org.example.nhom02_movieservice.dto.MovieRequest;
import org.example.nhom02_movieservice.models.Movie;
import org.example.nhom02_movieservice.repositories.MovieRepository;
import org.example.nhom02_movieservice.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {
    @Autowired
    private MovieRepository movieRepository;

    @Override
    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    @Override
    public Movie addMovie(MovieRequest movieRequest) {
        Movie movie = Movie.builder()
                .title(movieRequest.getTitle())
                .genre(movieRequest.getGenre())
                .duration(movieRequest.getDuration())
                .price(movieRequest.getPrice())
                .build();

        return movieRepository.save(movie);
    }
}
