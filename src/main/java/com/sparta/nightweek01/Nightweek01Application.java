package com.sparta.nightweek01;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.persistence.EntityListeners;

@EnableJpaAuditing
@SpringBootApplication
public class Nightweek01Application {

    public static void main(String[] args) {
        SpringApplication.run(Nightweek01Application.class, args);
    }

}
