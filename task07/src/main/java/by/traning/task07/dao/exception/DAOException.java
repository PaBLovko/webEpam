package by.traning.task07.dao.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class DAOException extends Exception {
    public DAOException(String message) {
        super(message);
    }
    public DAOException(String message, Throwable cause) {
        super(message, cause);
    }
    public DAOException(Throwable cause) {
        super(cause);
    }
}