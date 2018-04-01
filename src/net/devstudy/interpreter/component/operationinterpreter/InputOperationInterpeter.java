package net.devstudy.interpreter.component.operationinterpreter;

import net.devstudy.interpreter.component.OperationInterpeter;
import net.devstudy.interpreter.component.VariableStorage;
import net.devstudy.interpreter.component.impl.VariableStorageHelper;
import net.devstudy.interpreter.model.Operation;

import static net.devstudy.interpreter.component.KeyWords.INPUT;

import java.util.Scanner;

public class InputOperationInterpeter implements OperationInterpeter{

	@Override
	public boolean isSupport(Operation operation) {
		return operation.getTokenCount() > 0 &&
                INPUT.equals(operation.getToken(0));
	}

	@Override
	public void interpreter(Operation operation) {
		
		String varName = operation.getToken(1);
		System.out.println("Input: " + varName + " = ");
        String varValue = new Scanner(System.in).nextLine();
        VariableStorage variableStorage = VariableStorageHelper.getVariableStorage();
        variableStorage.putVariable(varName, varValue);
		
	}

}
