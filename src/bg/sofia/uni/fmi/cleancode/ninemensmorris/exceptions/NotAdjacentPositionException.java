package bg.sofia.uni.fmi.cleancode.ninemensmorris.exceptions;


public class NotAdjacentPositionException extends RuntimeException {
    public NotAdjacentPositionException() {
    }

    public NotAdjacentPositionException(String message) {
        super(message);
    }

    public NotAdjacentPositionException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotAdjacentPositionException(Throwable cause) {
        super(cause);
    }

    public NotAdjacentPositionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
