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
import net.devstudy.interpreter.component.UnaryCalculator;
import net.devstudy.interpreter.component.VariableVerifier;
import net.devstudy.interpreter.component.calculator.binary.arithmetic.DivideBinaryCalculator;
import net.devstudy.interpreter.component.calculator.binary.arithmetic.MinusBinaryCalculator;
import net.devstudy.interpreter.component.calculator.binary.arithmetic.MultiplicationBinaryCalculator;
import net.devstudy.interpreter.component.calculator.binary.arithmetic.PlusBinaryCalculator;
import net.devstudy.interpreter.component.calculator.binary.arithmetic.RemainderBinaryCalculator;
import net.devstudy.interpreter.component.calculator.binary.bit.BitAndBinaryCalculator;
import net.devstudy.interpreter.component.calculator.binary.bit.BitLeftShiftBinaryCalculator;
import net.devstudy.interpreter.component.calculator.binary.bit.BitNoSignRightShiftBinaryCalculator;
import net.devstudy.interpreter.component.calculator.binary.bit.BitOrBinaryCalculator;
import net.devstudy.interpreter.component.calculator.binary.bit.BitRightShiftBinaryCalculator;
import net.devstudy.interpreter.component.calculator.binary.bit.BitXorBinaryCalculator;
import net.devstudy.interpreter.component.calculator.binary.logic.AndBinaryCalculator;
import net.devstudy.interpreter.component.calculator.binary.logic.OrBinaryCalculator;
import net.devstudy.interpreter.component.calculator.binary.predicate.EqualsBinaryCalculator;
import net.devstudy.interpreter.component.calculator.binary.predicate.LessBinaryCalculator;
import net.devstudy.interpreter.component.calculator.binary.predicate.LessOrEqualsBinaryCalculator;
import net.devstudy.interpreter.component.calculator.binary.predicate.MoreBinaryCalculator;
import net.devstudy.interpreter.component.calculator.binary.predicate.MoreOrEqualsBinaryCalculator;
import net.devstudy.interpreter.component.calculator.binary.predicate.NotEqualsBinaryCalculator;
import net.devstudy.interpreter.component.calculator.unary.BitwiseComplementUnaryCalculator;
import net.devstudy.interpreter.component.calculator.unary.MinusUnaryCalculator;
import net.devstudy.interpreter.component.calculator.unary.NotUnaryCalculator;
import net.devstudy.interpreter.component.calculator.unary.PlusUnaryCalculator;
import net.devstudy.interpreter.component.expressionbuilder.BinaryAssignmentExpressionBuilder;
import net.devstudy.interpreter.component.expressionbuilder.BinaryExpressionBuilder;
import net.devstudy.interpreter.component.expressionbuilder.SimpleExpressionBuilder;
import net.devstudy.interpreter.component.expressionbuilder.UnaryExpressionBuilder;
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
                put("-", MinusBinaryCalculator.createArithmenticMinusBinaryCalculator());
                put("+", PlusBinaryCalculator.createArithmenticPlusBinaryCalculator());
                put("*", MultiplicationBinaryCalculator.createArithmenticMultiplicationBinaryCalculator());
                put("/", DivideBinaryCalculator.createArithmenticDivideBinaryCalculator());
                put("%", RemainderBinaryCalculator.createArithmenticRemainderBinaryCalculator());
                put("&", BitAndBinaryCalculator.createArithmenticBitAndBinaryCalculator());
                put("|", BitOrBinaryCalculator.createArithmenticBitOrBinaryCalculator());
                put("^", BitXorBinaryCalculator.createArithmenticBitXorBinaryCalculator());
                put("||", new OrBinaryCalculator());
                put("&&", new AndBinaryCalculator());
                put("<<", BitLeftShiftBinaryCalculator.createArithmenticBitLeftShiftBinaryCalculator());
                put(">>", BitRightShiftBinaryCalculator.createArithmenticBitRightShiftBinaryCalculator());
                put(">>>", BitNoSignRightShiftBinaryCalculator.createArithmenticBitNoSignRightShiftBinaryCalculator());
                put("==", new EqualsBinaryCalculator());
                put("!=", new NotEqualsBinaryCalculator());
                put("<", new LessBinaryCalculator());
                put(">", new MoreBinaryCalculator());
                put("<=", new LessOrEqualsBinaryCalculator());
                put(">=", new MoreOrEqualsBinaryCalculator());
            }
        });
    }

    private final Map<String, BinaryCalculator> binaryAssignmentCalculatorMap = createBinaryAssignmentCalculatorMap();

    protected Map<String, BinaryCalculator> createBinaryAssignmentCalculatorMap() {
        return Collections.unmodifiableMap(new HashMap<String, BinaryCalculator>() {
            {
                put("-=", MinusBinaryCalculator.createAssignmentMinusBinaryCalculator());
                put("+=", PlusBinaryCalculator.createAssignmentPlusBinaryCalculator());
                put("*=", MultiplicationBinaryCalculator.createAssignmentMultiplicationBinaryCalculator());
                put("/=", DivideBinaryCalculator.createAssignmentDivideBinaryCalculator());
                put("%=", RemainderBinaryCalculator.createAssignmentRemainderBinaryCalculator());
                put("&=", BitAndBinaryCalculator.createAssignmentBitAndBinaryCalculator());
                put("|=", BitOrBinaryCalculator.createAssignmentBitOrBinaryCalculator());
                put("^=", BitXorBinaryCalculator.createAssignmentBitXorBinaryCalculator());
                put("<<=", BitLeftShiftBinaryCalculator.createAssignmentBitLeftShiftBinaryCalculator());
                put(">>=", BitRightShiftBinaryCalculator.createAssignmentBitRightShiftBinaryCalculator());
                put(">>>=", BitNoSignRightShiftBinaryCalculator.createAssignmentBitNoSignRightShiftBinaryCalculator());
            }
        });
    }

    private final Map<String, UnaryCalculator> unaryCalculatorMap = createUnaryCalculatorMap();

    protected Map<String, UnaryCalculator> createUnaryCalculatorMap() {
        return Collections.unmodifiableMap(new HashMap<String, UnaryCalculator>() {
            {
                put("+", new PlusUnaryCalculator());
                put("-", new MinusUnaryCalculator());
                put("!", new NotUnaryCalculator());
                put("~", new BitwiseComplementUnaryCalculator());
            }
        });
    }

    private final List<ExpressionBuilder> expressionBuilders = createExpressionBuilders(variableVerifier);

    protected List<ExpressionBuilder> createExpressionBuilders(VariableVerifier variableVerifier) {
        SimpleExpressionBuilder simpleExpressionBuilder = new SimpleExpressionBuilder(variableVerifier);
        return DataUtils.unmodifiableList(
                Arrays.asList(
                        simpleExpressionBuilder,
                        new BinaryExpressionBuilder(simpleExpressionBuilder, binaryCalculatorMap),
                        new BinaryAssignmentExpressionBuilder(simpleExpressionBuilder, binaryAssignmentCalculatorMap),
                        new UnaryExpressionBuilder(simpleExpressionBuilder, unaryCalculatorMap)
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
