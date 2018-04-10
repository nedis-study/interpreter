package net.devstudy.interpreter.component;

import net.devstudy.interpreter.model.Expression;

public interface ExpressionBuilder {

    boolean isSupport(String... tokens);

    Expression build(String... tokens);
}
