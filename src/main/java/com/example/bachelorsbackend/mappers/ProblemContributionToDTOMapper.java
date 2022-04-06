package com.example.bachelorsbackend.mappers;

import com.example.bachelorsbackend.dtos.ProblemContributionDTO;
import com.example.bachelorsbackend.models.ProblemContribution;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Mapper(componentModel = "spring")
public abstract class ProblemContributionToDTOMapper {
    protected final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Mapping(target = "status", source = "dto.status", defaultExpression = "java(\"PENDING\")")
    @Mapping(target = "contributor", ignore = true)
    public abstract ProblemContribution dtoToEntity(ProblemContributionDTO dto);

    public abstract ProblemContributionDTO entityToDTO(ProblemContribution entity);

    protected String map(LocalDateTime createdTime) {
        return createdTime.format(formatter);
    }
}
