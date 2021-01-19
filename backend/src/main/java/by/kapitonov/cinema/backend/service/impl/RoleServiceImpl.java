package by.kapitonov.cinema.backend.service.impl;

import by.kapitonov.cinema.backend.exception.ModelNotFoundException;
import by.kapitonov.cinema.backend.model.Role;
import by.kapitonov.cinema.backend.repository.RoleRepository;
import by.kapitonov.cinema.backend.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<Role> getAll() {
        return roleRepository.findAll();
    }

    @Override
    public List<String> getAllRoleNames() {
        return roleRepository.findAll()
                .stream()
                .map(Role::getRoleName)
                .collect(Collectors.toList());
    }

    @Override
    public Role getByName(String roleName) {
        return roleRepository.findByRoleName(roleName)
                .orElseThrow(
                        () -> new ModelNotFoundException("Role name hasn't been found")
                );
    }

    @Override
    public Role create(String roleName) {
        roleName = "ROLE_" + roleName.toUpperCase();
        return roleRepository.save(new Role(roleName));
    }
}
