package com.github.demansh.api;

import com.github.demansh.entities.Book;
import com.github.demansh.services.BooksService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class BooksApi {

    private final BooksService booksService;

    public BooksApi(BooksService booksService) {
        this.booksService = booksService;
    }

    @GetMapping(path = "books/{id}")
    public Book getBook(@PathVariable long id) {
        return booksService.getBook(id);
    }

    @GetMapping(path = "books")
    public Collection<Book> getAll() {
        return booksService.getAll();
    }
}
