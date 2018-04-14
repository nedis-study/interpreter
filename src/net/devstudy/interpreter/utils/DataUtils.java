package net.devstudy.interpreter.utils;

import java.util.Collections;
import java.util.List;

public class DataUtils {
    private final static List<?> EMPTY = Collections.unmodifiableList(Collections.emptyList());

    public static <T> List<T> unmodifiableList(List<T> list) {
        if (list.getClass() == EMPTY.getClass()) {
            return list;
        } else {
            return Collections.unmodifiableList(list);
        }
    }
}
