package net.devstudy.interpreter.model.impl;

import net.devstudy.interpreter.component.BinaryCalculator;
import net.devstudy.interpreter.exception.SyntaxInterpreterException;
import net.devstudy.interpreter.model.Expression;

import static net.devstudy.interpreter.utils.TypeUtils.getType;

public class BinaryAssignmentExpression extends BinaryExpression {
    public BinaryAssignmentExpression(Expression operand1, Expression operand2,
                                      BinaryCalculator binaryCalculator) {
        super(operand1, operand2, binaryCalculator);
        if (!(operand1 instanceof VariableExpression)) {
            throw new SyntaxInterpreterException("First operand should be variable expression");
        }
    }

    @Override
    protected VariableExpression getOperand1() {
        return (VariableExpression) super.getOperand1();
    }

    @Override
    public Object getValue() {
        Object newValue = super.getValue();
        getOperand1().setValue(newValue);
        return newValue;
    }
}
