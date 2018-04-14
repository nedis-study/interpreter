package net.devstudy.interpreter.model;

import java.util.List;

public interface Operation {

    SourceLine getSourceLine();

    List<String> getTokens();

    default int getTokenCount() {
        return getTokens().size();
    }

    default String getToken(int index) {
        return getTokens().get(index);
    }

    default List<String> getSubTokens(int fromIndex) {
        return getSubTokens(fromIndex, getTokenCount());
    }

    default List<String> getSubTokens(int fromIndex, int toIndex) {
        return getTokens().subList(fromIndex, toIndex);
    }
}
