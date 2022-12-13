package lib.util.exception;

public class InvalidEdgeWeightPairException extends BaseException{
    public InvalidEdgeWeightPairException(String message, Throwable cause, boolean _killApp, boolean _throwIt, boolean _logIt) {
        super(message, cause, _killApp, _throwIt, _logIt);
    }

    public InvalidEdgeWeightPairException(String message, Throwable cause) {
        super(message, cause);
    }
}
