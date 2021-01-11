package by.kapitonov.cinema.fapi.service;

import by.kapitonov.cinema.fapi.rest.response.ApiResponse;

import java.util.List;

public interface UserStatusService {

    List<String> getAll();

    ApiResponse create(String statusName);

}
