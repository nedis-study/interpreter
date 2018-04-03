package net.devstudy.interpreter.component.impl;

import net.devstudy.interpreter.component.TokenParser;

import java.util.ArrayList;
import java.util.List;

public class TokenParserImpl implements TokenParser {

    @Override
    public String[] parse(String line) {
        StringBuilder token = new StringBuilder();
        List<String> tokens = new ArrayList<>();
        StringBuilder tokenWithNextChar;

        for (int i = 0; i < line.length(); i++) {
            char currentChar = line.charAt(i);
            char nextChar = i < line.length() - 1 ? line.charAt(i + 1) : 0;

            if (isCharSignificant(currentChar) || isTokenBeginLikeString(token)) {
                token.append(currentChar);
                tokenWithNextChar = new StringBuilder(token).append(nextChar);
                //Compare
                if (i == line.length() - 1) {
                    addTokenToList(tokens, token);
                } else if (isTokenString(token)) {
                    addTokenToList(tokens, token);
                } else if (isTokenOperator(token) && !isTokenOperator(tokenWithNextChar)) {
                    addTokenToList(tokens, token);
                } else if (!isCharSignificant(nextChar) && !isTokenBeginLikeString(token)) {
                    addTokenToList(tokens, token);
                } else if (OperatorsList.contains(nextChar) && !isTokenOperator(token)
                        && !isTokenBeginLikeString(token)) {
                    addTokenToList(tokens, token);
                }
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

    private void addTokenToList(List<String> tokens, StringBuilder token) {
        tokens.add(token.toString());
        token.setLength(0);
    }

    private boolean isTokenOperator(StringBuilder token) {
        return OperatorsList.contains(token);
    }

    private boolean isCharSignificant(char charAt) {
        return charAt != ' ' && charAt != '\t';
    }

}
