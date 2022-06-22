package com.example.bachelorsbackend.dtos.coderunner;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CodeDetails {
    private String code;
    private String langId;
    private String[] input;
    private String[] output;
}
