package bg.sofia.uni.fmi.cleancode.ninemensmorris.exceptions;

public class UnavailablePositionException extends RuntimeException {
    public UnavailablePositionException() {
        super();
    }

    public UnavailablePositionException(String message) {
        super(message);
    }

    public UnavailablePositionException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnavailablePositionException(Throwable cause) {
        super(cause);
    }

    protected UnavailablePositionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}