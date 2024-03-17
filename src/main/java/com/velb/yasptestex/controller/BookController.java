package com.velb.yasptestex.controller;

import com.velb.yasptestex.model.dto.BookResponseDto;

import com.velb.yasptestex.service.BookService;
import com.velb.yasptestex.validation.Column;
import com.velb.yasptestex.validation.Sort;
import com.velb.yasptestex.validation.Year;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
@RequestMapping("/api")
@RequiredArgsConstructor
@Validated
public class BookController {

    private final BookService bookService;


    @GetMapping(value = "/top10", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<BookResponseDto> getBooksTopTen(@RequestParam(name = "year", required = false) @Year String year,
                                                @RequestParam(name = "column") @Column String column,
                                                @RequestParam(name = "sort") @Sort String sort) {

        return bookService.getTopTenBooksByParameters(String.valueOf(year), column, sort);
    }

}
