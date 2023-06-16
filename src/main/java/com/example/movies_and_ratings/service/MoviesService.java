package com.example.movies_and_ratings.service;


import com.example.movies_and_ratings.dto.GenreMoviesWithSubtotals;
import com.example.movies_and_ratings.dto.MovieInput;
import com.example.movies_and_ratings.dto.ResponseOfLongestDurationMovies;
import com.example.movies_and_ratings.dto.TopRatedMoviesResponse;

import com.example.movies_and_ratings.repository.MoviesRepository;
import org.springframework.stereotype.Service;

import javax.persistence.Tuple;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class MoviesService {

    private final MoviesRepository moviesRepository;

    public MoviesService(MoviesRepository moviesRepository) {
        this.moviesRepository = moviesRepository;
    }


    public List<ResponseOfLongestDurationMovies> getLongestDurationMovies(){
        List<Tuple> longestDurationMovies = moviesRepository.getLongestDurationMovies();

        List<ResponseOfLongestDurationMovies> responses = new ArrayList<>();


        for (Tuple tuple : longestDurationMovies) {
            String tconst = (String) tuple.get("tconst");
            String primaryTitle = (String) tuple.get("primaryTitle");
            Integer runtimeMinutes = (Integer) tuple.get("runtimeMinutes");
            String genres = (String) tuple.get("genres");

            ResponseOfLongestDurationMovies response = new ResponseOfLongestDurationMovies(tconst, primaryTitle,runtimeMinutes, genres);
            responses.add(response);
        }

        return responses;


    }

    public String saveMovie(final MovieInput movieInput){
        String tconst = movieInput.getTconst();
        String primaryTitle = movieInput.getPrimary_title();
        String titleType = movieInput.getTitle_type();
        Integer runtimeMinutes = movieInput.getRuntime_minutes();
        String genres = movieInput.getGenres();
        moviesRepository.saveMovie(tconst,primaryTitle,titleType,runtimeMinutes,genres);
      return "success";
    }

    public List<TopRatedMoviesResponse> getTopRatedMovies(){
        List<Tuple> tuples = moviesRepository.getTopRatedMovies();
        List<TopRatedMoviesResponse> responses = new ArrayList<>();

        for (Tuple tuple : tuples) {
            String tconst = (String) tuple.get("tconst");
            String primaryTitle = (String) tuple.get("primaryTitle");
            String genres = (String) tuple.get("genres");
            double averageRating = (double) tuple.get("averageRating");

            TopRatedMoviesResponse response = new TopRatedMoviesResponse(tconst, primaryTitle, genres, averageRating);
            responses.add(response);
        }

        return responses;
    }
    public List<GenreMoviesWithSubtotals> getGenreMoviesWithSubtotals(){
        List<Tuple> genreMoviesWithSubtotals = moviesRepository.getGenreMoviesWithSubtotals();
        List<GenreMoviesWithSubtotals> responses = new ArrayList<>();

        for (Tuple tuple : genreMoviesWithSubtotals) {
            String primaryTitle = (String) tuple.get("primaryTitle");
            String genres = (String) tuple.get("genres");
            BigDecimal numVotes = (BigDecimal) tuple.get("numVotes");

           GenreMoviesWithSubtotals genreMoviesWithSubtotals1 =  new GenreMoviesWithSubtotals(genres,primaryTitle,numVotes);
            responses.add(genreMoviesWithSubtotals1);
        }

        return responses;
    }

    public String updateRuntime(){
        moviesRepository.updateRuntimeMinutes();
        return "updated";
    }
}
