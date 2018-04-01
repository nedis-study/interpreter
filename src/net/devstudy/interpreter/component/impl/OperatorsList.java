package net.devstudy.interpreter.component.impl;

import java.util.ArrayList;
import java.util.Arrays;

public final class OperatorsList {
	private static final String[] operatorsAssignment = { "=", "+=", "-=", "*=", "/=", "%=", ">>=", ">>>=", "<<=", "^=", "|=", "&=" };
	private static final String[] operatorsAriphmetics = { "+", "-", "*", "/", "%", "++", "--" };
	private static final String[] operatorsCompare = { "==", "!=", ">", "<", ">=", "<=" };
	private static final String[] operatorsBoolean = { "||", "&&", "!" };
	private static final String[] operatorsBit = { "~", "&", "|", "^", ">>", ">>>", "<<" };
	private static final String[] brakets = { "(", ")", "[", "]", "{", "}" };
	private static final String[] others = { ".", ",", ":", ":", "/" };
	private static final ArrayList<String> operatorsList = concatAll(operatorsAssignment, operatorsAriphmetics, operatorsCompare,
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
	
	public static ArrayList<String> getList() {
		return operatorsList;
	}
	
	private static ArrayList<String> concatAll(String[] first, String[]... rest) {
		int totalLength = first.length;
		for (String[] array : rest) {
			totalLength += array.length;
		}
		String[] result = Arrays.copyOf(first, totalLength);
		int offset = first.length;
		for (String[] array : rest) {
			System.arraycopy(array, 0, result, offset, array.length);
			offset += array.length;
		}
		return new ArrayList<String>(Arrays.asList(result));
	}

}
