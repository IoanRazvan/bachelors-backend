package com.example.bachelorsbackend.dtos;

import lombok.Getter;
import org.springframework.data.domain.Slice;

import java.util.List;

@Getter
public class Page<T> {
    private int page;
    private int size;
    private List<T> content;
    private boolean first;
    private boolean last;

    protected Page(Slice<T> slice) {
        this.page = slice.getNumber();
        this.size = slice.getSize();
        this.content = slice.getContent();
        this.first = slice.isFirst();
        this.last = slice.isLast();
    }
}
