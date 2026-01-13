package com.aluracursos.screenmatch;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ScreenmatchApplication {

    @Value("${omdb.api.key}")
    private String apiKey;

    public static void main(String[] args) {
        SpringApplication.run(ScreenmatchApplication.class, args);
    }
}