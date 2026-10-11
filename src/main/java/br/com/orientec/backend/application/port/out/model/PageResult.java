package br.com.orientec.backend.application.port.out.model;

import java.util.List;
import java.util.function.Function;

public record PageResult<E>(
        List<E> content,
        long totalElements,
        int totalPages,
        int page,
        int size) {
    public <R> PageResult<R> map(Function<E, R> mapper) {
        return new PageResult<>(content.stream().map(mapper).toList(),
                totalElements, totalPages, page, size);
    }
}
