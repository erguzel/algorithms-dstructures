package lib.util.exception;

public class InconsistentGraphException extends BaseException{
    public InconsistentGraphException(String message, Throwable cause, boolean _killApp, boolean _throwIt, boolean _logIt) {
        super(message, cause, _killApp, _throwIt, _logIt);
    }

    public InconsistentGraphException(String message, Throwable cause) {
        super(message, cause);
    }
}
