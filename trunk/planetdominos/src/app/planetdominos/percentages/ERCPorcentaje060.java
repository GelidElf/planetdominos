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

public class ERCPorcentaje060 extends Terminal {

	public ERCPorcentaje060() {
		super("porcentaje", "porcentaje060");
	}
	
	@Override
	public Object evaluate(List<Node> arguments, UserProgram userProgram,
			HashMap<String, Object> returnAddr) {
		return Double.valueOf(0.6);
	}


}