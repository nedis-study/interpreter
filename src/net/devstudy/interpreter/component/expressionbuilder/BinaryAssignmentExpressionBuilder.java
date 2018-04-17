package net.devstudy.interpreter.component.expressionbuilder;

import net.devstudy.interpreter.component.BinaryCalculator;
import net.devstudy.interpreter.model.Expression;
import net.devstudy.interpreter.model.impl.BinaryAssignmentExpression;
import net.devstudy.interpreter.model.impl.BinaryExpression;

import java.util.Map;

public class BinaryAssignmentExpressionBuilder extends BinaryExpressionBuilder {
    public BinaryAssignmentExpressionBuilder(SimpleExpressionBuilder simpleExpressionBuilder, Map<String, BinaryCalculator> binaryCalculatorMap) {
        super(simpleExpressionBuilder, binaryCalculatorMap);
    }

    @Override
    protected BinaryExpression createBinaryExpression(Expression expression1, Expression expression2, BinaryCalculator binaryCalculator) {
        return new BinaryAssignmentExpression(expression1, expression2, binaryCalculator);
    }
}
