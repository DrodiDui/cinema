package by.kapitonov.cinema.backend.exception;

public enum ApiErrorStatus {

    NOT_FOUNT("NOT FOUND"),
    ALREADY_EXISTS("ALREADY EXISTS");

    private String name;

    ApiErrorStatus(String name) {
        this.name = name;
    }
}
