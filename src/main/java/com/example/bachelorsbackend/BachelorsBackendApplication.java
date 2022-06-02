package com.example.bachelorsbackend;

import com.example.bachelorsbackend.converters.ObjectArrayToProblemRowDTOConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class BachelorsBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(BachelorsBackendApplication.class, args);
    }

    @Bean
    ObjectArrayToProblemRowDTOConverter converter() {
        return new ObjectArrayToProblemRowDTOConverter();
    }
}
