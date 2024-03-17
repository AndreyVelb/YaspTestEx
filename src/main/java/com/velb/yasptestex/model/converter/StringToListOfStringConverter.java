package com.velb.yasptestex.model.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StringToListOfStringConverter implements Converter<String, List<String>> {


    @Override
    public List<String> convert(String source) {
        if (source.length() >= 2) {
            String withoutBracket = source.substring(2, source.length() - 2);
            return List.of(withoutBracket.split("(', ')"));
        } else return List.of();
    }
}
