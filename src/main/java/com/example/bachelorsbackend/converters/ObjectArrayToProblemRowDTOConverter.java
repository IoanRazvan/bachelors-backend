package com.example.bachelorsbackend.converters;

import com.example.bachelorsbackend.dtos.ProblemRowDTO;
import com.example.bachelorsbackend.models.ProblemDifficulty;
import org.springframework.core.convert.converter.Converter;

public class ObjectArrayToProblemRowDTOConverter implements Converter<Object[], ProblemRowDTO> {

    @Override
    public ProblemRowDTO convert(Object[] source) {
        ProblemRowDTO target = new ProblemRowDTO();
        target.setId((Integer)source[0]);
        target.setTitle((String)source[1]);
        target.setDifficulty((ProblemDifficulty) source[2]);
        target.setStatus((String)source[3]);
        return target;
    }
}
