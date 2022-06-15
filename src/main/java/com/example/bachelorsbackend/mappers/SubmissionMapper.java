package com.example.bachelorsbackend.mappers;

import com.example.bachelorsbackend.dtos.AcceptedSubmissionDistributionBin;
import com.example.bachelorsbackend.dtos.FailedSubmissionDTO;
import com.example.bachelorsbackend.dtos.PassingSubmissionDTO;
import com.example.bachelorsbackend.dtos.SubmissionRowDTO;
import com.example.bachelorsbackend.models.Submission;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class SubmissionMapper {
    @Mapping(target = "error", expression = "java(entity.getFailedSubmission().getError())")
    @Mapping(target = "input", expression = "java(entity.getFailedSubmission().getInput())")
    @Mapping(target = "expected", expression = "java(entity.getFailedSubmission().getExpected())")
    @Mapping(target = "output", expression = "java(entity.getFailedSubmission().getOutput())")
    @Mapping(target = "languageId", expression = "java(entity.getProgrammingLanguage().getId())")
    public abstract FailedSubmissionDTO failedSubmissionEntityToDto(Submission entity);

    @Mapping(target = "runtime", expression = "java(entity.getPassingSubmission().getRuntime())")
    @Mapping(target = "languageId", expression = "java(entity.getProgrammingLanguage().getId())")
    @Mapping(target = "acceptedDistribution", expression = "java(bins.stream().map(this::objectArrayToAcceptedBin).collect(java.util.stream.Collectors.toList()))")
    public abstract PassingSubmissionDTO passingSubmissionEntityToDto(Submission entity, List<Object[]> bins);

    @Mapping(target = "languageId", expression = "java(submission.getProgrammingLanguage().getId())")
    @Mapping(target = "runtime", expression = "java(submission.getStatusCode() == 0 ? submission.getPassingSubmission().getRuntime() : -1)")
    public abstract SubmissionRowDTO submissionEntityToRowDto(Submission submission);

    protected AcceptedSubmissionDistributionBin objectArrayToAcceptedBin(Object[] source) {
        AcceptedSubmissionDistributionBin target = new AcceptedSubmissionDistributionBin();
        target.setRuntime((Long) source[0]);
        target.setCount((Long) source[1]);
        return target;
    }
}
