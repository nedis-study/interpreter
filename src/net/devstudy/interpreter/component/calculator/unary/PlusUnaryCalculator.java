package net.devstudy.interpreter.component.calculator.unary;

import net.devstudy.interpreter.component.UnaryCalculator;
import net.devstudy.interpreter.exception.SyntaxInterpreterException;

import static net.devstudy.interpreter.utils.TypeUtils.getType;

public class PlusUnaryCalculator implements UnaryCalculator {
    @Override
    public Object calculate(Object value) {
        if (value instanceof Number) {
            return value;
        } else {
            throw new SyntaxInterpreterException("Operator + not supported for type: " + getType(value));
        }
    }
}
