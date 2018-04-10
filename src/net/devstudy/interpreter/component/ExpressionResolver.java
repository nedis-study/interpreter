package net.devstudy.interpreter.component;

import net.devstudy.interpreter.model.Expression;

public interface ExpressionResolver {

    Expression resolve(String... tokens);
}
