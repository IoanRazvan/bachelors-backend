package com.example.bachelorsbackend.converters;

import com.example.bachelorsbackend.dtos.AssignedContributionStatusCount;
import com.example.bachelorsbackend.models.ProblemContributionStatus;
import org.springframework.core.convert.converter.Converter;

import java.util.ArrayList;
import java.util.List;

public class ObjectArrayToAssignedContributionStatusCount implements Converter<List<Object[]>, List<AssignedContributionStatusCount>> {

    @Override
    public List<AssignedContributionStatusCount> convert(List<Object[]> source) {
        long[] count = new long[3];
        source.forEach((obj) -> {
            ProblemContributionStatus pcs = (ProblemContributionStatus) obj[0];
            count[pcs.ordinal()] = (Long) obj[1];
        });
        List<AssignedContributionStatusCount> converted = new ArrayList<>();

        for (int i = 0; i < 3; i++)
            converted.add(new AssignedContributionStatusCount(ProblemContributionStatus.values()[i], count[i]));

        return converted;
    }
}
