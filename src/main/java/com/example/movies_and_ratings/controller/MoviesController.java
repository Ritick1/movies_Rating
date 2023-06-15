package com.example.movies_and_ratings.controller;

import com.example.movies_and_ratings.domains.Movies;
import com.example.movies_and_ratings.dto.GenreMoviesWithSubtotals;
import com.example.movies_and_ratings.dto.MovieInput;
import com.example.movies_and_ratings.dto.ResponseOfLongestDurationMovies;
import com.example.movies_and_ratings.dto.TopRatedMoviesResponse;
import com.example.movies_and_ratings.service.MoviesService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MoviesController {

   private final MoviesService moviesService;

    public MoviesController(MoviesService moviesService) {
        this.moviesService = moviesService;
    }

    @GetMapping("api/v1/longest-duration-movies")
    public List<ResponseOfLongestDurationMovies> getLongestDurationMovies(){
       return moviesService.getLongestDurationMovies();
    }

    @PostMapping("api/v1/new-movie")
    public String saveMovie(@RequestBody final MovieInput movieInput){
        return moviesService.saveMovie(movieInput);
    }

    @GetMapping("api/v1/top-rated-movies")
    public List<TopRatedMoviesResponse> getTopRatedMovies(){
        return moviesService.getTopRatedMovies();
    }

    @GetMapping("api/v1/genre-movies-with-subtotals")
    public List<GenreMoviesWithSubtotals> getGenreMoviesWithSubtotals(){
        return moviesService.getGenreMoviesWithSubtotals();
    }

    @GetMapping("api/v1/update-runtime-minutes")
    public String updateRuntime(){
        return moviesService.updateRuntime();
    }

}

