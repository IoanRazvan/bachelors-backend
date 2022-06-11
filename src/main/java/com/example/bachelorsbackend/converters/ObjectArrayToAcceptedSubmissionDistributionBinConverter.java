package com.example.bachelorsbackend.converters;

import com.example.bachelorsbackend.dtos.AcceptedSubmissionDistributionBin;
import org.springframework.core.convert.converter.Converter;

public class ObjectArrayToAcceptedSubmissionDistributionBinConverter implements Converter<Object[], AcceptedSubmissionDistributionBin> {

    @Override
    public AcceptedSubmissionDistributionBin convert(Object[] source) {
        AcceptedSubmissionDistributionBin target = new AcceptedSubmissionDistributionBin();
        target.setRuntime((Long) source[0]);
        target.setCount((Long) source[1]);
        return target;
    }
}
