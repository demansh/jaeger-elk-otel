package com.github.demansh.api;

import com.github.demansh.entities.Shop;
import com.github.demansh.repository.ShopRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController
public class ShopsApi {
    private final ShopRepository shopRepository;

    public ShopsApi(ShopRepository shopRepository) {
        this.shopRepository = shopRepository;
    }

    @GetMapping(path = "books/{id}/shops")
    public Collection<Shop> getShops(@PathVariable long id) {
        List<Shop> result = new ArrayList<>();
        shopRepository.findAll().forEach(shop -> {
            if (shop.getBooks().stream().anyMatch(book -> book.getId() == id)) {
                result.add(shop);
            }
        });
        return result;
    }
}
