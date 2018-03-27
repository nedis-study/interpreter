package net.devstudy.interpreter.component.impl;

import net.devstudy.interpreter.component.TokenParser;

public class TokenParserImpl implements TokenParser {
    @Override
    public String[] parse(String line) {
        return line.split(" ");
    }
}
