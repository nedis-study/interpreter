package net.devstudy.interpreter.component.impl;

import net.devstudy.interpreter.component.VariableVerifier;
import net.devstudy.interpreter.exception.SyntaxInterpreterException;

import static net.devstudy.interpreter.component.KeyWords.ALL_KEY_WORDS;

public class VariableVerifierImpl implements VariableVerifier {
    @Override
    public void validate(String variableName) {
        validateJavaVariableName(variableName);
        validateUsingKeywords(variableName);
    }

    private void validateUsingKeywords(String variableName) {
        if (ALL_KEY_WORDS.contains(variableName)) {
            throw new SyntaxInterpreterException("Variable name can't be a key world: " + variableName);
        }
    }

    private void validateJavaVariableName(String variableName) {
        if (variableName.length() > 0) {
            char first = variableName.charAt(0);
            if (first != '_' && !Character.isLetter(first)) {
                throw new SyntaxInterpreterException("Invalid variable name: '"
                        + variableName + "'. First character of variable name should be a letter or _");
            }
        } else {
            throw new SyntaxInterpreterException("Invalid variable name: '"
                    + variableName + "'. Variable name can't be an empty");
        }
    }
}
