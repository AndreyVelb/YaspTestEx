package com.velb.yasptestex.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@Repository
@RequiredArgsConstructor
public class BookDao {

    private final Connection connection;


    public ResultSet getBooksByYearSortByColumnWithLimit(String startOfYear, String endOfYear, String column, String sort, int limit) {
        try {

            String SELECT_BOOKS_BY_YEAR_SORT_BY_COLUMN = String.format("SELECT * FROM BOOKS " +
                    "WHERE PUBLICATION_DATE_MOD >= %s AND PUBLICATION_DATE_MOD <= %s " +
                    "ORDER BY %s %s NULLS LAST LIMIT %s", startOfYear, endOfYear, column, sort, limit);
            Statement statement = connection.createStatement();

            return statement.executeQuery(SELECT_BOOKS_BY_YEAR_SORT_BY_COLUMN);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public ResultSet getBooksSortByColumnWithLimit(String column, String sort, int limit) {
        try {
            String SELECT_BOOKS_SORT_BY_COLUMN = String.format("SELECT * FROM BOOKS ORDER BY %s %s NULLS LAST LIMIT %s",
                    column,
                    sort,
                    limit);

            Statement statement = connection.createStatement();

            return statement.executeQuery(SELECT_BOOKS_SORT_BY_COLUMN);
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }
}