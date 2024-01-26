package com.github.demansh.entities;

import java.util.Collection;

public record BookAggregate(Book book, Collection<Shop> shops) {}
