package net.devstudy.interpreter.component.expressionbuilder;

import net.devstudy.interpreter.component.ExpressionBuilder;
import net.devstudy.interpreter.component.VariableVerifier;
import net.devstudy.interpreter.exception.SyntaxInterpreterException;
import net.devstudy.interpreter.model.Expression;
import net.devstudy.interpreter.model.impl.SimpleExpression;
import net.devstudy.interpreter.model.impl.VariableExpression;

import java.util.List;

import static net.devstudy.interpreter.component.KeyWords.*;

public class SimpleExpressionBuilder implements ExpressionBuilder {

    private final VariableVerifier variableVerifier;

    public SimpleExpressionBuilder(VariableVerifier variableVerifier) {
        this.variableVerifier = variableVerifier;
    }

    @Override
    public boolean isSupport(List<String> tokens) {
        return tokens.size() == 1;
    }

    @Override
    public Expression build(List<String> tokens) {
        String token = tokens.get(0);
        if (NULL.equals(token)) {
            return new SimpleExpression(null);
        } else if (TRUE.equals(token) || FALSE.equals(token)) {
            return new SimpleExpression(Boolean.parseBoolean(token));
        } else if (token.startsWith("\"")) {
            return parseStringExpression(token);
        } else {
            try {
                return new SimpleExpression(Integer.parseInt(token));
            } catch (NumberFormatException e) {
                try {
                    return new SimpleExpression(Double.parseDouble(token));
                } catch (NumberFormatException e2) {
                    variableVerifier.validate(token);
                    return new VariableExpression(token);
                }
            }
        }
    }

    private Expression parseStringExpression(String token) {
        if (token.endsWith("\"")) {
            if (token.length() >= 2) {
                return new SimpleExpression(token.substring(1, token.length() - 1));
            } else {
                throw new SyntaxInterpreterException("Invalid string const: missing \"");
            }
        } else {
            throw new SyntaxInterpreterException("Invalid string const: should be end with \"");
        }
    }
}
