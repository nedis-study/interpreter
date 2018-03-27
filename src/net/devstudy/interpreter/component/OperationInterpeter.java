package net.devstudy.interpreter.component;

import net.devstudy.interpreter.model.Operation;

public interface OperationInterpeter {

    boolean isSupport(Operation operation);

    void interpreter(Operation operation);
}
