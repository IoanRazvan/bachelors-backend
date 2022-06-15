package com.example.bachelorsbackend.mappers;

import com.example.bachelorsbackend.dtos.*;
import com.example.bachelorsbackend.models.Problem;
import com.example.bachelorsbackend.models.ProblemDifficulty;
import com.example.bachelorsbackend.models.ProblemSolution;
import com.example.bachelorsbackend.models.ProblemTestcase;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public abstract class ProblemMapper {
    @Mapping(target="problemSolutions", source = "solutions")
    @Mapping(target="problemTestcases", source = "testcases")
    @Mapping(target = "problemCategories", source = "categories")
    @Mapping(target="id", ignore = true)
    public abstract Problem problemDtoToEntity(ProblemRequestDTO dto);

    @Mapping(target="programmingLanguage", expression = "java(new com.example.bachelorsbackend.models.ProgrammingLanguage(dto.getLanguageId()))")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "problem", ignore = true)
    public abstract ProblemSolution problemSolutionDtoToEntity(ProblemSolutionDTO dto);

    public abstract List<ProblemSolution> problemSolutionDtoToEntity(List<ProblemSolutionDTO> dto);

    @Mapping(target="programmingLanguage", expression = "java(new com.example.bachelorsbackend.models.ProgrammingLanguage(dto.getLanguageId()))")
    public abstract List<ProblemTestcase> problemTestcaseDtoToEntity(List<ProblemTestcaseDTO> dto);

    @Mapping(target="id", ignore = true)
    @Mapping(target="problem", ignore = true)
    public abstract ProblemTestcase problemTestcaseDtoToEntity(ProblemTestcaseDTO dto);

    @Mapping(target="starters", expression = "java(convertSolutionsToStarters(problem.getProblemSolutions()))")
    @Mapping(target="categories", source = "problemCategories")
    public abstract ProblemResponseDTO problemEntityToResponseDto(Problem problem);

    protected List<ProblemStarterDTO> convertSolutionsToStarters(List<ProblemSolution> solutions) {
        List<ProblemStarterDTO> starters = new ArrayList<>();
        for (ProblemSolution solution : solutions)
            starters.add(convertSolutionToStarter(solution));
        return starters;
    }

    protected ProblemStarterDTO convertSolutionToStarter(ProblemSolution solution) {
        ProblemStarterDTO responseDTO = new ProblemStarterDTO();
        responseDTO.setLanguageId(solution.getProgrammingLanguage().getId());
        responseDTO.setLanguageName(solution.getProgrammingLanguage().getLanguageName());
        String starterCode = solution.getSourceCode().split("// starter")[1];
        String starterWithoutSolution = starterCode.replaceAll("(?s)// solution.+?// solution", "");
        responseDTO.setSourceCode(starterWithoutSolution);
        return responseDTO;
    }

    public ProblemRowDTO objectArrayToProblemRow(Object[] source) {
        ProblemRowDTO target = new ProblemRowDTO();
        target.setId((Integer) source[0]);
        target.setTitle((String) source[1]);
        target.setDifficulty((ProblemDifficulty) source[2]);
        target.setStatus((String) source[3]);
        return target;
    }
}
