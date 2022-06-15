package com.example.bachelorsbackend.mappers;

import com.example.bachelorsbackend.dtos.*;
import com.example.bachelorsbackend.models.ProblemContribution;
import com.example.bachelorsbackend.models.ProblemContributionStatus;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

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

    public abstract PreviousContributionRowDTO entityToPreviousContributionRow(ProblemContribution entity);

    @Mapping(target = "contributorUsername", expression = "java(entity.getContributor().getUsername())")
    public abstract UnassignedContributionRowDTO entityToUnassignedContributionRow(ProblemContribution entity);

    @Mapping(target = "contributorUsername", expression = "java(entity.getContributor().getUsername())")
    public abstract AssignedContributionRowDTO entityToAssignedContributionRow(ProblemContribution entity);

    public List<AssignedContributionStatusCount> objectArrayToContributionStatusCount(List<Object[]> source) {
        long[] count = new long[3];
        source.forEach((obj) -> {
            ProblemContributionStatus pcs = (ProblemContributionStatus) obj[0];
            count[pcs.ordinal()] = (Long) obj[1];
        });
        List<AssignedContributionStatusCount> converted = new ArrayList<>();

        for (int i = 0; i < 3; i++)
            converted.add(new AssignedContributionStatusCount(ProblemContributionStatus.values()[i], count[i]));

        return converted;
    }
}
