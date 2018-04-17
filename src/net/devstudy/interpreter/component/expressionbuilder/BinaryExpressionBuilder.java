package net.devstudy.interpreter.component.expressionbuilder;

import net.devstudy.interpreter.component.BinaryCalculator;
import net.devstudy.interpreter.component.ExpressionBuilder;
import net.devstudy.interpreter.exception.SyntaxInterpreterException;
import net.devstudy.interpreter.model.Expression;
import net.devstudy.interpreter.model.impl.BinaryExpression;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class BinaryExpressionBuilder implements ExpressionBuilder {

    private final SimpleExpressionBuilder simpleExpressionBuilder;
    private final Map<String, BinaryCalculator> binaryCalculatorMap;

    public BinaryExpressionBuilder(SimpleExpressionBuilder simpleExpressionBuilder,
                                   Map<String, BinaryCalculator> binaryCalculatorMap) {
        this.simpleExpressionBuilder = simpleExpressionBuilder;
        this.binaryCalculatorMap = binaryCalculatorMap;
    }

    @Override
    public boolean isSupport(List<String> tokens) {
        return tokens.size() == 3 && binaryCalculatorMap.containsKey(tokens.get(1));
    }

    @Override
    public Expression build(List<String> tokens) {
        String operator = tokens.get(1);
        Expression expression1 = simpleExpressionBuilder.build(Collections.singletonList(tokens.get(0)));
        Expression expression2 = simpleExpressionBuilder.build(Collections.singletonList(tokens.get(2)));
        BinaryCalculator binaryCalculator = binaryCalculatorMap.get(operator);
        return createBinaryExpression(expression1, expression2, binaryCalculator);
    }

    protected BinaryExpression createBinaryExpression(Expression expression1, Expression expression2, BinaryCalculator binaryCalculator) {
        return new BinaryExpression(expression1, expression2, binaryCalculator);
    }
}
