package br.com.orientec.backend.adapter.out.persistence;

import br.com.orientec.backend.application.port.out.model.PageQuery;
import br.com.orientec.backend.application.port.out.model.PageResult;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public final class SpringDataPageAdapter {
    private SpringDataPageAdapter() {}

    public static Pageable toPageable(PageQuery query) {
        String[] parts = query.sort().split(",", 2);
        Sort.Direction direction = parts.length > 1 && "desc".equalsIgnoreCase(parts[1])
                ? Sort.Direction.DESC : Sort.Direction.ASC;
        return PageRequest.of(query.page(), query.size(), Sort.by(direction, parts[0]));
    }

    public static <E> PageResult<E> toResult(Page<E> page) {
        return new PageResult<>(page.getContent(), page.getTotalElements(),
                page.getTotalPages(), page.getNumber(), page.getSize());
    }
}
