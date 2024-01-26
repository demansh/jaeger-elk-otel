package com.github.demansh.services;

import com.github.demansh.entities.Book;
import com.github.demansh.entities.BookAggregate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;
import java.util.Objects;

@Component
public class BooksService {

    private final RestTemplate restTemplate;
    private final String baseUrl;

    public BooksService(
            RestTemplate restTemplate,
            @Value("${booksClient.baseUrl}") String baseUrl) {
        this.restTemplate = restTemplate;
        this.baseUrl = baseUrl;
    }

    public Book getBook(long id) {
        String url = String.format("%s/books/%d", baseUrl, id);
        return restTemplate.getForObject(url, Book.class);
    }

    public Collection<Book> getAllBooks() {
        String url = String.format("%s/books", baseUrl);
        return Objects.requireNonNull(restTemplate.getForObject(url, Books.class)).books;
    }

    private record Books(Collection<Book> books) {
    }
}
