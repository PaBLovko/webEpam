package by.traning.task07.service.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ServiceException extends Exception {
    public ServiceException(String message) {
        super(message);
    }
    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }
    public ServiceException(Throwable cause) {
        super(cause);
    }
}