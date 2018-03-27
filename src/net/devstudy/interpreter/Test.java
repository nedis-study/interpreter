package net.devstudy.interpreter;

import net.devstudy.interpreter.component.Config;
import net.devstudy.interpreter.component.Interpreter;
import net.devstudy.interpreter.component.impl.SQLLIkeConfigImpl;

public class Test {
    public static void main(String[] args) {
        Config config = new SQLLIkeConfigImpl();
        Interpreter interpreter = config.getCurrentInterpreter();
        interpreter.interpret("test.javamm");
    }
}
