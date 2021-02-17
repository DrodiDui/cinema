package by.kapitonov.cinema.fapi.exception;

public class AccountAccessException extends RuntimeException {

    public AccountAccessException() {
        super();
    }

    public AccountAccessException(String message) {
        super(message);
    }

    public AccountAccessException(String message, Throwable cause) {
        super(message, cause);
    }

    public AccountAccessException(Throwable cause) {
        super(cause);
    }

    protected AccountAccessException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
