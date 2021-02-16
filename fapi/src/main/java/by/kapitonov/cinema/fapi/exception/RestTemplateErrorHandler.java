package by.kapitonov.cinema.fapi.exception;

import by.kapitonov.cinema.fapi.rest.response.ApiExceptionResponse;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;
import java.io.InputStream;

public class RestTemplateErrorHandler implements ResponseErrorHandler {

    @Override
    public boolean hasError(ClientHttpResponse clientHttpResponse) throws IOException {
        return clientHttpResponse.getStatusCode() != HttpStatus.OK;
    }

    @Override
    public void handleError(ClientHttpResponse clientHttpResponse) throws IOException {

        ApiExceptionResponse response = toExceptionResponseObject(clientHttpResponse.getBody());

        if (clientHttpResponse.getStatusCode() == HttpStatus.NOT_FOUND) {
            throw new ModelNotFoundException(response.getExceptionMessage());
        }

        if (clientHttpResponse.getStatusCode() == HttpStatus.FOUND) {
            throw new ModelAlreadyExistsException(response.getExceptionMessage());
        }

    }

    private ApiExceptionResponse toExceptionResponseObject(InputStream inputStream) {
        ApiExceptionResponse response = null;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            response = objectMapper.readValue(inputStream, ApiExceptionResponse.class);
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }
}
