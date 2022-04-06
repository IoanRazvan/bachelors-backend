package com.example.bachelorsbackend.dtos;

import lombok.Getter;
import org.springframework.data.domain.Slice;

import java.util.List;
import java.util.function.Function;

@Getter
public class Page<T> {
    private int page;
    private int size;
    private List<T> content;
    private boolean first;
    private boolean last;

    private Page(Slice<T> slice) {
        this.page = slice.getNumber();
        this.size = slice.getSize();
        this.content = slice.getContent();
        this.first = slice.isFirst();
        this.last = slice.isLast();
    }

    public static <U> Page<U> of(Slice<U> slice) {
        return new Page<>(slice);
    }

    public static <T1, T2> Page<T2> of(Slice<T1> slice, Function<? super T1, ? extends T2> mapper) {
        return new Page<>(slice.map(mapper));
    }
}
