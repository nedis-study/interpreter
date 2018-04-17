package net.devstudy.interpreter.component.calculator;

public abstract class AbstractCalculator {

    private final String operator;

    public AbstractCalculator(String operator) {
        this.operator = operator;
    }

    public String getOperator() {
        return operator;
    }
}
