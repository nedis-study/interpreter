package net.devstudy.interpreter.component.expressionbuilder;

import net.devstudy.interpreter.component.ExpressionBuilder;
import net.devstudy.interpreter.component.UnaryCalculator;
import net.devstudy.interpreter.model.Expression;
import net.devstudy.interpreter.model.impl.UnaryExpression;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class UnaryExpressionBuilder implements ExpressionBuilder {

    private final SimpleExpressionBuilder simpleExpressionBuilder;
    private final Map<String, UnaryCalculator> unaryCalculatorMap;

    public UnaryExpressionBuilder(SimpleExpressionBuilder simpleExpressionBuilder,
                                  Map<String, UnaryCalculator> unaryCalculatorMap) {
        this.simpleExpressionBuilder = simpleExpressionBuilder;
        this.unaryCalculatorMap = unaryCalculatorMap;
    }


    @Override
    public boolean isSupport(List<String> tokens) {
        return tokens.size() == 2 && unaryCalculatorMap.containsKey(tokens.get(0));
    }

    @Override
    public Expression build(List<String> tokens) {
        String operator = tokens.get(0);
        Expression expression1 = simpleExpressionBuilder.build(Collections.singletonList(tokens.get(1)));
        UnaryCalculator unaryCalculator = unaryCalculatorMap.get(operator);
        return new UnaryExpression(expression1, unaryCalculator);
    }
}
