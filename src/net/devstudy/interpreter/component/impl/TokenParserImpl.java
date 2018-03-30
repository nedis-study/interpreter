package net.devstudy.interpreter.component.impl;

import java.util.ArrayList;
import java.util.Arrays;

//import net.devstudy.interpreter.component.KeyWords;
import net.devstudy.interpreter.component.TokenParser;

public class TokenParserImpl implements TokenParser {

	private StringBuilder token = new StringBuilder();
		
	@Override
	public String[] parse(String line) {
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
		StringBuilder tokenWithNextChar;
		
		for (int i = 0; i < line.length(); i++) {
			char currentChar = line.charAt(i);
			char nextChar = i < line.length() - 1 ? line.charAt(i + 1) : 0;

			if (isCharSignificant(currentChar) || isTokenBeginLikeString(token)) {
				token.append(currentChar);
				tokenWithNextChar = new StringBuilder(token).append(nextChar);
			} else {
				continue;
			}

			if (i == line.length() - 1) {
				addTokenToList(tokens, token);
				continue;
			} else if (isTokenString(token)) {
				addTokenToList(tokens, token);
			continue;
			} else if (isTokenOperator(operatorsList, token) && !isTokenOperator(operatorsList, tokenWithNextChar)) {
				addTokenToList(tokens, token);
				continue;
			} else if (!isCharSignificant(nextChar) && !isTokenBeginLikeString(token)) {
				addTokenToList(tokens, token);
				continue;
			} else if (operatorsList.contains(Character.toString(nextChar)) && !isTokenOperator(operatorsList, token) && token.charAt(0) != '"') {
				addTokenToList(tokens, token);
				continue;
			}
		}
		return tokens.toArray(new String[tokens.size()]);
	}

	private boolean isTokenString(StringBuilder token) {
		return isTokenBeginLikeString(token) && token.length() > 1 && token.charAt(token.length() - 1) == '"';
	}

	private boolean isTokenBeginLikeString(StringBuilder token) {
		return token.length() > 0 && token.charAt(0) == '"';
	}

	private void addTokenToList(ArrayList<String> tokens, StringBuilder token) {
		tokens.add(token.toString());
		this.token = new StringBuilder();
	}

	private boolean isTokenOperator(ArrayList<String> operatorsList, StringBuilder token) {
		return operatorsList.contains(token.toString());
	}

	private boolean isCharSignificant(char charAt) {
		return charAt != ' ' && charAt != '\t';
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

