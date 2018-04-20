package net.devstudy.interpreter.component.calculator;

import static net.devstudy.interpreter.utils.TypeUtils.getType;

import net.devstudy.interpreter.component.BinaryCalculator;
import net.devstudy.interpreter.exception.SyntaxInterpreterException;

public class MoreOrEqualsBinaryCalculator implements BinaryCalculator {
	@Override
    public Object calculate(Object value1, Object value2) {
        if (value1 instanceof Number && value2 instanceof Number) {
            return ((Number) value1).doubleValue() >= ((Number) value2).doubleValue();
        } else {
            throw new SyntaxInterpreterException("Operator >= not supported for types: " +
                    getType(value1) + " and " + getType(value2));
        }
    }
}
