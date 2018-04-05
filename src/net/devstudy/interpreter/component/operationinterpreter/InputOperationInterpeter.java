package net.devstudy.interpreter.component.operationinterpreter;


import net.devstudy.interpreter.component.OperationInterpeter;
import net.devstudy.interpreter.component.VariableStorage;
import net.devstudy.interpreter.component.VariableVerifier;
import net.devstudy.interpreter.component.impl.VariableStorageHelper;
import net.devstudy.interpreter.exception.RuntimeInterpreterException;
import net.devstudy.interpreter.exception.SyntaxInterpreterException;
import net.devstudy.interpreter.model.Operation;

import java.util.Scanner;

import static net.devstudy.interpreter.component.KeyWords.INPUT;

public class InputOperationInterpeter implements OperationInterpeter {
	
	private final VariableVerifier variableVerifier;

    public InputOperationInterpeter(VariableVerifier variableVerifier) {
        this.variableVerifier = variableVerifier;
    }

    @Override
    public boolean isSupport(Operation operation) {
        return operation.getTokenCount() > 0 &&
                INPUT.equals(operation.getToken(0));
    }

    @Override
    public void interpreter(Operation operation) {
    	validateSyntax(operation);
        String varName = operation.getToken(1);
        variableVerifier.validate(varName);
        VariableStorage variableStorage = VariableStorageHelper.getVariableStorage();
        if (!variableStorage.isDefined(varName)) {
            throw new RuntimeInterpreterException("Variable '" + varName + "' is not defined");
        }
        String varValue = new Scanner(System.in).nextLine();
        VariableStorage variableStorageInput = VariableStorageHelper.getVariableStorage();
        variableStorageInput.putVariable(varName, varValue);
    }
    
    private void validateSyntax(Operation operation) {
        if (operation.getTokenCount() == 1) {
            throw new SyntaxInterpreterException("Missing variable name");
        }
    }
}
