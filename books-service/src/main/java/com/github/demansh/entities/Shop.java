package com.github.demansh.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Shop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    @ManyToMany
    private List<Book> books;

    protected Shop() {
    }

    public Shop(String name, List<Book> books) {
        this.name = name;
        this.books = books;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
