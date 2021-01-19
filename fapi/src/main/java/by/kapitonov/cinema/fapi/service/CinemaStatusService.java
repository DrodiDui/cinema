package by.kapitonov.cinema.fapi.service;

import by.kapitonov.cinema.fapi.rest.response.ApiResponse;

import java.util.List;

public interface CinemaStatusService {

    List<String> getAllStatusNames();

    ApiResponse create(String statusName);

}
