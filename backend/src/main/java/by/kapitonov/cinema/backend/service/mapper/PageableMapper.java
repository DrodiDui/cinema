package by.kapitonov.cinema.backend.service.mapper;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PageableMapper {

    public static Pageable mapToPageable(Map<String, String> pageableParams) {

        if (pageableParams.isEmpty()) {
            return PageRequest.of(0, 10);
        }

        int page = Integer.parseInt(pageableParams.get("page"));
        int size = Integer.parseInt(pageableParams.get("size"));

        pageableParams.remove("page");
        pageableParams.remove("size");

        if (pageableParams.isEmpty()) {
            return PageRequest.of(page, size);
        }

        List<Sort.Order> orders = pageableParams.entrySet()
                .stream()
                .map((item) -> new Sort.Order(
                        getDirection(item.getValue().toString()),
                        item.getKey().toString()
                ))
                .collect(Collectors.toList());

        return PageRequest.of(
                page,
                size,
                Sort.by(orders)
        );
    }

    private static Sort.Direction getDirection(String sortDirection) {
        if ("asc".equals(sortDirection)) {
            return Sort.Direction.ASC;
        } else if ("desc".equals(sortDirection)) {
            return Sort.Direction.DESC;
        } else {
            throw new IllegalArgumentException("There is no such sorting method: " + sortDirection);
        }
    }

}
