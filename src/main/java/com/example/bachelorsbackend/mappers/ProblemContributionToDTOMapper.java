package com.example.bachelorsbackend.mappers;

import com.example.bachelorsbackend.dtos.ProblemContributionDTO;
import com.example.bachelorsbackend.models.ProblemContribution;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProblemContributionToDTOMapper {
    @Mapping(target="status", source="dto.status", defaultExpression = "java(\"PENDING\")")
    ProblemContribution dtoToEntity(ProblemContributionDTO dto);
    ProblemContributionDTO entityToDTO(ProblemContribution entity);
}
