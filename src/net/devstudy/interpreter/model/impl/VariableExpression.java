package net.devstudy.interpreter.model.impl;

import net.devstudy.interpreter.component.VariableStorage;
import net.devstudy.interpreter.component.impl.VariableStorageHelper;
import net.devstudy.interpreter.exception.RuntimeInterpreterException;
import net.devstudy.interpreter.model.Expression;

public class VariableExpression implements Expression {
    private final String variableName;

    public VariableExpression(String variableName) {
        this.variableName = variableName;
    }

    @Override
    public Object getValue() {
        VariableStorage variableStorage = VariableStorageHelper.getVariableStorage();
        if (variableStorage.isDefined(variableName)) {
            return variableStorage.getVariable(variableName);
        } else {
            throw new RuntimeInterpreterException("Variable '" + variableName + "' not defined");
        }
    }

    public void setValue(Object newValue) {
        VariableStorage variableStorage = VariableStorageHelper.getVariableStorage();
        if (variableStorage.isDefined(variableName)) {
            variableStorage.putVariable(variableName, newValue);
        } else {
            throw new RuntimeInterpreterException("Variable '" + variableName + "' not defined");
        }
    }
}
