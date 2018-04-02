package net.devstudy.interpreter.component.impl;

import java.util.HashMap;
import java.util.Map;

import net.devstudy.interpreter.component.VariableStorage;

public class VariableStorageImpl implements VariableStorage {
	private Map<String, Object> variables = new HashMap<>();

	@Override
	public void putVariable(String variableName, Object variableValue) {
		this.variables.put(variableName, variableValue);
	}

	@Override
	public Object getVariable(String variableName) {
		return this.variables.containsKey(variableName) ? this.variables.get(variableName) : null;
	}

	@Override
	public boolean isDefined(String variableName) {
		return this.variables.containsKey(variableName);
	}
}
