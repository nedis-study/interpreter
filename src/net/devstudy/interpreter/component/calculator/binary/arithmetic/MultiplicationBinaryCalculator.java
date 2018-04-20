package net.devstudy.interpreter.component.calculator.binary.arithmetic;

import net.devstudy.interpreter.component.BinaryCalculator;
import net.devstudy.interpreter.component.calculator.AbstractCalculator;
import net.devstudy.interpreter.exception.SyntaxInterpreterException;

import static net.devstudy.interpreter.utils.TypeUtils.getType;

public class MultiplicationBinaryCalculator extends AbstractCalculator implements BinaryCalculator {
	private MultiplicationBinaryCalculator(String operator) {
        super(operator);
    }

    public static MultiplicationBinaryCalculator createArithmenticMultiplicationBinaryCalculator(){
        return new MultiplicationBinaryCalculator("*");
    }

    public static MultiplicationBinaryCalculator createAssignmentMultiplicationBinaryCalculator(){
        return new MultiplicationBinaryCalculator("*=");
    }
	
    @Override
    public Object calculate(Object value1, Object value2) {
        if (value1 instanceof Integer && value2 instanceof Integer) {
            return (Integer) value1 * (Integer) value2;
        } else if (value1 instanceof Number && value2 instanceof Number) {
            return ((Number) value1).doubleValue() * ((Number) value2).doubleValue();
        } else {
            throw new SyntaxInterpreterException("Operator * not supported for types: " +
                    getType(value1) + " and " + getType(value2));
        }
    }
}
