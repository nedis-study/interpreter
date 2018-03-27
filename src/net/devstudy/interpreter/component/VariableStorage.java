package net.devstudy.interpreter.component;

public interface VariableStorage {

    void putVariable(String variableName, Object value);

    Object getVariable(String variableName);

    boolean isDefined(String variableName);
}
