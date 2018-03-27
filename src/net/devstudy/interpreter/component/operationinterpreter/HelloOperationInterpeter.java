package net.devstudy.interpreter.component.operationinterpreter;

import net.devstudy.interpreter.component.OperationInterpeter;
import net.devstudy.interpreter.model.Operation;

public class HelloOperationInterpeter implements OperationInterpeter {
    @Override
    public boolean isSupport(Operation operation) {
        return operation.getTokenCount() == 1 &&
                "hello".equals(operation.getToken(0));
    }

    @Override
    public void interpreter(Operation operation) {
        System.out.println("bye");
    }
}
