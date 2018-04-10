package net.devstudy.interpreter.model.impl;

import net.devstudy.interpreter.model.Expression;

public class SimpleExpression implements Expression {
    private final Object value;

    public SimpleExpression(Object value) {
        this.value = value;
    }

    @Override
    public Object getValue() {
        return value;
    }
}
