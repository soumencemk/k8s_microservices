package com.soumen.kubemongodemo;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.UUID;
import java.util.stream.Stream;

@SpringBootApplication
@RequiredArgsConstructor
public class KubeMongoDemoApplication implements CommandLineRunner {

    private final MovieRepository movieRepository;

    public static void main(String[] args) {
        SpringApplication.run(KubeMongoDemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Stream.of("A", "B", "C", "D", "E")
                .map(name -> new Movie(UUID.randomUUID().toString(), name))
                .forEach(this.movieRepository::save);
    }
}
