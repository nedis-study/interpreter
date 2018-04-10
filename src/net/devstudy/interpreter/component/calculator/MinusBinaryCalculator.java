package net.devstudy.interpreter.component.calculator;

import net.devstudy.interpreter.component.BinaryCalculator;
import net.devstudy.interpreter.exception.SyntaxInterpreterException;

public class MinusBinaryCalculator implements BinaryCalculator {
    @Override
    public Object calculate(Object value1, Object value2) {
        if (value1 instanceof Integer && value2 instanceof Integer) {
            return (Integer) value1 - (Integer) value2;
        } else if (value1 instanceof Double && value2 instanceof Double) {
            return (Double) value1 - (Double) value2;
        } else {
            throw new SyntaxInterpreterException("Operator - not supported for types: " +
                    getType(value1) + " and " + getType(value2));
        }
    }

    private String getType(Object value) {
        return value == null ? "null" : value.getClass().getSimpleName().toLowerCase();
    }
}
