package com.egzel.lib.util.exception;

public class NonUniqueElementsException extends BaseException{
    public NonUniqueElementsException(String message, Throwable cause, boolean _killApp, boolean _throwIt, boolean _logIt) {
        super(message, cause, _killApp, _throwIt, _logIt);
    }

    public NonUniqueElementsException(String message, Throwable cause) {
        super(message, cause);
    }
}
