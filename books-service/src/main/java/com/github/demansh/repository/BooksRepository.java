package com.github.demansh.repository;

import com.github.demansh.entities.Book;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Map;

@Component
public class BooksRepository {

    private Map<Long, Book> books = Map.of(
            100001L, new Book(100001, "Clean code"),
            100002L, new Book(100002, "Effective Java"),
            100003L, new Book(100003, "Learn Java the Easy Way"),
            100004L, new Book(100004, "Java For Dummies"),
            100005L, new Book(100005, "Java Concurrency In Practice")
    );

    public Book getBook(long id) {
        return books.get(id);
    }

    public Collection<Book> getAll() {
        return books.values();
    }
}
