package net.devstudy.interpreter.component;

import net.devstudy.interpreter.model.Operation;
import net.devstudy.interpreter.model.SourceLine;

public interface OperationTreeBuilder {

    Operation[] buildTree(SourceLine[] sourceLines);
}
