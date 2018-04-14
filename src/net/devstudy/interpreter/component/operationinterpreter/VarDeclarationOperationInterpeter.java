package net.devstudy.interpreter.component.operationinterpreter;

import net.devstudy.interpreter.component.ExpressionResolver;
import net.devstudy.interpreter.component.OperationInterpeter;
import net.devstudy.interpreter.component.VariableStorage;
import net.devstudy.interpreter.component.VariableVerifier;
import net.devstudy.interpreter.component.impl.VariableStorageHelper;
import net.devstudy.interpreter.exception.RuntimeInterpreterException;
import net.devstudy.interpreter.exception.SyntaxInterpreterException;
import net.devstudy.interpreter.model.Expression;
import net.devstudy.interpreter.model.Operation;

import java.util.List;

import static net.devstudy.interpreter.component.KeyWords.VAR;

public class VarDeclarationOperationInterpeter extends AbstractOperationInterpeter implements OperationInterpeter {

    public VarDeclarationOperationInterpeter(VariableVerifier variableVerifier,
                                             ExpressionResolver expressionResolver) {
        super(variableVerifier, expressionResolver);
    }

    @Override
    public boolean isSupport(Operation operation) {
        return operation.getTokenCount() > 0 &&
                VAR.equals(operation.getToken(0));
    }

    @Override
    public void interpreter(Operation operation) {
        validateSyntax(operation);
        String varName = operation.getToken(1);
        variableVerifier.validate(varName);
        VariableStorage variableStorage = VariableStorageHelper.getVariableStorage();
        if (variableStorage.isDefined(varName)) {
            throw new RuntimeInterpreterException("Variable '" + varName + "' already defined");
        }
        Object value = getVariableValue(operation);
        variableStorage.putVariable(varName, value);
    }

    private Object getVariableValue(Operation operation) {
        if (operation.getTokenCount() == 2) {
            return null;
        } else {
            List<String> tokens = operation.getSubTokens(3);
            Expression expression = expressionResolver.resolve(tokens);
            return expression.getValue();
        }
    }

    private void validateSyntax(Operation operation) {
        if (operation.getTokenCount() == 1) {
            throw new SyntaxInterpreterException("Missing variable name");
        }
        if (operation.getTokenCount() == 3) {
            throw new SyntaxInterpreterException("Missing variable value");
        }
    }
}
