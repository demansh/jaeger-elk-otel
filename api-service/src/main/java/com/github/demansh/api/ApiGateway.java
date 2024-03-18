package com.github.demansh.api;

import com.github.demansh.entities.Book;
import com.github.demansh.entities.BookAggregate;
import com.github.demansh.entities.Shop;
import com.github.demansh.services.BooksService;
import com.github.demansh.services.ShopsService;
import io.opentelemetry.instrumentation.annotations.SpanAttribute;
import io.opentelemetry.instrumentation.annotations.WithSpan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.stream.Collectors;

@RestController
public class ApiGateway {

    private final BooksService booksService;
    private final ShopsService shopsService;

    public ApiGateway(BooksService booksService, ShopsService shopsService) {
        this.booksService = booksService;
        this.shopsService = shopsService;
    }

    @WithSpan
    @GetMapping(path = "books/{id}")
    public BookAggregate getBook(@SpanAttribute @PathVariable("id") long bookId) {
        Book book = booksService.getBook(bookId);
        Collection<Shop> shops = shopsService.findShopsForBook(bookId);
        return new BookAggregate(book, shops);
    }

    @WithSpan
    @GetMapping(path = "books")
    public Collection<BookAggregate> getAllBooks() {
        return booksService.getAllBooks().stream()
                .map(book -> new BookAggregate(book, shopsService.findShopsForBook(book.id())))
                .collect(Collectors.toList());
    }
}
