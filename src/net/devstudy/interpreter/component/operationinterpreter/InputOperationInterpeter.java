package net.devstudy.interpreter.component.operationinterpreter;


import net.devstudy.interpreter.component.ExpressionResolver;
import net.devstudy.interpreter.component.OperationInterpeter;
import net.devstudy.interpreter.component.VariableStorage;
import net.devstudy.interpreter.component.VariableVerifier;
import net.devstudy.interpreter.component.impl.VariableStorageHelper;
import net.devstudy.interpreter.model.Operation;

import java.util.Scanner;

import static net.devstudy.interpreter.component.KeyWords.INPUT;

public class InputOperationInterpeter extends AbstractOperationInterpeter implements OperationInterpeter {

    public InputOperationInterpeter(VariableVerifier variableVerifier,
                                    ExpressionResolver expressionResolver) {
        super(variableVerifier, expressionResolver);
    }

    @Override
    public boolean isSupport(Operation operation) {
        return operation.getTokenCount() > 0 &&
                INPUT.equals(operation.getToken(0));
    }

    @Override
    public void interpreter(Operation operation) {
        String varName = operation.getToken(1);
        String varValue = new Scanner(System.in).nextLine();
        VariableStorage variableStorageInput = VariableStorageHelper.getVariableStorage();
        variableStorageInput.putVariable(varName, varValue);
    }
}
