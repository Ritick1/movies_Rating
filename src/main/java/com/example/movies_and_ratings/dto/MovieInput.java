package com.example.movies_and_ratings.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovieInput {
    private String tconst;
    private String title_type;
    private String primary_title;
    private Integer runtime_minutes;
    private String genres;
}
