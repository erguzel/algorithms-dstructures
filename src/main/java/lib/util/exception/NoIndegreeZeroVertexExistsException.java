package lib.util.exception;

public class NoIndegreeZeroVertexExistsException extends BaseException {
    public NoIndegreeZeroVertexExistsException(String message, Throwable cause, boolean _killApp, boolean _throwIt, boolean _logIt) {
        super(message, cause, _killApp, _throwIt, _logIt);
    }

    public NoIndegreeZeroVertexExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}
