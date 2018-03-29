package net.devstudy.interpreter.component.impl;

import java.util.ArrayList;
import java.util.Arrays;

import net.devstudy.interpreter.component.KeyWords;
import net.devstudy.interpreter.component.TokenParser;

public class TokenParserImpl implements TokenParser {
	@Override
	public String[] parse(String line) {
		//KeyWords operatorsKeyWords = new KeyWords();
		//String[] operatorsKW = operatorsKeyWords.getKeyWords();

		String[] operatorsAssignment = { "=", "+=", "-=", "*=", "/=", "%=", ">>=", ">>>=", "<<=", "^=", "|=", "&=" };
		String[] operatorsAriphmetics = { "+", "-", "*", "/", "%", "++", "--" };
		String[] operatorsCompare = { "==", "!=", ">", "<", ">=", "<=" };
		String[] operatorsBoolean = { "||", "&&", "!" };
		String[] operatorsBit = { "~", "&", "|", "^", ">>", ">>>", "<<" };
		String[] brakets = { "(", ")", "[", "]", "{", "}" };
		ArrayList<String> operatorsList= concatAll(operatorsAssignment, operatorsAriphmetics, operatorsCompare,
				operatorsBoolean, operatorsBit, brakets);

		ArrayList<String> tokens = new ArrayList<>();
		StringBuilder token = new StringBuilder();

		int currentIndex = 0;
		// First operator parser
		for (int i = 0; i < line.length(); i++) {
			currentIndex = i;
			char currentChar = line.charAt(i);
			String currentStringChar = Character.toString(currentChar);
			if (isCharSignificant(currentChar) && !operatorsList.contains(currentStringChar)) {
				token.append(currentChar);
			} else {
				tokens.add(token.toString());
				token.setLength(0);
				break;
			}
		}
		// Other operators parser
		for (int i = currentIndex + 1; i < line.length(); i++) {
			char currentChar = line.charAt(i);
			char nextChar = line.charAt(i + 1);
			String currentStringChar = Character.toString(currentChar);
			String nextStringChar = Character.toString(nextChar);
			if (isCharSignificant(currentChar)) {
				token.append(currentChar);
				if(isOperator(operatorsList, token.toString()) && !isOperator(operatorsList, token.toString() + nextStringChar)) {
					tokens.add(token.toString());
					token.setLength(0);
				}
			}else {
				tokens.add(token.toString());
				token.setLength(0);
			}
			
		}
		

		return line.split(" ");
	}

	public boolean isOperator(ArrayList<String> operatorsList, String token) {
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
