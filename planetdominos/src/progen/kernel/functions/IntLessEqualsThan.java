package progen.kernel.functions;

import java.util.HashMap;
import java.util.List;

import progen.kernel.tree.Node;
import progen.userprogram.UserProgram;

/**
 * Clase que implementa el operador de menor o igual que (<=) de dos variable enteras.
 * 
 * @author jirsis
 * @since 2.0
 */
public class IntLessEqualsThan extends NonTerminal{

	/**
	 * Constructor por defecto.
	 */
	public IntLessEqualsThan() {
		super("boolean$$int$$int", "<=");
	}

	/*
	 * (non-Javadoc)
	 * @see progen.kernel.functions.Function#evaluate(java.util.List, progen.userprogram.UserProgram, java.util.HashMap)
	 */
	@Override
	public Object evaluate(List<Node> arguments, UserProgram userProgram, HashMap<String, Object> returnAddr) {
		Integer operador1 = (Integer) arguments.get(0).evaluate(userProgram, returnAddr);
		Integer operador2 = (Integer) arguments.get(1).evaluate(userProgram, returnAddr);
		return operador1 <= operador2;
	}

}
