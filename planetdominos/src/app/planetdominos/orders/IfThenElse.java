package app.planetdominos.orders;

import java.util.HashMap;
import java.util.List;

import progen.kernel.functions.Function;
import progen.kernel.tree.Node;
import progen.userprogram.UserProgram;
import app.gpwars.engine.Order;

/**
 * 
 * @author 100061031 y 100082433
 *
 */

public class IfThenElse extends Function{

	public IfThenElse() {
		super("List<Order>$$condition$$List<Order>$$List<Order>", "ifthenelse");
		
	}

	@Override
	@SuppressWarnings("unchecked")
	public Object evaluate(List<Node> arguments, UserProgram userProgram, 
			HashMap<String, Object> returnAddr) {
		
		Boolean condicion = (Boolean)arguments.get(0).evaluate(userProgram, returnAddr);
		List<Order> listaOrdenes1 = (List<Order>) arguments.get(1).evaluate(userProgram, returnAddr);
		List<Order> listaOrdenes2 = (List<Order>) arguments.get(2).evaluate(userProgram, returnAddr);
		
		return (condicion)?listaOrdenes1:listaOrdenes2;
	}

}
