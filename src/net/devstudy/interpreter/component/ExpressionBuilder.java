package net.devstudy.interpreter.component;

import net.devstudy.interpreter.model.Expression;

import java.util.List;

public interface ExpressionBuilder {

    boolean isSupport(List<String> tokens);

    Expression build(List<String> tokens);
}
