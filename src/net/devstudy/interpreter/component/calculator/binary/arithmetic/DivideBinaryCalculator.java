package net.devstudy.interpreter.component.calculator.binary.arithmetic;

import static net.devstudy.interpreter.utils.TypeUtils.getType;

import net.devstudy.interpreter.component.BinaryCalculator;
import net.devstudy.interpreter.component.calculator.AbstractCalculator;
import net.devstudy.interpreter.exception.SyntaxInterpreterException;
import net.devstudy.interpreter.utils.DivideByZeroUtils;

public class DivideBinaryCalculator extends AbstractCalculator implements BinaryCalculator {
	private DivideBinaryCalculator(String operator) {
		super(operator);
	}

	public static DivideBinaryCalculator createArithmenticDivideBinaryCalculator() {
		return new DivideBinaryCalculator("/");
	}

	public static DivideBinaryCalculator createAssignmentDivideBinaryCalculator() {
		return new DivideBinaryCalculator("/=");
	}

	@Override
	public Object calculate(Object value1, Object value2) {
		DivideByZeroUtils.check(value2);
		if (value1 instanceof Integer && value2 instanceof Integer) {
			return (Integer) value1 / (Integer) value2;

		} else if (value1 instanceof Number && value2 instanceof Number) {
			return ((Number) value1).doubleValue() / ((Number) value2).doubleValue();
		} else {
			throw new SyntaxInterpreterException(
					"Operator / not supported for types: " + getType(value1) + " and " + getType(value2));
		}
	}
}