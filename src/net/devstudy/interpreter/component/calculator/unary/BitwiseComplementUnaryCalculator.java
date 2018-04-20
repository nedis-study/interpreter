package net.devstudy.interpreter.component.calculator.unary;

import static net.devstudy.interpreter.utils.TypeUtils.getType;

import net.devstudy.interpreter.component.UnaryCalculator;
import net.devstudy.interpreter.exception.SyntaxInterpreterException;

public class BitwiseComplementUnaryCalculator implements UnaryCalculator {
	@Override
	public Object calculate(Object value) {
		if (value instanceof Integer) {
			return ~(Integer) value;
		} else {
			throw new SyntaxInterpreterException("Operator ~ not supported for type: " + getType(value));
		}
	}
}
