package by.kapitonov.cinema.backend.service;

import by.kapitonov.cinema.backend.model.CinemaStatus;

import java.util.List;

public interface CinemaStatusService {

    List<String> getAllStatusNames();

    CinemaStatus getByName(String statusName);

    CinemaStatus create(String statusName);

}
