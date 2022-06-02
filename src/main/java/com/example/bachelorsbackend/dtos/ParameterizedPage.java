package com.example.bachelorsbackend.dtos;

import lombok.Getter;
import org.springframework.data.domain.Slice;

import java.util.Map;

@Getter
public class ParameterizedPage<T> extends Page<T> {
    private Map<String, Object> parameters;

    protected ParameterizedPage(Slice<T> slice, Map<String, Object> parameters) {
        super(slice);
        this.parameters = parameters;
    }
}
