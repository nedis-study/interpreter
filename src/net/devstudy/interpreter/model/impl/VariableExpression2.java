package net.devstudy.interpreter.model.impl;

import net.devstudy.interpreter.component.VariableStorage;
import net.devstudy.interpreter.component.impl.VariableStorageHelper;
import net.devstudy.interpreter.exception.RuntimeInterpreterException;
import net.devstudy.interpreter.model.Expression;

public class VariableExpression2 implements Expression {
    private final String variableName;

    public VariableExpression2(String variableName) {
        this.variableName = variableName;
    }

    @Override
    public Object getValue() {
        return executeIfVariableDefined(this::getVariableValue, null);
    }

    private Object getVariableValue(VariableStorage variableStorage, Object newValue) {
        return variableStorage.getVariable(variableName);
    }

    public void setValue(Object newValue) {
        executeIfVariableDefined(this::setVariableValue, newValue);
    }

    private Object setVariableValue(VariableStorage variableStorage, Object newValue) {
        variableStorage.putVariable(variableName, newValue);
        return null;
    }

    private Object executeIfVariableDefined(Action action, Object newValue) {
        VariableStorage variableStorage = VariableStorageHelper.getVariableStorage();
        if (variableStorage.isDefined(variableName)) {
            return action.execute(variableStorage, newValue);
        } else {
            throw new RuntimeInterpreterException("Variable '" + variableName + "' not defined");
        }
    }

    private interface Action {
        Object execute(VariableStorage variableStorage, Object newValue);
    }
}
