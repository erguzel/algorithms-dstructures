package com.egzel.lib.util.exception;

public class MultiDimentionalArrayHasNullElementsException extends BaseException{
    public MultiDimentionalArrayHasNullElementsException(String message, Throwable cause, boolean _killApp, boolean _throwIt, boolean _logIt) {
        super(message, cause, _killApp, _throwIt, _logIt);
    }

    public MultiDimentionalArrayHasNullElementsException(String message, Throwable cause) {
        super(message, cause);
    }
}
