package net.devstudy.interpreter.component.impl;

import net.devstudy.interpreter.component.SignificantLineVerifier;

public class SQLLikeSignificantLineVerifier implements SignificantLineVerifier {
    @Override
    public boolean isSignificant(String line) {
        return !line.trim().isEmpty() && !line.trim().startsWith("--");
    }
}
