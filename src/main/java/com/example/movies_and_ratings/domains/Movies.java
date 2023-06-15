package com.example.movies_and_ratings.domains;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "movies")
@Setter
@Getter
public class Movies {
    @Id
    private Integer id;
    @Column(name = "tconst")
    private String tconst;
    @Column(name = "title_type")
    private String title_type;
    @Column(name = "primary_title")
    private String primary_title;
    @Column(name = "runtime_minutes")
    private Integer runtime_Minutes;
    private String genres;
    private Double average_rating;
}
