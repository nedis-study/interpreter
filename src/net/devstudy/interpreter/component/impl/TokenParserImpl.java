package net.devstudy.interpreter.component.impl;

import java.util.ArrayList;
import java.util.Arrays;

//import net.devstudy.interpreter.component.KeyWords;
import net.devstudy.interpreter.component.TokenParser;

public class TokenParserImpl implements TokenParser {
	@Override
	public String[] parse(String line) {
		// KeyWords operatorsKeyWords = new KeyWords();
		// String[] operatorsKW = operatorsKeyWords.getKeyWords();

		String[] operatorsAssignment = { "=", "+=", "-=", "*=", "/=", "%=", ">>=", ">>>=", "<<=", "^=", "|=", "&=" };
		String[] operatorsAriphmetics = { "+", "-", "*", "/", "%", "++", "--" };
		String[] operatorsCompare = { "==", "!=", ">", "<", ">=", "<=" };
		String[] operatorsBoolean = { "||", "&&", "!" };
		String[] operatorsBit = { "~", "&", "|", "^", ">>", ">>>", "<<" };
		String[] brakets = { "(", ")", "[", "]", "{", "}" };
		String[] others = { ".", ",", ":", ":", "/" };
		ArrayList<String> operatorsList = concatAll(operatorsAssignment, operatorsAriphmetics, operatorsCompare,
				operatorsBoolean, operatorsBit, brakets, others);

		ArrayList<String> tokens = new ArrayList<>();
		StringBuilder token = new StringBuilder();
		StringBuilder tokenWithNextChar;
		for (int i = 0; i < line.length(); i++) {
			char currentChar = line.charAt(i);
			char nextChar = i < line.length() - 1 ? line.charAt(i + 1) : 0;

			// String nextStringChar = Character.toString(nextChar);
			if (isCharSignificant(currentChar) || token.charAt(0) == '"') {
				token.append(currentChar);
				tokenWithNextChar = new StringBuilder(token).append(nextChar);
			} else {
				continue;
			}

			if (i == line.length() - 1) {
				addTokenToList(tokens, token);
				token = new StringBuilder();
			} else if (token.charAt(0) == '"' && token.length() > 1 && token.charAt(token.length() - 1) == '"') {
				addTokenToList(tokens, token);
				token = new StringBuilder();
			} else if (isOperator(operatorsList, token) && !isOperator(operatorsList, tokenWithNextChar)) {
				addTokenToList(tokens, token);
				token = new StringBuilder();
			} else if (!isCharSignificant(nextChar) && token.charAt(0) != '"') {
				addTokenToList(tokens, token);
				token = new StringBuilder();
			} else if (operatorsList.contains(Character.toString(nextChar)) && !isOperator(operatorsList, token) && token.charAt(0) != '"') {
				addTokenToList(tokens, token);
				token = new StringBuilder();
			}
		}
		return tokens.toArray(new String[tokens.size()]);
	}

	public void addTokenToList(ArrayList<String> tokens, StringBuilder token) {
		tokens.add(token.toString());
	}

	public boolean isOperator(ArrayList<String> operatorsList, StringBuilder token) {
		return operatorsList.contains(token.toString());
	}

	private boolean isCharSignificant(char charAt) {
		return charAt != ' ' && charAt != '\t';
	}

	public static ArrayList<String> concatAll(String[] first, String[]... rest) {
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

