package com.velb.yasptestex.model.parameter;

import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class ColumnParameter {

    private Map<String, String> userValTablaValMap;

    public ColumnParameter() {
        userValTablaValMap = Map.of(
                "book", "TITLE",
                "author", "AUTHORS",
                "numPages", "NUM_PAGES_MOD",
                "publicationDate", "PUBLICATION_DATE_MOD",
                "rating", "RATING_MOD",
                "numberOfVoters", "NUMBER_OF_VOTERS");
    }


    public String getValueByKey(String userVal) {
        return userValTablaValMap.get(userVal);
    }

    public boolean isThisKeyInMap(String userVal) {
        return userValTablaValMap.containsKey(userVal);
    }

    public void addNewParameter(String userVal, String tableVal) {
        userValTablaValMap.put(userVal, tableVal);
    }

    public void removeParameter(String userVal) {
        if (userVal != null) {
            userValTablaValMap.remove(userVal);
        }
    }

}

