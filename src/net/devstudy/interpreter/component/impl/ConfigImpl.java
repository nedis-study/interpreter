package net.devstudy.interpreter.component.impl;

import net.devstudy.interpreter.component.*;
import net.devstudy.interpreter.component.calculator.HelloBinaryCalculator;
import net.devstudy.interpreter.component.calculator.MinusBinaryCalculator;
import net.devstudy.interpreter.component.expressionbuilder.BinaryExpressionBuilder;
import net.devstudy.interpreter.component.expressionbuilder.SimpleExpressionBuilder;
import net.devstudy.interpreter.component.operationinterpreter.InputOperationInterpeter;
import net.devstudy.interpreter.component.operationinterpreter.OutOperationInterpeter;
import net.devstudy.interpreter.component.operationinterpreter.VarDeclarationOperationInterpeter;

import java.util.HashMap;
import java.util.Map;

public class ConfigImpl implements Config {
    private final VariableVerifier variableVerifier = createVariableVerifier();

    protected VariableVerifier createVariableVerifier() {
        return new VariableVerifierImpl();
    }

    private final Map<String, BinaryCalculator> binaryCalculatorMap = createBinaryCalculatorMap();

    protected Map<String, BinaryCalculator> createBinaryCalculatorMap() {
        return new HashMap<String, BinaryCalculator>() {
            {
                put("-", new MinusBinaryCalculator());
                put("$", new HelloBinaryCalculator());
            }
        };
    }

    private final ExpressionBuilder[] expressionBuilders = createExpressionBuilders(variableVerifier);

    protected ExpressionBuilder[] createExpressionBuilders(VariableVerifier variableVerifier) {
        return new ExpressionBuilder[]{
                new SimpleExpressionBuilder(variableVerifier),
                new BinaryExpressionBuilder(new SimpleExpressionBuilder(variableVerifier), binaryCalculatorMap)
        };
    }

    private final ExpressionResolver expressionResolver = createExpressionResolver(expressionBuilders);

    protected ExpressionResolver createExpressionResolver(ExpressionBuilder[] expressionBuilders) {
        return new ExpressionResolverImpl(expressionBuilders);
    }

    private final SignificantLineVerifier significantLineVerifier = createSignificantVerifier();

    protected SignificantLineVerifier createSignificantVerifier() {
        return new SignificantLineVerifierImpl();
    }

    private final SourceLineReader sourceLineReader = createSourceLineReader(significantLineVerifier);

    protected SourceLineReader createSourceLineReader(SignificantLineVerifier significantLineVerifier) {
        return new SourceLineReaderImpl(significantLineVerifier);
    }

    private final TokenParser tokenParser = createTokenParser();

    protected TokenParser createTokenParser() {
        return new TokenParserImpl();
    }

    private final OperationTreeBuilder operationTreeBuilder = createOperationTreeBuilder(tokenParser);

    protected OperationTreeBuilder createOperationTreeBuilder(TokenParser tokenParser) {
        return new OperationTreeBuilderImpl(tokenParser);
    }

    private final OperationInterpeter[] operationInterpeters = createOperationInterperters(variableVerifier, expressionResolver);

    protected OperationInterpeter[] createOperationInterperters(
            VariableVerifier variableVerifier,
            ExpressionResolver expressionResolver) {
        return new OperationInterpeter[]{
                new VarDeclarationOperationInterpeter(variableVerifier, expressionResolver),
                new OutOperationInterpeter(variableVerifier, expressionResolver),
                new InputOperationInterpeter(variableVerifier, expressionResolver)
        };
    }

    private final ContextInterpeter contextInterpeter = createContextInterpreter(operationInterpeters);

    protected ContextInterpeter createContextInterpreter(
            OperationInterpeter[] operationInterpeters) {
        return new ContextInterpeterImpl(operationInterpeters);
    }

    private final Interpreter interpreter = createInterpreter(sourceLineReader, operationTreeBuilder, contextInterpeter);

    protected Interpreter createInterpreter(SourceLineReader sourceLineReader,
                                            OperationTreeBuilder operationTreeBuilder,
                                            ContextInterpeter contextInterpeter) {
        return new InterpreterImpl(sourceLineReader, operationTreeBuilder, contextInterpeter);
    }

    @Override
    public Interpreter getCurrentInterpreter() {
        return interpreter;
    }
}
