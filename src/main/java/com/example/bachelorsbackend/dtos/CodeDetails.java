package com.example.bachelorsbackend.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CodeDetails {
    private String code;
    private int langId;
    private String[] input;
    private String[] output;
}
