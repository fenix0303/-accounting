package ua.sych.exception;

public class WrongInputDataException extends RuntimeException {
    public WrongInputDataException(String message) {
        super(message);
    }
}
