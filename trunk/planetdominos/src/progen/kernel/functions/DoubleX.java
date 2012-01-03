package progen.kernel.functions;

import java.util.HashMap;
import java.util.List;

import progen.kernel.tree.Node;
import progen.userprogram.UserProgram;

/**
 * Identifica una variable de tipo double, identificada con el literal <code>dX</code>
 * @author jirsis
 * @since 2.0
 */
public class DoubleX extends Terminal{

	/**
	 * Constructor por defecto.
	 */
	public DoubleX() {
		super("double", "dX");
	}

	/*
	 * (non-Javadoc)
	 * @see progen.kernel.functions.Terminal#evaluate(java.util.List, progen.userprogram.UserProgram, java.util.HashMap)
	 */
	@Override
	public Object evaluate(List<Node> arguments, UserProgram userProgram, HashMap<String, Object> returnAddr) {
		return getValue();
	}

}
