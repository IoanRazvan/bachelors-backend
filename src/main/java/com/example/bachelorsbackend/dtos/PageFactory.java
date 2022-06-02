package com.example.bachelorsbackend.dtos;

import org.springframework.data.domain.Slice;

import java.util.Map;
import java.util.function.Function;

public class PageFactory {
    public static <U> Page<U> of(Slice<U> slice) {
        return new Page<>(slice);
    }

    public static <U> Page<U> of(Slice<U> slice, Map<String, Object> parameters) {
        return new ParameterizedPage<>(slice, parameters);
    }

    public static <T1, T2> Page<T2> of(Slice<T1> slice, Function<? super T1, ? extends T2> mapper) {
        return new Page<>(slice.map(mapper));
    }

    public static <T1, T2> Page<T2> of(Slice<T1> slice, Function<? super T1, ? extends T2> mapper, Map<String, Object> parameters) {
        return new ParameterizedPage<>(slice.map(mapper), parameters);
    }
}
