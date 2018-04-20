package net.devstudy.interpreter.component.calculator.unary;

import static net.devstudy.interpreter.utils.TypeUtils.getType;

import net.devstudy.interpreter.component.UnaryCalculator;
import net.devstudy.interpreter.exception.SyntaxInterpreterException;

public class MinusUnaryCalculator implements UnaryCalculator {
	@Override
	public Object calculate(Object value) {
		if (value instanceof Integer) {
			return -((Number) value).intValue();
		}else if (value instanceof Double) {
			return -((Number) value).doubleValue();
		} else {
			throw new SyntaxInterpreterException("Operator - not supported for type: " + getType(value));
		}
	}
}
