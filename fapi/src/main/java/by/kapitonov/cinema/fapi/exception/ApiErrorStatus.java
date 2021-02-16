package by.kapitonov.cinema.fapi.exception;

public enum ApiErrorStatus {

    NOT_FOUNT("NOT FOUND"),
    ALREADY_EXISTS("ALREADY EXISTS"),
    INVALID_CREDENTIAL("INVALID CREDENTIAL"),
    INVALID_TOKEN("INVALID TOKEN"),
    ACCESS_DENIED("ACCESS DENIED");

    private String name;

    ApiErrorStatus(String name) {
        this.name = name;
    }

}
