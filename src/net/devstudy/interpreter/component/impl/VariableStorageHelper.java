package net.devstudy.interpreter.component.impl;

import net.devstudy.interpreter.component.VariableStorage;

public class VariableStorageHelper {

    private static VariableStorage variableStorage = new VariableStorageImpl();

    public static VariableStorage getVariableStorage() {
        return variableStorage;
    }
}
