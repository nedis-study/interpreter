package net.devstudy.interpreter.component.impl;

import net.devstudy.interpreter.component.OperationTreeBuilder;
import net.devstudy.interpreter.component.TokenParser;
import net.devstudy.interpreter.model.Operation;
import net.devstudy.interpreter.model.SimpleOperation;
import net.devstudy.interpreter.model.SourceLine;
import net.devstudy.interpreter.utils.DataUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class OperationTreeBuilderImpl implements OperationTreeBuilder {
    private final TokenParser tokenParser;

    public OperationTreeBuilderImpl(TokenParser tokenParser) {
        this.tokenParser = tokenParser;
    }

    @Override
    public List<Operation> buildTree(List<SourceLine> sourceLines) {
        Operation[] operations = new Operation[sourceLines.size()];
        for (int i = 0; i < sourceLines.size(); i++) {
            SourceLine sourceLine = sourceLines.get(i);
            List<String> tokens = tokenParser.parse(sourceLine.getLine());
            operations[i] = new SimpleOperation(tokens, sourceLine);
        }
        return DataUtils.unmodifiableList(Arrays.asList(operations));
    }
}
