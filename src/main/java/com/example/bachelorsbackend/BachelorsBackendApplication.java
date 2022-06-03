package com.example.bachelorsbackend;

import com.example.bachelorsbackend.converters.ObjectArrayToProblemRowDTOConverter;
import com.example.bachelorsbackend.converters.ObjectArrayToUserListRowDTOConverter;
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
    ObjectArrayToProblemRowDTOConverter problemRowConverter() {
        return new ObjectArrayToProblemRowDTOConverter();
    }

    @Bean
    ObjectArrayToUserListRowDTOConverter userLisRowConverter() {
        return new ObjectArrayToUserListRowDTOConverter();
    }
}
