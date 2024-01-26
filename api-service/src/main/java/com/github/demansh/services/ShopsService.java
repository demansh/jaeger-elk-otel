package com.github.demansh.services;

import com.github.demansh.entities.Shop;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Component
public class ShopsService {

    private final RestTemplate restTemplate;
    private final String baseUrl;

    public ShopsService(
            RestTemplate restTemplate,
            @Value("${shopsClient.baseUrl}") String baseUrl) {
        this.restTemplate = restTemplate;
        this.baseUrl = baseUrl;
    }

    public Collection<Shop> findShopsForBook(long bookId) {
        String url = String.format("%s/books/%d/shops", baseUrl, bookId);
        return Arrays.stream(Objects.requireNonNull(restTemplate.getForObject(url, Shop[].class))).toList();
    }
}
