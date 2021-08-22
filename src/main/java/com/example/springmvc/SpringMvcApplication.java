package com.example.springmvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringMvcApplication {

    public static void main(String[] args) {
//        Flyway flyway = Flyway.configure().dataSource(
//                "jdbc:postgresql://localhost:5435/spring", "postgres", "postgrespass").load();
//        flyway.migrate();

        SpringApplication.run(SpringMvcApplication.class, args);

    }
}