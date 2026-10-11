package br.com.orientec.backend.application.port.out.model;

public record PageQuery(int page, int size, String sort) {
    public PageQuery {
        if (page < 0) page = 0;
        if (size < 1 || size > 200) size = 20;
        if (sort == null || sort.isBlank()) sort = "id,asc";
    }
}
