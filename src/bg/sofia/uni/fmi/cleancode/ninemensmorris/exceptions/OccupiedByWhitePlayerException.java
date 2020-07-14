package bg.sofia.uni.fmi.cleancode.ninemensmorris.exceptions;

public class OccupiedByWhitePlayerException extends RuntimeException {
    public OccupiedByWhitePlayerException() {
        super();
    }

    public OccupiedByWhitePlayerException(String message) {
        super(message);
    }

    public OccupiedByWhitePlayerException(String message, Throwable cause) {
        super(message, cause);
    }

    public OccupiedByWhitePlayerException(Throwable cause) {
        super(cause);
    }

    protected OccupiedByWhitePlayerException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
