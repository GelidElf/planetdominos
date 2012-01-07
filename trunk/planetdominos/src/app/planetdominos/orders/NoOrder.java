package app.planetdominos.orders;

import java.util.Collections;
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

public class NoOrder extends Function {

	public NoOrder() {
		super("List<Order>", "noOrder");
	}

	@Override
	public Object evaluate(List<Node> arguments, UserProgram userProgram,
			HashMap<String, Object> returnAddr) {
		return Collections.EMPTY_LIST;
	}

}
