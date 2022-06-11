package com.example.bachelorsbackend;

import com.example.bachelorsbackend.converters.ObjectArrayToAcceptedSubmissionDistributionBinConverter;
import com.example.bachelorsbackend.converters.ObjectArrayToAssignedContributionStatusCount;
import com.example.bachelorsbackend.converters.ObjectArrayToProblemRowDTOConverter;
import com.example.bachelorsbackend.converters.ObjectArrayToUserListRowDTOConverter;
import com.example.bachelorsbackend.dtos.AcceptedSubmissionDistributionBin;
import com.example.bachelorsbackend.dtos.AssignedContributionStatusCount;
import com.example.bachelorsbackend.dtos.ProblemRowDTO;
import com.example.bachelorsbackend.dtos.UserListRowDTO;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.List;

@SpringBootApplication
@EnableJpaAuditing
public class BachelorsBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(BachelorsBackendApplication.class, args);
    }

    @Bean
    Converter<Object[], ProblemRowDTO> problemRowConverter() {
        return new ObjectArrayToProblemRowDTOConverter();
    }

    @Bean
    Converter<Object[], UserListRowDTO> userLisRowConverter() {
        return new ObjectArrayToUserListRowDTOConverter();
    }

    @Bean
    Converter<List<Object[]>, List<AssignedContributionStatusCount>> assignedContributionStatusCountConverter() {
        return new ObjectArrayToAssignedContributionStatusCount();
    }

    @Bean
    Converter<Object[], AcceptedSubmissionDistributionBin> acceptedSubmissionDistributionBinConverter() {
        return new ObjectArrayToAcceptedSubmissionDistributionBinConverter();
    }
}
