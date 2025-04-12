package com.movitrack.model;

import lombok.Data;
import java.time.LocalDate;

@Data
public class Movie {
    private Long id;
    private String title;
    private String titleId;
    private LocalDate releaseDate;
    private String parentalAdvisory;
    private Double aggregateRating;
    private Integer voteCount;
    private Integer metascore;
    private Integer runtime;
    private String genres;
}