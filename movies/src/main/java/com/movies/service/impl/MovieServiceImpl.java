package com.movies.service.impl;

import com.movies.dto.MovieDTO;
import com.movies.entity.Movie;
import com.movies.exception.ResourceNotFoundException;
import com.movies.repository.MovieRepository;
import com.movies.service.MovieService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieServiceImpl implements MovieService {

    private static final Logger logger = LoggerFactory.getLogger(MovieServiceImpl.class);

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public List<MovieDTO> findAllMovies() {
        List<Movie> movieList = movieRepository.findAll();
        List<MovieDTO> movieDTOList = movieList.stream().map(movie -> mapper.map(movie, MovieDTO.class)).collect(Collectors.toList());
        logger.info("Total Movies {}", movieDTOList.size());
        return movieDTOList;
    }

    @Override
    public MovieDTO findMovieByImdbId(String imdbId) {
        Movie movie = movieRepository.findMovieByImdbId(imdbId).orElseThrow(() -> new ResourceNotFoundException("Movie not found"));
        MovieDTO movieDTO = mapper.map(movie, MovieDTO.class);
        logger.info("Movie Fetched with Imdb Id {}", movieDTO.getImdbId());
        return movieDTO;
    }
}
