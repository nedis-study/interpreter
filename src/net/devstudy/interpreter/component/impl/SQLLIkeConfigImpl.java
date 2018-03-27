package net.devstudy.interpreter.component.impl;

import net.devstudy.interpreter.component.*;
import net.devstudy.interpreter.component.operationinterpreter.HelloOperationInterpeter;
import net.devstudy.interpreter.component.operationinterpreter.OutOperationInterpeter;
import net.devstudy.interpreter.component.operationinterpreter.VarDeclarationOperationInterpeter;

public class SQLLIkeConfigImpl implements Config {
    private final SignificantLineVerifier significantLineVerifier =
            new SQLLikeSignificantLineVerifier();
    private final SourceLineReader sourceLineReader =
            new SourceLineReaderImpl(significantLineVerifier);
    private final TokenParser tokenParser =
            new TokenParserImpl();
    private final OperationTreeBuilder operationTreeBuilder =
            new OperationTreeBuilderImpl(tokenParser);
    private final OperationInterpeter[] operationInterpeters = {
            new VarDeclarationOperationInterpeter(),
            new OutOperationInterpeter(),
            new HelloOperationInterpeter()
    };
    private final ContextInterpeter contextInterpeter =
            new ContextInterpeterImpl(operationInterpeters);
    private final Interpreter interpreter =
            new InterpreterImpl(sourceLineReader, operationTreeBuilder, contextInterpeter);

    @Override
    public Interpreter getCurrentInterpreter() {
        return interpreter;
    }
}
