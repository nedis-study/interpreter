package net.devstudy.interpreter.component;

import net.devstudy.interpreter.utils.DataUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class KeyWords {

    public static final String VAR = "var";

    public static final String OUT = "out";

    public static final String INPUT = "input";

    public static final String NULL = "null";

    public static final String TRUE = "true";

    public static final String FALSE = "false";

    public static final List<String> ALL_KEY_WORDS;

    static {
        List<String> list = new ArrayList<>();
        Field[] fields = KeyWords.class.getDeclaredFields();
        for (Field field : fields) {
            if (field.getType() == String.class) {
                try {
                    list.add(field.get(KeyWords.class).toString());
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        ALL_KEY_WORDS = DataUtils.unmodifiableList(list);
    }
}
