package net.devstudy.interpreter.component.operationinterpreter;

import net.devstudy.interpreter.component.OperationInterpeter;
import net.devstudy.interpreter.component.VariableStorage;
import net.devstudy.interpreter.component.VariableVerifier;
import net.devstudy.interpreter.component.impl.VariableStorageHelper;
import net.devstudy.interpreter.exception.RuntimeInterpreterException;
import net.devstudy.interpreter.exception.SyntaxInterpreterException;
import net.devstudy.interpreter.model.Operation;

import static net.devstudy.interpreter.component.KeyWords.OUT;

public class OutOperationInterpeter implements OperationInterpeter {
	
	private final VariableVerifier variableVerifier;
	
	public OutOperationInterpeter(VariableVerifier variableVerifier) {
        this.variableVerifier = variableVerifier;
    }
	
    @Override
    public boolean isSupport(Operation operation) {
        return operation.getTokenCount() > 0 &&
                OUT.equals(operation.getToken(0));
    }

    @Override
    public void interpreter(Operation operation) {
    	validateSyntax(operation);
        String variableName = operation.getToken(1);
        variableVerifier.validate(variableName);
        VariableStorage variableStorage = VariableStorageHelper.getVariableStorage();
        if (!variableStorage.isDefined(variableName)) {
            throw new RuntimeInterpreterException("Variable '" + variableName + "' is not defined");
        }
        System.out.println(variableStorage.getVariable(variableName));
    }
    
    private void validateSyntax(Operation operation) {
        if (operation.getTokenCount() == 1) {
            throw new SyntaxInterpreterException("Missing variable name");
        }
    }
}
