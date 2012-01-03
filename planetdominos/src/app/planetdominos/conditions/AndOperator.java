package app.planetdominos.conditions;

import java.util.HashMap;
import java.util.List;

import progen.kernel.functions.Function;
import progen.kernel.tree.Node;
import progen.userprogram.UserProgram;

/**
 * 
 * @author 100061031 y 100082433
 *
 */

public class AndOperator extends Function {

	public AndOperator() {
		super("condition$$condition$$condition", "and");
	}

	@Override
	public Object evaluate(List<Node> arguments, UserProgram userProgram,
			HashMap<String, Object> returnAddr) {
		Boolean left = (Boolean)arguments.get(0).evaluate(userProgram, returnAddr);
		Boolean right = (Boolean)arguments.get(1).evaluate(userProgram, returnAddr);
		return left && right;
	}

}