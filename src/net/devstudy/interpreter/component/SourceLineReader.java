package net.devstudy.interpreter.component;

import net.devstudy.interpreter.model.SourceLine;

import java.util.List;

public interface SourceLineReader {

    List<SourceLine> read(String fileName);
}
