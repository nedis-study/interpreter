package net.devstudy.interpreter.exception;

public class SyntaxInterpreterException extends InterpreterException {
    public SyntaxInterpreterException(String message) {
        super(message);
    }

    @Override
    protected ErrorType getErrorType() {
        return ErrorType.Syntax;
    }
}
