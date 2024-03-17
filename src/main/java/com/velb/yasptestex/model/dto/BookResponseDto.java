package com.velb.yasptestex.model.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookResponseDto {

    private Long id;

    private String book;

    private String series;

    private String releaseNumber;

    private String author;

    private String description;

    private Integer numPages;

    private String format;

    private List<String> genres;

    private java.sql.Date publicationDate;

    private Float rating;

    private Integer numberOfVoters;

}
