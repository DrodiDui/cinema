package by.kapitonov.cinema.fapi.service;

import by.kapitonov.cinema.fapi.rest.response.ApiResponse;

import java.util.List;

public interface RoleService {

    List<String> getAll();

    ApiResponse create(String roleName);

}
