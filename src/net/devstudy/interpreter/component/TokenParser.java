package net.devstudy.interpreter.component;

import java.util.List;

public interface TokenParser {

    List<String> parse(String line);
}
