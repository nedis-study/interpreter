package net.devstudy.interpreter.component.impl;

import net.devstudy.interpreter.component.ExpressionBuilder;
import net.devstudy.interpreter.component.ExpressionResolver;
import net.devstudy.interpreter.exception.SyntaxInterpreterException;
import net.devstudy.interpreter.model.Expression;

import java.util.Arrays;
import java.util.List;

public class ExpressionResolverImpl implements ExpressionResolver {

    private final List<ExpressionBuilder> expressionBuilders;

    public ExpressionResolverImpl(List<ExpressionBuilder> expressionBuilders) {
        this.expressionBuilders = expressionBuilders;
    }

    @Override
    public Expression resolve(List<String> tokens) {
        for (ExpressionBuilder expressionBuilder : expressionBuilders) {
            if (expressionBuilder.isSupport(tokens)) {
                return expressionBuilder.build(tokens);
            }
        }
        throw new SyntaxInterpreterException("Undefined expression: " + tokens);
    }
}
