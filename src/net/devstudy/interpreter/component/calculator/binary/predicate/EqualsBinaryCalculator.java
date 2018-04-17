package net.devstudy.interpreter.component.calculator.binary.predicate;

import net.devstudy.interpreter.component.BinaryCalculator;
import net.devstudy.interpreter.component.calculator.AbstractCalculator;
import net.devstudy.interpreter.exception.SyntaxInterpreterException;

import static net.devstudy.interpreter.utils.TypeUtils.getType;

public class EqualsBinaryCalculator extends AbstractCalculator implements BinaryCalculator {

    public EqualsBinaryCalculator() {
        super("==");
    }

    @Override
    public Object calculate(Object value1, Object value2) {
        if (value1 instanceof Integer && value2 instanceof Integer) {
            return value1.equals(value2);
        } else if (value1 instanceof Number && value2 instanceof Number) {
            return 0 == Double.compare(
                    ((Number) value1).doubleValue(),
                    ((Number) value2).doubleValue()
            );
        } else {
            if (value1 != null && value2 != null) {
                if (value1.getClass() == value2.getClass()) {
                    return value1.equals(value2);
                } else {
                    throw new SyntaxInterpreterException("Operator " + getOperator() + " not supported for types: " +
                            getType(value1) + " and " + getType(value2));
                }
            } else {
                return value1 == null && value2 == null;
            }
        }
    }
}
