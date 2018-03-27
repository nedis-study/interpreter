package net.devstudy.interpreter.component.impl;

import net.devstudy.interpreter.component.OperationTreeBuilder;
import net.devstudy.interpreter.component.TokenParser;
import net.devstudy.interpreter.model.Operation;
import net.devstudy.interpreter.model.SimpleOperation;
import net.devstudy.interpreter.model.SourceLine;

public class OperationTreeBuilderImpl implements OperationTreeBuilder {
    private final TokenParser tokenParser;

    public OperationTreeBuilderImpl(TokenParser tokenParser) {
        this.tokenParser = tokenParser;
    }

    @Override
    public Operation[] buildTree(SourceLine[] sourceLines) {
        Operation[] operations = new Operation[sourceLines.length];
        for (int i = 0; i < sourceLines.length; i++) {
            SourceLine sourceLine = sourceLines[i];
            String[] tokens = tokenParser.parse(sourceLine.getLine());
            operations[i] = new SimpleOperation(tokens, sourceLine);
        }
        return operations;
    }
}
