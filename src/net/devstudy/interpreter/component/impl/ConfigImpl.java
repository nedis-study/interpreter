package net.devstudy.interpreter.component.impl;

import net.devstudy.interpreter.component.*;
import net.devstudy.interpreter.component.calculator.MinusBinaryCalculator;
import net.devstudy.interpreter.component.calculator.PlusBinaryCalculator;
import net.devstudy.interpreter.component.expressionbuilder.BinaryExpressionBuilder;
import net.devstudy.interpreter.component.expressionbuilder.SimpleExpressionBuilder;
import net.devstudy.interpreter.component.operationinterpreter.InputOperationInterpeter;
import net.devstudy.interpreter.component.operationinterpreter.OutOperationInterpeter;
import net.devstudy.interpreter.component.operationinterpreter.VarDeclarationOperationInterpeter;
import net.devstudy.interpreter.utils.DataUtils;

import java.util.*;

public class ConfigImpl implements Config {
    private final VariableVerifier variableVerifier = createVariableVerifier();

    protected VariableVerifier createVariableVerifier() {
        return new VariableVerifierImpl();
    }

    private final Map<String, BinaryCalculator> binaryCalculatorMap = createBinaryCalculatorMap();

    protected Map<String, BinaryCalculator> createBinaryCalculatorMap() {
        return Collections.unmodifiableMap(new HashMap<String, BinaryCalculator>() {
            {
                put("-", new MinusBinaryCalculator());
                put("+", new PlusBinaryCalculator());
            }
        });
    }

    private final List<ExpressionBuilder> expressionBuilders = createExpressionBuilders(variableVerifier);

    protected List<ExpressionBuilder> createExpressionBuilders(VariableVerifier variableVerifier) {
        return DataUtils.unmodifiableList(
                Arrays.asList(
                        new SimpleExpressionBuilder(variableVerifier),
                        new BinaryExpressionBuilder(new SimpleExpressionBuilder(variableVerifier), binaryCalculatorMap)
                )
        );
    }

    private final ExpressionResolver expressionResolver = createExpressionResolver(expressionBuilders);

    protected ExpressionResolver createExpressionResolver(List<ExpressionBuilder> expressionBuilders) {
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

    private final List<OperationInterpeter> operationInterpeters = createOperationInterperters(variableVerifier, expressionResolver);

    protected List<OperationInterpeter> createOperationInterperters(
            VariableVerifier variableVerifier,
            ExpressionResolver expressionResolver) {
        return DataUtils.unmodifiableList(
                Arrays.asList(
                        new VarDeclarationOperationInterpeter(variableVerifier, expressionResolver),
                        new OutOperationInterpeter(variableVerifier, expressionResolver),
                        new InputOperationInterpeter(variableVerifier, expressionResolver)
                )
        );
    }

    private final ContextInterpeter contextInterpeter = createContextInterpreter(operationInterpeters);

    protected ContextInterpeter createContextInterpreter(List<OperationInterpeter> operationInterpeters) {
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
