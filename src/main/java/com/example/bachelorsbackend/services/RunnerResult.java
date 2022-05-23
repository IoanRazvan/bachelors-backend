package com.example.bachelorsbackend.services;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RunnerResult {
    private int status;
    private String details;
}
