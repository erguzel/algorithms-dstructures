package lib.util.exception;

public class NotTopologicallySortableException extends  BaseException{
    public NotTopologicallySortableException(String message, Throwable cause, boolean _killApp, boolean _throwIt, boolean _logIt) {
        super(message, cause, _killApp, _throwIt, _logIt);
    }

    public NotTopologicallySortableException(String message, Throwable cause) {
        super(message, cause);
    }
}
