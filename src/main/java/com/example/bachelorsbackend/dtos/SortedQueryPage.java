package com.example.bachelorsbackend.dtos;

import lombok.Getter;
import org.springframework.data.domain.Slice;

@Getter
public class SortedQueryPage<T> extends Page<T> {
    private String query;
    private String sorting;
    private String status;

    protected SortedQueryPage(Slice<T> slice, String query, String sorting, String status) {
        super(slice);
        this.query = query;
        this.sorting = sorting;
        this.status = status;
    }
}
