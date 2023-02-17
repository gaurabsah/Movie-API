package com.movies.controller;

import com.movies.dto.MovieDTO;
import com.movies.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/movies")
public class MovieController {

    @Autowired
    private MovieService service;

    @GetMapping
    public ResponseEntity<List<MovieDTO>> getMovies() {
        return new ResponseEntity<List<MovieDTO>>(service.findAllMovies(), HttpStatus.OK);
    }

    @GetMapping("/{imdbId}")
    public ResponseEntity<MovieDTO> getSingleMovie(@PathVariable String imdbId) {
        return new ResponseEntity<MovieDTO>(service.findMovieByImdbId(imdbId), HttpStatus.OK);
    }
}
