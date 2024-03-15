package com.github.demansh.api;

import com.github.demansh.entities.Book;
import com.github.demansh.repository.BookRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController
public class BooksApi {

    private final BookRepository bookRepository;

    public BooksApi(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping(path = "books/{id}")
    public Book getBook(@PathVariable long id) {
        return bookRepository.findById(id).orElseThrow();
    }

    @GetMapping(path = "books")
    public Collection<Book> getAll() {
        List<Book> result = new ArrayList<>();
        bookRepository.findAll().iterator().forEachRemaining(result::add);
        return result;
    }
}
