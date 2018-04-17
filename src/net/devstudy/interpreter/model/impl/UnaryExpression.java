package net.devstudy.interpreter.model.impl;

import net.devstudy.interpreter.component.UnaryCalculator;
import net.devstudy.interpreter.model.Expression;

public class UnaryExpression implements Expression {

    private final Expression operand;
    private final UnaryCalculator unaryCalculator;

    public UnaryExpression(Expression operand, UnaryCalculator unaryCalculator) {
        this.operand = operand;
        this.unaryCalculator = unaryCalculator;
    }

    @Override
    public Object getValue() {
        Object value = operand.getValue();
        return unaryCalculator.calculate(value);
    }
}
