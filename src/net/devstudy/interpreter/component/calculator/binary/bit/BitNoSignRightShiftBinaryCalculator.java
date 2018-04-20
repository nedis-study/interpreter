package net.devstudy.interpreter.component.calculator.binary.bit;

import net.devstudy.interpreter.component.BinaryCalculator;
import net.devstudy.interpreter.component.calculator.AbstractCalculator;
import net.devstudy.interpreter.exception.SyntaxInterpreterException;

import static net.devstudy.interpreter.utils.TypeUtils.getType;

public class BitNoSignRightShiftBinaryCalculator  extends AbstractCalculator  implements BinaryCalculator {
	private BitNoSignRightShiftBinaryCalculator(String operator) {
		super(operator);
	}

	public static BitNoSignRightShiftBinaryCalculator createArithmenticBitNoSignRightShiftBinaryCalculator() {
		return new BitNoSignRightShiftBinaryCalculator(">>>");
	}

	public static BitNoSignRightShiftBinaryCalculator createAssignmentBitNoSignRightShiftBinaryCalculator() {
		return new BitNoSignRightShiftBinaryCalculator(">>>=");
	}
	
    @Override
    public Object calculate(Object value1, Object value2) {
        if (value1 instanceof Integer && value2 instanceof Integer) {
            return (Integer) value1 >>> (Integer) value2;
        } else {
            throw new SyntaxInterpreterException("Operator >>> not supported for types: " +
                    getType(value1) + " and " + getType(value2));
        }
    }
}
