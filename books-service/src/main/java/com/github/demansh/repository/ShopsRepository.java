package com.github.demansh.repository;

import com.github.demansh.entities.Book;
import com.github.demansh.entities.Shop;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ShopsRepository {
    private final Map<Long, Shop> shops = Map.of(
            1001L, new Shop(1001L, "Chitai Gorod. Perm, Ulitsa Sibirskaya, 10"),
            1002L, new Shop(1002L, "Chitai Gorod. Perm, Ulitsa Popova, 23"),
            1003L, new Shop(1003L, "Chitai Gorod. Perm, Komsomol'skaya Ulitsa, 54")
    );
    private final Map<Long, Set<Long>> stock = Map.of(
            1001L, Set.of(100001L, 100002L),
            1002L, Set.of(100001L, 100003L, 100004L),
            1003L, Set.of(100003L, 100004L, 100005L)
    );
    public Collection<Shop> findShopsForBook(long bookId) {
        return stock.entrySet().stream()
                .filter(entry -> entry.getValue()
                .contains(bookId))
                .map(Map.Entry::getKey)
                .map(shops::get)
                .collect(Collectors.toSet());
    }
}
