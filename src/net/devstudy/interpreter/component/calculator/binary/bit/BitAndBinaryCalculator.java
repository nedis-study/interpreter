package net.devstudy.interpreter.component.calculator.binary.bit;

import static net.devstudy.interpreter.utils.TypeUtils.getType;

import net.devstudy.interpreter.component.BinaryCalculator;
import net.devstudy.interpreter.component.calculator.AbstractCalculator;
import net.devstudy.interpreter.exception.SyntaxInterpreterException;

public class BitAndBinaryCalculator extends AbstractCalculator implements BinaryCalculator {
	private BitAndBinaryCalculator(String operator) {
		super(operator);
	}

	public static BitAndBinaryCalculator createArithmenticBitAndBinaryCalculator() {
		return new BitAndBinaryCalculator("&");
	}

	public static BitAndBinaryCalculator createAssignmentBitAndBinaryCalculator() {
		return new BitAndBinaryCalculator("&=");
	}
	
    @Override
    public Object calculate(Object value1, Object value2) {
        if (value1 instanceof Integer && value2 instanceof Integer) {
            return (Integer) value1 & (Integer) value2;
        }else if (value1 instanceof Boolean && value2 instanceof Boolean) {
            return (Boolean) value1 & (Boolean) value2;
        } else {
            throw new SyntaxInterpreterException("Operator & not supported for types: " +
                    getType(value1) + " and " + getType(value2));
        }
    }
}