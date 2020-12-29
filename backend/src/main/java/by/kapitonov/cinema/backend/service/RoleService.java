package by.kapitonov.cinema.backend.service;

import by.kapitonov.cinema.backend.model.Role;

import java.util.List;

public interface RoleService {

    List<Role> getAll();
    List<String> getAllRoleNames();

    Role getByName(String roleName);

    Role create(String roleName);
}
