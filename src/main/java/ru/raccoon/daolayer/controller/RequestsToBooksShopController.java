package ru.raccoon.daolayer.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.raccoon.daolayer.repository.BooksShopRepository;

import java.util.List;
import java.util.Locale;

@RestController
public class RequestsToBooksShopController {

    private final BooksShopRepository repository;

    public RequestsToBooksShopController(BooksShopRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/products/fetch-product")
    public List<String> fetchProduct(@RequestParam("name") String name) {
        return repository.getProductName(name.toLowerCase(Locale.ROOT));
    }


}
