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

public class NumPlanetsLessThan extends Function {

	public NumPlanetsLessThan() {
		super("condition$$numPlanets$$numPlanets", "numPlanetsLessThan");
	}

	@Override
	public Object evaluate(List<Node> arguments, UserProgram userProgram,
			HashMap<String, Object> returnAddr) {
		Integer int1 = (Integer)arguments.get(0).evaluate(userProgram, returnAddr);
		Integer int2 = (Integer)arguments.get(1).evaluate(userProgram, returnAddr);
		return int1 < int2;
	}

}