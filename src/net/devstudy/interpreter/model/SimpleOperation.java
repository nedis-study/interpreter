package net.devstudy.interpreter.model;

import java.util.Arrays;

public class SimpleOperation implements Operation {
    private final String[] tokens;
    private final SourceLine sourceLine;

    public SimpleOperation(String[] tokens, SourceLine sourceLine) {
        this.tokens = tokens;
        this.sourceLine = sourceLine;
    }

    @Override
    public SourceLine getSourceLine() {
        return sourceLine;
    }

    @Override
    public String[] getTokens() {
        return tokens;
    }

    @Override
    public String toString() {
        return "SimpleOperation{" +
                "tokens=" + Arrays.toString(tokens) +
                ", sourceLine=" + sourceLine +
                '}';
    }
}
