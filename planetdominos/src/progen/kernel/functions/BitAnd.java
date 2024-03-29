package progen.kernel.functions;

import java.util.HashMap;
import java.util.List;

import progen.kernel.tree.Node;
import progen.userprogram.UserProgram;

/**
 * Clase que implementa el operador de Y lógico de dos variable enteras a nivel de bit.
 * 
 * @author jirsis
 * @since 2.0
 */
public class BitAnd extends NonTerminal{

	/**
	 * Constructor por defecto.
	 */
	public BitAnd() {
		super("int$$int$$int", "&");
	}

	/*
	 * (non-Javadoc)
	 * @see progen.kernel.functions.Function#evaluate(java.util.List, progen.userprogram.UserProgram, java.util.HashMap)
	 */
	@Override
	public Object evaluate(List<Node> arguments, UserProgram userProgram, HashMap<String, Object> returnAddr) {
		Integer operador1 = (Integer) arguments.get(0).evaluate(userProgram, returnAddr);
		Integer operador2 = (Integer) arguments.get(1).evaluate(userProgram, returnAddr);
		return operador1 & operador2;
	}

}
