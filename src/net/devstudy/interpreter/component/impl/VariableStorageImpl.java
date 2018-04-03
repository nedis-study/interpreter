package net.devstudy.interpreter.component.impl;

import net.devstudy.interpreter.component.VariableStorage;

import java.util.HashMap;
import java.util.Map;

public class VariableStorageImpl implements VariableStorage {
    private Map<String, Object> variables = new HashMap<>();

    @Override
    public void putVariable(String variableName, Object variableValue) {
        this.variables.put(variableName, variableValue);
    }

    @Override
    public Object getVariable(String variableName) {
        return this.variables.get(variableName);
    }

    @Override
    public boolean isDefined(String variableName) {
        return this.variables.containsKey(variableName);
    }
}
