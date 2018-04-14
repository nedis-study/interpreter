package net.devstudy.interpreter.model;

import net.devstudy.interpreter.utils.DataUtils;

import java.util.List;

public class SimpleOperation implements Operation {
    private final List<String> tokens;
    private final SourceLine sourceLine;

    public SimpleOperation(List<String> tokens, SourceLine sourceLine) {
        this.tokens = DataUtils.unmodifiableList(tokens);
        this.sourceLine = sourceLine;
    }

    @Override
    public SourceLine getSourceLine() {
        return sourceLine;
    }

    @Override
    public List<String> getTokens() {
        return tokens;
    }

    @Override
    public String toString() {
        return "SimpleOperation{" +
                "tokens=" + tokens +
                ", sourceLine=" + sourceLine +
                '}';
    }
}
