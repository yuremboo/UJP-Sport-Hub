package com.softserve.edu.sporthubujp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SportHubUjpApplication {

    public static void main(String[] args) {
        SpringApplication.run(SportHubUjpApplication.class, args);
    }
}
