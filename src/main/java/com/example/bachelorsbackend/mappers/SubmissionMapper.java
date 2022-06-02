package com.example.bachelorsbackend.mappers;

import com.example.bachelorsbackend.dtos.FailedSubmissionDTO;
import com.example.bachelorsbackend.dtos.PassingSubmissionDTO;
import com.example.bachelorsbackend.dtos.SubmissionDTO;
import com.example.bachelorsbackend.dtos.SubmissionRowDTO;
import com.example.bachelorsbackend.models.FailedSubmission;
import com.example.bachelorsbackend.models.PassingSubmission;
import com.example.bachelorsbackend.models.Submission;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class SubmissionMapper {
    public abstract List<SubmissionDTO> submissionEntitiesToDTOs(List<Submission> submissions);

    @Mapping(target="passingDto", source="passingSubmission")
    @Mapping(target="failedDto", source="failedSubmission")
    @Mapping(target="languageId", expression="java(entity.getProgrammingLanguage().getId())")
    public abstract SubmissionDTO submissionEntityToDto(Submission entity);

    public abstract FailedSubmissionDTO failedSubmissionEntityToDto(FailedSubmission entity);

    public abstract PassingSubmissionDTO passingSubmissionEntityToDto(PassingSubmission entity);

    @Mapping(target="languageId", expression = "java(submission.getProgrammingLanguage().getId())")
    @Mapping(target="runtime", expression = "java(submission.getStatusCode() == 0 ? submission.getPassingSubmission().getRuntime() : -1)")
    public abstract SubmissionRowDTO submissionEntityToRowDto(Submission submission);

    public abstract List<SubmissionRowDTO> submissionEntitiesToRowDTOs(List<Submission> submission);
}
