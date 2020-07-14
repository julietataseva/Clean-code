package bg.sofia.uni.fmi.cleancode.ninemensmorris.exceptions;

public class OccupiedByBlackPlayerException extends RuntimeException {
    public OccupiedByBlackPlayerException() {
        super();
    }

    public OccupiedByBlackPlayerException(String message) {
        super(message);
    }

    public OccupiedByBlackPlayerException(String message, Throwable cause) {
        super(message, cause);
    }

    public OccupiedByBlackPlayerException(Throwable cause) {
        super(cause);
    }

    protected OccupiedByBlackPlayerException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
