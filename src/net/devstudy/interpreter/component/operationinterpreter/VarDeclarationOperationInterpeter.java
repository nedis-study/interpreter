package net.devstudy.interpreter.component.operationinterpreter;

import net.devstudy.interpreter.component.OperationInterpeter;
import net.devstudy.interpreter.component.VariableStorage;
import net.devstudy.interpreter.component.impl.VariableStorageHelper;
import net.devstudy.interpreter.model.Operation;

import static net.devstudy.interpreter.component.KeyWords.VAR;

public class VarDeclarationOperationInterpeter implements OperationInterpeter {
    @Override
    public boolean isSupport(Operation operation) {
        return operation.getTokenCount() > 0 &&
                VAR.equals(operation.getToken(0));
    }

    @Override
    public void interpreter(Operation operation) {
        String varName = operation.getToken(1);
        String varValue = operation.getToken(3);
        VariableStorage variableStorage = VariableStorageHelper.getVariableStorage();
        variableStorage.putVariable(varName, varValue);
    }
}
