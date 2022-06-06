package lib.util.exception;

public class MultiDimentionalArrayEmptyException extends BaseException{
    public MultiDimentionalArrayEmptyException(String message, Throwable cause, boolean _killApp, boolean _throwIt, boolean _logIt) {
        super(message, cause, _killApp, _throwIt, _logIt);
    }

    public MultiDimentionalArrayEmptyException(String message, Throwable cause) {
        super(message, cause);
    }
}
