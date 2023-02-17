package com.movies.service;

import com.movies.dto.MovieDTO;

import java.util.List;

public interface MovieService {

    List<MovieDTO> findAllMovies();

    MovieDTO findMovieByImdbId(String imdbId);
}
