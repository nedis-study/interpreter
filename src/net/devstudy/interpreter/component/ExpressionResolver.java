package net.devstudy.interpreter.component;

import net.devstudy.interpreter.model.Expression;

import java.util.List;

public interface ExpressionResolver {

    Expression resolve(List<String> tokens);
}
