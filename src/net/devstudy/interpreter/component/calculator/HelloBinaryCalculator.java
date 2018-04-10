package net.devstudy.interpreter.component.calculator;

import net.devstudy.interpreter.component.BinaryCalculator;

public class HelloBinaryCalculator implements BinaryCalculator {
    @Override
    public Object calculate(Object value1, Object value2) {
        return "hello";
    }
}
