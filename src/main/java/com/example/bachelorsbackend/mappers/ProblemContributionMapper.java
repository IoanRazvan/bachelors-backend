package com.example.bachelorsbackend.mappers;

import com.example.bachelorsbackend.dtos.ProblemContributionRequestDTO;
import com.example.bachelorsbackend.dtos.ProblemContributionResponseDTO;
import com.example.bachelorsbackend.models.ProblemContribution;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Mapper(componentModel = "spring")
public abstract class ProblemContributionMapper {
    protected final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Mapping(target = "contributor", ignore = true)
    @Mapping(target="createdTime", ignore = true)
    @Mapping(target="statusDetails", ignore = true)
    @Mapping(target="id", ignore=true)
    @Mapping(target = "status", constant = "PENDING")
    public abstract ProblemContribution dtoToEntity(ProblemContributionRequestDTO dto);

    public abstract ProblemContributionResponseDTO entityToDTO(ProblemContribution entity);

    protected String map(LocalDateTime createdTime) {
        return createdTime.format(formatter);
    }
}
