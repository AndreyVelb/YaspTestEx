package com.velb.yasptestex.service;

import com.velb.yasptestex.dao.BookDao;
import com.velb.yasptestex.model.converter.ResultSetToBookDtoConverter;
import com.velb.yasptestex.model.converter.StringToListOfStringConverter;
import com.velb.yasptestex.model.dto.BookResponseDto;
import com.velb.yasptestex.model.parameter.ColumnParameter;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookDao bookDao;

    private final ResultSetToBookDtoConverter resultSetToBookDtoConverter;

    private final ColumnParameter columnParameter;


    private final int LIMIT_10 = 10;


    public List<BookResponseDto> getTopTenBooksByParameters(String year, String column, String sort) {
        ResultSet rs;
        if (!year.equals("null")) {
            rs = bookDao.getBooksByYearSortByColumnWithLimit(
                    createStartOfYearLiteral(year),
                    createEndOfYearLiteral(year),
                    columnParameter.getValueByKey(column),
                    sort,
                    LIMIT_10);
        } else {
            rs = bookDao.getBooksSortByColumnWithLimit(
                    columnParameter.getValueByKey(column),
                    sort,
                    LIMIT_10);
        }

        return convertResultSetToListDto(rs);
    }

    @SneakyThrows
    private List<BookResponseDto> convertResultSetToListDto(ResultSet rs) {
        List<BookResponseDto> bookResponseDtoList = new ArrayList<>();
        while (rs.next()) {
            bookResponseDtoList.add(resultSetToBookDtoConverter.convert(rs));
        }
        return bookResponseDtoList;
    }

    private String createStartOfYearLiteral(String year) {
        return "'" + year + "-01-01'";
    }

    private String createEndOfYearLiteral(String year) {
        return "'" + year + "-12-31'";
    }

}
