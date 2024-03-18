package com.github.demansh;

import io.opentelemetry.instrumentation.spring.autoconfigure.EnableOpenTelemetry;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableOpenTelemetry
public class ApiServiceApp {
    public static void main(String[] args) {
        SpringApplication.run(ApiServiceApp.class, args);
    }
}
