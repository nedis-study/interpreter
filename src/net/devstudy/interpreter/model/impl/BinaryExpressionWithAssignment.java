package net.devstudy.interpreter.model.impl;

import net.devstudy.interpreter.component.BinaryCalculator;
import net.devstudy.interpreter.model.Expression;

public class BinaryExpressionWithAssignment implements Expression {

    private final String operand1;
    private final Expression operand2;
    private final BinaryCalculator binaryCalculator;

    public BinaryExpressionWithAssignment(String operand1, Expression operand2,
                            BinaryCalculator binaryCalculator) {
        this.operand1 = operand1;
        this.operand2 = operand2;
        this.binaryCalculator = binaryCalculator;
    }

    @Override
    public Object getValue() {
        Object value1 = operand1;
        Object value2 = operand2.getValue();
        return binaryCalculator.calculate(value1, value2);
    }
}
