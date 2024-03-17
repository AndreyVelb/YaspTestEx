package com.velb.yasptestex.model.converter;

import com.velb.yasptestex.model.dto.BookResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
@RequiredArgsConstructor
public class ResultSetToBookDtoConverter implements Converter<ResultSet, BookResponseDto> {

    private final StringToListOfStringConverter stringToListOfStringConverter;


    @Override
    public BookResponseDto convert(ResultSet rs) {
        try {
            return BookResponseDto.builder()
                    .id(Long.valueOf(rs.getString("COLUMN0")))
                    .book(rs.getString("TITLE"))
                    .series(rs.getString("SERIES_TITLE"))
                    .releaseNumber(rs.getString("SERIES_RELEASE_NUMBER"))
                    .author(rs.getString("AUTHORS"))
                    .description(rs.getString("DESCRIPTION"))
                    .numPages(rs.getInt("NUM_PAGES_MOD"))
                    .format(rs.getString("FORMAT"))
                    .genres(stringToListOfStringConverter.convert(rs.getString("GENRES")))
                    .publicationDate(rs.getDate("PUBLICATION_DATE_MOD"))
                    .rating(rs.getFloat("RATING_MOD"))
                    .numberOfVoters(rs.getInt("NUMBER_OF_VOTERS"))
                    .build();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
