package com.github.demansh.api;

import com.github.demansh.entities.Book;
import com.github.demansh.entities.Shop;
import com.github.demansh.services.BooksService;
import com.github.demansh.services.ShopsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class ShopsApi {
    private final ShopsService shopsService;

    public ShopsApi(ShopsService shopsService) {
        this.shopsService = shopsService;
    }

    @GetMapping(path = "books/{id}/shops")
    public Collection<Shop> getShops(@PathVariable long id) {
        return shopsService.findShopsForBook(id);
    }
}
