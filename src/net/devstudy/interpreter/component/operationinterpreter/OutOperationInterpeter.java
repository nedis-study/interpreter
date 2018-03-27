package net.devstudy.interpreter.component.operationinterpreter;

import net.devstudy.interpreter.component.OperationInterpeter;
import net.devstudy.interpreter.component.VariableStorage;
import net.devstudy.interpreter.component.impl.VariableStorageHelper;
import net.devstudy.interpreter.model.Operation;

import static net.devstudy.interpreter.component.KeyWords.OUT;

public class OutOperationInterpeter implements OperationInterpeter {
    @Override
    public boolean isSupport(Operation operation) {
        return operation.getTokenCount() > 0 &&
                OUT.equals(operation.getToken(0));
    }

    @Override
    public void interpreter(Operation operation) {
        String variableName = operation.getToken(1);
        VariableStorage variableStorage = VariableStorageHelper.getVariableStorage();
        System.out.println(variableStorage.getVariable(variableName));
    }
}
