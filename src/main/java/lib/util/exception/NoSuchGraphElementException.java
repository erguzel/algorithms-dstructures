package lib.util.exception;

public class NoSuchGraphElementException extends BaseException{
    public NoSuchGraphElementException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoSuchGraphElementException(String message, Throwable cause, boolean _killApp, boolean _throwIt, boolean _logIt) {
        super(message, cause, _killApp, _throwIt, _logIt);

    }
}
