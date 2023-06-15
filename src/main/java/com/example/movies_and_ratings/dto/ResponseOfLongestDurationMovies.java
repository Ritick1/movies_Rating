package com.example.movies_and_ratings.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseOfLongestDurationMovies {
    private  String tconst;
    private String primaryTitle;
    private Integer runtimeMinutes;
    private String genres;
}
