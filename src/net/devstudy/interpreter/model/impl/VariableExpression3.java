package net.devstudy.interpreter.model.impl;

import net.devstudy.interpreter.component.VariableStorage;
import net.devstudy.interpreter.component.impl.VariableStorageHelper;
import net.devstudy.interpreter.exception.RuntimeInterpreterException;
import net.devstudy.interpreter.model.Expression;

import java.util.function.BiFunction;

public class VariableExpression3 implements Expression {
    private final String variableName;

    public VariableExpression3(String variableName) {
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

    private Object executeIfVariableDefined(BiFunction<VariableStorage, Object, Object> function, Object newValue) {
        VariableStorage variableStorage = VariableStorageHelper.getVariableStorage();
        if (variableStorage.isDefined(variableName)) {
            return function.apply(variableStorage, newValue);
        } else {
            throw new RuntimeInterpreterException("Variable '" + variableName + "' not defined");
        }
    }
}
