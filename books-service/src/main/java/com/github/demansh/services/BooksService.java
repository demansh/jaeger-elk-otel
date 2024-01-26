package com.github.demansh.services;

import com.github.demansh.entities.Book;
import com.github.demansh.repository.BooksRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class BooksService {

    private final BooksRepository booksRepository;

    public BooksService(BooksRepository booksRepository) {
        this.booksRepository = booksRepository;
    }

    public Book getBook(long id) {
        return booksRepository.getBook(id);
    }

    public Collection<Book> getAll() {
        return booksRepository.getAll();
    }
}
