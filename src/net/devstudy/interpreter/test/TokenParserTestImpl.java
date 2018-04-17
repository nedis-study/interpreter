package net.devstudy.interpreter.test;

import net.devstudy.interpreter.component.impl.OperatorsList;
import net.devstudy.interpreter.component.impl.TokenParserImpl;

import java.util.Arrays;

public class TokenParserTestImpl {
    public static void main(String[] args) {
        TokenParserImpl tokenParser = new TokenParserImpl();
        System.out.println((tokenParser.parse("var a+=6*y-function2(5,func3(8))")));
        System.out.println((tokenParser.parse("var b =\"hello 3 * 7\"+67 >>>>> * >90 \"8*9")));
        System.out.println(OperatorsList.getOperatorsAssignment().contains("-="));
    }
}
