package app.planetdominos.orders;

import java.util.ArrayList;
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

public class Prog2 extends Function{

	public Prog2() {
		super("List<Order>$$List<Order>$$List<Order>", "Prog2");
		
	}

	@Override
	@SuppressWarnings("unchecked")
	public Object evaluate(List<Node> arguments, UserProgram userProgram, 
			HashMap<String, Object> returnAddr) {
		
		List<Order> listaOrdenes1 = (List<Order>) arguments.get(0).evaluate(userProgram, returnAddr);
		List<Order> listaOrdenes2 = (List<Order>) arguments.get(1).evaluate(userProgram, returnAddr);
		
		List<Order> listaOrdenes = new ArrayList<Order>();
		listaOrdenes.addAll(listaOrdenes1);
		listaOrdenes.addAll(listaOrdenes2);
		return listaOrdenes;
	}

}
