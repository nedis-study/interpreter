package net.devstudy.interpreter.model;

import java.util.Arrays;

public interface Operation {

    SourceLine getSourceLine();

    String[] getTokens();

    default int getTokenCount() {
        return getTokens().length;
    }

    default String getToken(int index) {
        return getTokens()[index];
    }

    default String[] getSubTokens(int fromIndex) {
        return getSubTokens(fromIndex, getTokenCount());
    }

    default String[] getSubTokens(int fromIndex, int toIndex) {
        return Arrays.copyOfRange(getTokens(), fromIndex, toIndex);
    }
}
