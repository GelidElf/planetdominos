/**
 * 
 */
package progen.kernel.functions;

import java.util.HashMap;
import java.util.List;

import progen.kernel.tree.Node;
import progen.userprogram.UserProgram;

/**
 * Implementación del operador de exponenciación entera, es decir, eleva un número 
 * entero a otro número entero, devolviendo un número entero.
 * 
 * @author jirsis
 * @since 2.0
 */
public class IntegerPow extends NonTerminal {
	

	/**
	 * Constructor por defecto.
	 */
	public IntegerPow() {
		super("intenger$$integer$$integer", "^");
	}

	/* (non-Javadoc)
	 * @see progen.kernel.functions.Function#evaluate(java.util.List, progen.userprogram.UserProgram)
	 */
	@Override
	public Object evaluate(List<Node> arguments, UserProgram userProgram, HashMap<String,Object> returnAddr){
		Integer base=(Integer)arguments.get(0).evaluate(userProgram, returnAddr);
		Integer exponent=(Integer)arguments.get(1).evaluate(userProgram, returnAddr);
		return (int)Math.pow(base, exponent);
	}

}
