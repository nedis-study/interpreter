package net.devstudy.interpreter.exception;

import net.devstudy.interpreter.component.impl.SourceLineHelper;
import net.devstudy.interpreter.model.SourceLine;

public abstract class InterpreterException extends RuntimeException {
    private String message;

    protected InterpreterException(String message) {
        this.message = buildErrorMessage(getErrorType(), message, SourceLineHelper.getSourceLine());
    }

    private String buildErrorMessage(ErrorType errorType, String message, SourceLine sourceLine) {
        if (sourceLine == null) {
            return String.format("%s error - %s", errorType, message);
        } else {
            return String.format("%s error [Line %s] - %s", errorType, sourceLine.getNumber(), message);
        }
    }

    @Override
    public String getMessage() {
        return message;
    }

    protected abstract ErrorType getErrorType();

    protected enum ErrorType {
        Syntax,

        Runtime;
    }
}
