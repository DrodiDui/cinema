package by.kapitonov.cinema.backend.service;

import by.kapitonov.cinema.backend.model.UserStatus;

import java.util.List;

public interface UserStatusService {

    List<UserStatus> getAll();
    List<String> getAllStatusNames();

    UserStatus getByName(String statusName);

    UserStatus create(String statusName);

}
