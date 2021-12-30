package com.oasis.oasis_business;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class OasisBusinessApplication {

    public static void main(String[] args) {
        SpringApplication.run(OasisBusinessApplication.class, args);
    }

}
