package net.devstudy.interpreter.component.impl;

import net.devstudy.interpreter.model.SourceLine;

public class SourceLineHelper {
    private static SourceLine sourceLine;

    public static SourceLine getSourceLine() {
        return sourceLine;
    }

    public static void setSourceLine(SourceLine sourceLine) {
        SourceLineHelper.sourceLine = sourceLine;
    }
}
