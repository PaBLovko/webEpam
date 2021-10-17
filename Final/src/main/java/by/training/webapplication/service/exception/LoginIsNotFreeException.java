package by.training.webapplication.service.exception;

public class LoginIsNotFreeException extends Exception {
    public LoginIsNotFreeException() {
    }

    public LoginIsNotFreeException(String message) {
        super(message);
    }

    public LoginIsNotFreeException(String message, Throwable cause) {
        super(message, cause);
    }

    public LoginIsNotFreeException(Throwable cause) {
        super(cause);
    }
}
