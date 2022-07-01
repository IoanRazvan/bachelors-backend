package com.example.bachelorsbackend.dtos.submission;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class SubmissionDateCountDTO {
    private String date;
    private BigDecimal count;
}
