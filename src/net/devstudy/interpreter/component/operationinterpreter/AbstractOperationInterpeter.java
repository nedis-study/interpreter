package net.devstudy.interpreter.component.operationinterpreter;

import net.devstudy.interpreter.component.ExpressionResolver;
import net.devstudy.interpreter.component.OperationInterpeter;
import net.devstudy.interpreter.component.VariableVerifier;

public abstract class AbstractOperationInterpeter implements OperationInterpeter{

    protected final VariableVerifier variableVerifier;

    protected final ExpressionResolver expressionResolver;

    public AbstractOperationInterpeter(VariableVerifier variableVerifier,
                                       ExpressionResolver expressionResolver) {
        this.variableVerifier = variableVerifier;
        this.expressionResolver = expressionResolver;
    }
}
