package ru.raccoon.daolayer.repository;

import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class BooksShopRepository {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final String requestString;

    public BooksShopRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        requestString = read("query.sql");
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    private static String read(String fileName) {
        try (InputStream is = new ClassPathResource(fileName).getInputStream();
             BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is))) {
            return bufferedReader.lines().collect(Collectors.joining("\n"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<String> getProductName(String name) {
        Map<String, Object> params = new HashMap<>();
        params.put("name", name);
        return namedParameterJdbcTemplate.queryForList(requestString,
                params,
                String.class
        );
    }
}
