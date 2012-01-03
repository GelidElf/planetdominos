package app.planetdominos.conditions;

import java.util.HashMap;
import java.util.List;

import progen.kernel.functions.Terminal;
import progen.kernel.tree.Node;
import progen.userprogram.UserProgram;

/**
 * 
 * @author 100061031 y 100082433
 *
 */

public class TrueConstant extends Terminal {

	public TrueConstant() {
		super("condition", "true");
	}

	@Override
	public Object evaluate(List<Node> arguments, UserProgram userProgram,
			HashMap<String, Object> returnAddr) {
		return Boolean.TRUE;
	}

}