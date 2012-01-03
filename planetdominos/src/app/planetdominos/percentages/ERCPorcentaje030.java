package app.planetdominos.percentages;

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

public class ERCPorcentaje030 extends Terminal {

	public ERCPorcentaje030() {
		super("porcentaje", "porcentaje030");
	}
	
	@Override
	public Object evaluate(List<Node> arguments, UserProgram userProgram,
			HashMap<String, Object> returnAddr) {
		return Double.valueOf(0.3);
	}


}