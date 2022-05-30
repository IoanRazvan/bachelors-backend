package com.example.bachelorsbackend.mappers;

import com.example.bachelorsbackend.dtos.ProblemRequestDTO;
import com.example.bachelorsbackend.dtos.ProblemSolutionDTO;
import com.example.bachelorsbackend.dtos.ProblemTestcaseDTO;
import com.example.bachelorsbackend.models.Problem;
import com.example.bachelorsbackend.models.ProblemSolution;
import com.example.bachelorsbackend.models.ProblemTestcase;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class ProblemMapper {
    @Mapping(target="problemSolutions", source = "solutions")
    @Mapping(target="problemTestcases", source = "testcases")
    @Mapping(target = "problemCategories", source = "categories")
    @Mapping(target="id", expression = "java(2)")
    public abstract Problem problemDtoToEntity(ProblemRequestDTO dto);

    @Mapping(target="programmingLanguage", expression = "java(new com.example.bachelorsbackend.models.ProgrammingLanguage(dto.getLanguageId()))")
    public abstract ProblemSolution problemSolutionDtoToEntity(ProblemSolutionDTO dto);

    public abstract List<ProblemSolution> problemSolutionDtoToEntity(List<ProblemSolutionDTO> dto);

    @Mapping(target="programmingLanguage", expression = "java(new com.example.bachelorsbackend.models.ProgrammingLanguage(dto.getLanguageId()))")
    public abstract List<ProblemTestcase> problemTestcaseDtoToEntity(List<ProblemTestcaseDTO> dto);

    public abstract ProblemTestcase problemTestcaseDtoToEntity(ProblemTestcaseDTO dto);
}
