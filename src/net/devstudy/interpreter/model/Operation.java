package net.devstudy.interpreter.model;

public interface Operation {

    SourceLine getSourceLine();

    String[] getTokens();

    default int getTokenCount() {
        return getTokens().length;
    }

    default String getToken(int index) {
        return getTokens()[index];
    }
}
