package net.devstudy.interpreter.component.calculator.binary.logic;

import net.devstudy.interpreter.component.BinaryCalculator;
import net.devstudy.interpreter.exception.SyntaxInterpreterException;

import static net.devstudy.interpreter.utils.TypeUtils.getType;

public class OrBinaryCalculator implements BinaryCalculator {
    @Override
    public Object calculate(Object value1, Object value2) {
        if (value1 instanceof Boolean && value2 instanceof Boolean) {
            return (Boolean)value1 || (Boolean)value2;
        } else {
            throw new SyntaxInterpreterException("Operator || not supported for types: " +
                    getType(value1) + " and " + getType(value2));
        }
    }
}
