package com.example.bachelorsbackend.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WrongAnswer {
    private String input;
    private String actual;
    private String expected;
}
