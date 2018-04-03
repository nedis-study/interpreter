package net.devstudy.interpreter.exception;

public class RuntimeInterpreterException extends InterpreterException {
    public RuntimeInterpreterException(String message) {
        super(message);
    }

    @Override
    protected ErrorType getErrorType() {
        return ErrorType.Runtime;
    }
}
