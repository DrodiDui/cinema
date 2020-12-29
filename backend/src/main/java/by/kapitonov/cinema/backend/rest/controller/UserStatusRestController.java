package by.kapitonov.cinema.backend.rest.controller;

import by.kapitonov.cinema.backend.rest.response.ApiResponse;
import by.kapitonov.cinema.backend.service.UserStatusService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/user-statuses")
public class UserStatusRestController {

    private final UserStatusService userStatusService;

    public UserStatusRestController(UserStatusService userStatusService) {
        this.userStatusService = userStatusService;
    }

    @GetMapping("")
    public ResponseEntity getAll() {

        List<String> statuses = userStatusService.getAllStatusNames();

        return new ResponseEntity(statuses, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<ApiResponse> create(String statusName) {

        userStatusService.create(statusName);

        return new ResponseEntity<>(new ApiResponse("User status successfully created"), HttpStatus.OK);
    }
}
