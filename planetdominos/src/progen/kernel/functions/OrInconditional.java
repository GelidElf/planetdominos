package progen.kernel.functions;

import java.util.HashMap;
import java.util.List;

import progen.kernel.functions.NonTerminal;
import progen.kernel.tree.Node;
import progen.userprogram.UserProgram;

/**
 * Clase que implementa el operador de O lógico de dos variable booleanas de tal 
 * forma que se evalúan los dos parámetros.
 * 
 * @author jirsis
 * @since 2.0
 */
public class OrInconditional extends NonTerminal {

	/**
	 * Constructor por defecto.
	 */
	public OrInconditional() {
		super("boolean$$boolean$$boolean", "|");
	}

	/*
	 * (non-Javadoc)
	 * @see progen.kernel.functions.Function#evaluate(java.util.List, progen.userprogram.UserProgram, java.util.HashMap)
	 */
	@Override
	public Object evaluate(List<Node> arguments, UserProgram userProgram, HashMap<String, Object> returnAddr) {
		Boolean operador1 = (Boolean) arguments.get(0).evaluate(userProgram, returnAddr);
		Boolean operador2 = (Boolean) arguments.get(1).evaluate(userProgram, returnAddr);
		return operador1 | operador2;
	}

}
