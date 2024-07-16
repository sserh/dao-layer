package ru.raccoon.daolayer.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@org.springframework.stereotype.Repository
public class Repository {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private String requestString;

    public Repository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        requestString = read("query.sql");
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    private static String read(String scriptFileName) {
        try (InputStream is = new ClassPathResource(scriptFileName).getInputStream();
             BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is))) {
            return bufferedReader.lines().collect(Collectors.joining("\n"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<String> getProductName(String name) {
        Map<String, Object> params = new HashMap<>();
        params.put("name", name);
        List<String> books = namedParameterJdbcTemplate.query(requestString + ":name",
                params,
                (rs, rowNum) -> {
                    String bookName = rs.getString("product_name");
                    return bookName;
                });
        return books;
    }
}
