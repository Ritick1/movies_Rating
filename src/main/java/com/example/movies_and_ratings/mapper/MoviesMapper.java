package com.example.movies_and_ratings.mapper;

import com.example.movies_and_ratings.domains.Movies;
import com.example.movies_and_ratings.dto.MovieInput;
import com.example.movies_and_ratings.dto.ResponseOfLongestDurationMovies;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MoviesMapper {
    public List<ResponseOfLongestDurationMovies> responseOfLongestDurationMoviesList (List<Movies> moviesList){

        List<ResponseOfLongestDurationMovies> list = moviesList.stream().map(movies -> {
            ResponseOfLongestDurationMovies responseOfLongestDurationMovies = new ResponseOfLongestDurationMovies();
            responseOfLongestDurationMovies.setTconst(movies.getTconst());
            responseOfLongestDurationMovies.setPrimaryTitle(movies.getPrimary_title());
            responseOfLongestDurationMovies.setRuntimeMinutes(movies.getRuntime_Minutes());
            responseOfLongestDurationMovies.setGenres(movies.getGenres());
            return responseOfLongestDurationMovies;
        }).toList();
        return list;
    }

    public  Movies inputMappedToDomain(MovieInput movieInput){
        Movies movies = new Movies();
        movies.setTconst(movies.getTconst());
        movies.setTitle_type(movieInput.getTitle_type());
        movies.setPrimary_title(movieInput.getPrimary_title());
        movies.setRuntime_Minutes(movieInput.getRuntime_minutes());
        movies.setGenres(movieInput.getGenres());
        return movies;
    }
}
