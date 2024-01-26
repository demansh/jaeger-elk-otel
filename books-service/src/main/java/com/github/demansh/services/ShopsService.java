package com.github.demansh.services;

import com.github.demansh.entities.Book;
import com.github.demansh.entities.Shop;
import com.github.demansh.repository.BooksRepository;
import com.github.demansh.repository.ShopsRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class ShopsService {
    private final ShopsRepository shopsRepository;

    public ShopsService(ShopsRepository shopsRepository) {
        this.shopsRepository = shopsRepository;
    }

    public Collection<Shop> findShopsForBook(long id) {
        return shopsRepository.findShopsForBook(id);
    }
}
