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

public class Not extends Function {

	public Not() {
		super("condition$$condition", "not");
	}

	@Override
	public Object evaluate(List<Node> arguments, UserProgram userProgram,
			HashMap<String, Object> returnAddr) {
		Boolean condicion = (Boolean)arguments.get(0).evaluate(userProgram, returnAddr);
		return !condicion;
	}

}