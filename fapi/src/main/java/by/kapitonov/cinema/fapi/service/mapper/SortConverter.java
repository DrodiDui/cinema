package by.kapitonov.cinema.fapi.service.mapper;

import org.springframework.data.domain.Sort;

import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class SortConverter {

    public static Sort mapToSort(Map<String, String> sortParams) {

        if (sortParams.isEmpty()) {
            return Sort.unsorted();
        }

        sortParams.remove("page");
        sortParams.remove("size");

        List<Sort.Order> orders = new LinkedList<>();
        for (Map.Entry params: sortParams.entrySet()) {
            Sort.Order order = new Sort.Order(
                    getDirection(params.getValue().toString()),
                    params.getKey().toString()
            );
            orders.add(order);
        }

        return Sort.by(orders);
    }

    private static Sort.Direction getDirection(String direction) {
        if ("asc".equals(direction.toLowerCase())) {
            return Sort.Direction.ASC;
        } else if ("desc".equals(direction.toLowerCase())) {
            return Sort.Direction.DESC;
        } else {
            throw new IllegalArgumentException("There is no such sorting method: " + direction);
        }

    }

}
