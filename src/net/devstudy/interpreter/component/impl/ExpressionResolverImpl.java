package net.devstudy.interpreter.component.impl;

import net.devstudy.interpreter.component.ExpressionBuilder;
import net.devstudy.interpreter.component.ExpressionResolver;
import net.devstudy.interpreter.exception.SyntaxInterpreterException;
import net.devstudy.interpreter.model.Expression;

import java.util.Arrays;

public class ExpressionResolverImpl implements ExpressionResolver {

    private final ExpressionBuilder[] expressionBuilders;

    public ExpressionResolverImpl(ExpressionBuilder[] expressionBuilders) {
        this.expressionBuilders = expressionBuilders;
    }

    @Override
    public Expression resolve(String... tokens) {
        for (ExpressionBuilder expressionBuilder : expressionBuilders) {
            if (expressionBuilder.isSupport(tokens)) {
                return expressionBuilder.build(tokens);
            }
        }
        throw new SyntaxInterpreterException("Undefined expression: " + Arrays.toString(tokens));
    }
}
