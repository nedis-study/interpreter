package net.devstudy.interpreter.component.calculator.binary.predicate;

import static net.devstudy.interpreter.utils.TypeUtils.getType;

import net.devstudy.interpreter.component.BinaryCalculator;
import net.devstudy.interpreter.component.calculator.AbstractCalculator;
import net.devstudy.interpreter.exception.SyntaxInterpreterException;

public class MoreOrEqualsBinaryCalculator extends AbstractCalculator implements BinaryCalculator {
	public MoreOrEqualsBinaryCalculator() {
		super(">=");
	}
	
	@Override
	public Object calculate(Object value1, Object value2) {
		if (value1 instanceof Integer && value2 instanceof Integer) {
			return (Integer) value1 >= (Integer) value2;
		} else if (value1 instanceof Double && value2 instanceof Double) {
			return (Double) value1 >= (Double) value2;
		} else if (value1 instanceof String && value2 instanceof String) {
			int result = value1.toString().compareTo(value2.toString());
			if (result >= 0) {
				return true;
			} else {
				return false;
			}
		} else {
			if (value1 != null && value2 != null) {
				if (value1.getClass() == value2.getClass()) {
					return value1.equals(value2);
				} else {
					throw new SyntaxInterpreterException("Operator " + getOperator() + " not supported for types: "
							+ getType(value1) + " and " + getType(value2));
				}
			} else {
				return value1 == null && value2 == null;

			}
		}
	}
}
