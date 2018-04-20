package net.devstudy.interpreter.utils;

import net.devstudy.interpreter.exception.RuntimeInterpreterException;

public class DivideByZeroUtils {

	public static void check(Object value) {
		if (value instanceof Number && ((Number) value).doubleValue() == 0.0) {
			throw new RuntimeInterpreterException("Division by zero");
		}
	}

}
