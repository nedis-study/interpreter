package net.devstudy.interpreter.component.calculator.binary.predicate;

import net.devstudy.interpreter.component.BinaryCalculator;
import net.devstudy.interpreter.exception.SyntaxInterpreterException;

import static net.devstudy.interpreter.utils.TypeUtils.getType;

public class NotEqualsBinaryCalculator implements BinaryCalculator {

    private EqualsBinaryCalculator calculator = new EqualsBinaryCalculator();

    @Override
    public Object calculate(Object value1, Object value2) {
        try {
            return !(Boolean)calculator.calculate(value1, value2);
        } catch (SyntaxInterpreterException e){
            throw new SyntaxInterpreterException("Operator != not supported for types: " +
                    getType(value1) + " and " + getType(value2));
        }
    }
}
