package by.kapitonov.cinema.backend.service.mapper;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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

        List<Sort.Order> orders = new LinkedList<>();
        for (Map.Entry params: pageableParams.entrySet()) {
            Sort.Order order = new Sort.Order(
                    getDirection(params.getValue().toString()),
                    params.getKey().toString()
            );
            orders.add(order);
        }

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
