package net.devstudy.interpreter.component.calculator;

import static net.devstudy.interpreter.utils.TypeUtils.getType;

import net.devstudy.interpreter.component.BinaryCalculator;

import net.devstudy.interpreter.component.VariableStorage;

import net.devstudy.interpreter.component.impl.VariableStorageHelper;
import net.devstudy.interpreter.exception.SyntaxInterpreterException;

public class MinusEqualsBinaryCalculator implements BinaryCalculator {

	@Override
	public Object calculate(Object value1, Object value2) {
		VariableStorage variableStorage = VariableStorageHelper.getVariableStorage();
		String varName = value1.toString();
		Object result = null;
		if (variableStorage.isDefined(varName)) {
			Object varValue = variableStorage.getVariable(varName);
			if (value2 instanceof Integer) {
				result = (Integer) varValue - (Integer) value2;
				variableStorage.putVariable(varName, (Integer) result);
			} else if (value2 instanceof Number) {
				result = ((Number) varValue).doubleValue() - ((Number) value2).doubleValue();
				variableStorage.putVariable(varName, (Double) result);
			} else {
				throw new SyntaxInterpreterException(
						"Operator -= not supported for types: " + getType(value1) + " and " + getType(value2));
			}
			return result;
		} else {
			throw new SyntaxInterpreterException("Variable name is not defined");
		}

	}
}
