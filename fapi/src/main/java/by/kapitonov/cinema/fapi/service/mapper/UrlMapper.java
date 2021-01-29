package by.kapitonov.cinema.fapi.service.mapper;

import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;

public class UrlMapper {

    public static String mapPramsToUrlWithParams(
            String url, Map<String, String> pageableParams
    ) {

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);

        pageableParams.entrySet()
                .stream()
                .forEach(param -> builder.queryParam(param.getKey(), param.getValue()));

        /*for (Map.Entry params: pageableParams.entrySet()) {
            builder.queryParam(String.valueOf(params.getKey()), params.getValue());
        }*/
        return builder.toUriString();
    }

}
