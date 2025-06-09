package com.movitrack.model;

import lombok.Data;
import java.time.LocalDate;

@Data
public class Item {
    private Integer id;
    private String title;
    private String titleId;
    private String releaseDate;
    private String parentalAdvisory;
    private Double aggregateRating;
    private Integer voteCount;
    private String metascore;
    private Integer runtime;
    private String genres;
}