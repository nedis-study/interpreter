package net.devstudy.interpreter.model.impl;

import net.devstudy.interpreter.component.VariableStorage;
import net.devstudy.interpreter.component.impl.VariableStorageHelper;
import net.devstudy.interpreter.exception.RuntimeInterpreterException;
import net.devstudy.interpreter.model.Expression;

public class VariableExpression1 implements Expression {
    private final String variableName;

    public VariableExpression1(String variableName) {
        this.variableName = variableName;
    }

    @Override
    public Object getValue() {
        return executeIfVariableDefined(new Action() {
            @Override
            public Object execute(VariableStorage variableStorage) {
                return variableStorage.getVariable(variableName);
            }
        });
    }

    public void setValue(Object newValue) {
        executeIfVariableDefined(new Action() {
            @Override
            public Object execute(VariableStorage variableStorage) {
                variableStorage.putVariable(variableName, newValue);
                return null;
            }
        });
    }

    private Object executeIfVariableDefined(Action action) {
        VariableStorage variableStorage = VariableStorageHelper.getVariableStorage();
        if (variableStorage.isDefined(variableName)) {
            return action.execute(variableStorage);
        } else {
            throw new RuntimeInterpreterException("Variable '" + variableName + "' not defined");
        }
    }

    private interface Action {
        Object execute(VariableStorage variableStorage);
    }
}
