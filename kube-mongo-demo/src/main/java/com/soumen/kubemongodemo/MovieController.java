package com.soumen.kubemongodemo;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Soumen Karmakar
 * @Date 13/04/2021
 */
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class MovieController {
    private final MovieRepository movieRepository;

    @GetMapping("/movies")
    public List<Movie> getMovies() {
        return this.movieRepository.findAll();
    }

    @PostMapping("/movies")
    public Movie addMovie(Movie movie) {
        return this.movieRepository.save(movie);
    }
}
