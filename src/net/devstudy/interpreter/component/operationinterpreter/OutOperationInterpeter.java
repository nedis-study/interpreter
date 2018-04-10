package net.devstudy.interpreter.component.operationinterpreter;

import net.devstudy.interpreter.component.ExpressionResolver;
import net.devstudy.interpreter.component.OperationInterpeter;
import net.devstudy.interpreter.component.VariableVerifier;
import net.devstudy.interpreter.exception.SyntaxInterpreterException;
import net.devstudy.interpreter.model.Expression;
import net.devstudy.interpreter.model.Operation;

import static net.devstudy.interpreter.component.KeyWords.OUT;

public class OutOperationInterpeter extends AbstractOperationInterpeter implements OperationInterpeter {
    public OutOperationInterpeter(VariableVerifier variableVerifier,
                                  ExpressionResolver expressionResolver) {
        super(variableVerifier, expressionResolver);
    }

    @Override
    public boolean isSupport(Operation operation) {
        return operation.getTokenCount() > 0 &&
                OUT.equals(operation.getToken(0));
    }

    @Override
    public void interpreter(Operation operation) {
        validateSyntax(operation);
        String[] tokens = operation.getSubTokens(1);
        Expression expression = expressionResolver.resolve(tokens);
        System.out.println(expression.getValue());
    }

    private void validateSyntax(Operation operation) {
        if (operation.getTokenCount() == 1) {
            throw new SyntaxInterpreterException("Missing expression");
        }
    }
}
