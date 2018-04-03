package net.devstudy.interpreter.component.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class OperatorsList {
    private static final String[] operatorsAssignment = {"=", "+=", "-=", "*=", "/=", "%=", ">>=", ">>>=", "<<=", "^=", "|=", "&="};
    private static final String[] operatorsAriphmetics = {"+", "-", "*", "/", "%", "++", "--"};
    private static final String[] operatorsCompare = {"==", "!=", ">", "<", ">=", "<="};
    private static final String[] operatorsBoolean = {"||", "&&", "!"};
    private static final String[] operatorsBit = {"~", "&", "|", "^", ">>", ">>>", "<<"};
    private static final String[] brakets = {"(", ")", "[", "]", "{", "}"};
    private static final String[] others = {".", ",", ":", ":", "/"};
    private static final List<String> operatorsList = concatAll(operatorsAssignment, operatorsAriphmetics, operatorsCompare,
            operatorsBoolean, operatorsBit, brakets, others);

    public static boolean contains(StringBuilder sb) {
        return operatorsList.contains(sb.toString());
    }

    public static boolean contains(String s) {
        return operatorsList.contains(s);
    }

    public static boolean contains(char ch) {
        return operatorsList.contains(Character.toString(ch));
    }

    public static List<String> getList() {
        return operatorsList;
    }

    public static String[] getArray() {
        return operatorsList.toArray(new String[operatorsList.size()]);
    }

    private static List<String> concatAll(String[]... rest) {
        List<String> list = new ArrayList<>();
        for (String[] strings : rest) {
            Collections.addAll(list, strings);
        }
        return list;
    }

}
