package net.devstudy.interpreter.component;

import net.devstudy.interpreter.model.SourceLine;

public interface SourceLineReader {

    SourceLine[] read(String fileName);
}
