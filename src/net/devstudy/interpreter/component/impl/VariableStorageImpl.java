package net.devstudy.interpreter.component.impl;

import net.devstudy.interpreter.component.VariableStorage;

public class VariableStorageImpl implements VariableStorage {
    private String variableName;
    private Object variableValue;

    @Override
    public void putVariable(String variableName, Object variableValue) {
        this.variableName = variableName;
        this.variableValue = variableValue;
    }

    @Override
    public Object getVariable(String variableName) {
        return this.variableName.equals(variableName) ? variableValue : null;
    }

    @Override
    public boolean isDefined(String variableName) {
        return this.variableName.equals(variableName);
    }
}
