package net.devstudy.interpreter.component.impl;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.devstudy.interpreter.component.BinaryCalculator;
import net.devstudy.interpreter.component.Config;
import net.devstudy.interpreter.component.ContextInterpeter;
import net.devstudy.interpreter.component.ExpressionBuilder;
import net.devstudy.interpreter.component.ExpressionResolver;
import net.devstudy.interpreter.component.Interpreter;
import net.devstudy.interpreter.component.OperationInterpeter;
import net.devstudy.interpreter.component.OperationTreeBuilder;
import net.devstudy.interpreter.component.SignificantLineVerifier;
import net.devstudy.interpreter.component.SourceLineReader;
import net.devstudy.interpreter.component.TokenParser;
import net.devstudy.interpreter.component.VariableVerifier;
import net.devstudy.interpreter.component.calculator.AndBinaryCalculator;
import net.devstudy.interpreter.component.calculator.BitAndBinaryCalculator;
import net.devstudy.interpreter.component.calculator.BitLeftShiftBinaryCalculator;
import net.devstudy.interpreter.component.calculator.BitNoSignRightShiftBinaryCalculator;
import net.devstudy.interpreter.component.calculator.BitOrBinaryCalculator;
import net.devstudy.interpreter.component.calculator.BitRightShiftBinaryCalculator;
import net.devstudy.interpreter.component.calculator.BitXorBinaryCalculator;
import net.devstudy.interpreter.component.calculator.DivideBinaryCalculator;
import net.devstudy.interpreter.component.calculator.EqualsBinaryCalculator;
import net.devstudy.interpreter.component.calculator.LessBinaryCalculator;
import net.devstudy.interpreter.component.calculator.LessOrEqualsBinaryCalculator;
import net.devstudy.interpreter.component.calculator.MinusBinaryCalculator;
import net.devstudy.interpreter.component.calculator.MinusEqualsBinaryCalculator;
import net.devstudy.interpreter.component.calculator.MoreBinaryCalculator;
import net.devstudy.interpreter.component.calculator.MoreOrEqualsBinaryCalculator;
import net.devstudy.interpreter.component.calculator.MultiplicationBinaryCalculator;
import net.devstudy.interpreter.component.calculator.NotEqualsBinaryCalculator;
import net.devstudy.interpreter.component.calculator.OrBinaryCalculator;
import net.devstudy.interpreter.component.calculator.PlusBinaryCalculator;
import net.devstudy.interpreter.component.calculator.RemainderBinaryCalculator;
import net.devstudy.interpreter.component.expressionbuilder.BinaryExpressionBuilder;
import net.devstudy.interpreter.component.expressionbuilder.SimpleExpressionBuilder;
import net.devstudy.interpreter.component.operationinterpreter.InputOperationInterpeter;
import net.devstudy.interpreter.component.operationinterpreter.OutOperationInterpeter;
import net.devstudy.interpreter.component.operationinterpreter.VarDeclarationOperationInterpeter;
import net.devstudy.interpreter.utils.DataUtils;

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
                put("*", new MultiplicationBinaryCalculator());
                put("/", new DivideBinaryCalculator());
                put("%", new RemainderBinaryCalculator());
                put("==", new EqualsBinaryCalculator());
                put("!=", new NotEqualsBinaryCalculator());
                put(">", new MoreBinaryCalculator());
                put("<", new LessBinaryCalculator());
                put(">=", new MoreOrEqualsBinaryCalculator());
                put("<=", new LessOrEqualsBinaryCalculator());
                put("||", new OrBinaryCalculator());
                put("&&", new AndBinaryCalculator());
                put("|", new BitOrBinaryCalculator());
                put("&", new BitAndBinaryCalculator());
                put("^", new BitXorBinaryCalculator());
                put("<<", new BitLeftShiftBinaryCalculator());
                put(">>", new BitRightShiftBinaryCalculator());
                put(">>>", new BitNoSignRightShiftBinaryCalculator());
                put("-=", new MinusEqualsBinaryCalculator());
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
