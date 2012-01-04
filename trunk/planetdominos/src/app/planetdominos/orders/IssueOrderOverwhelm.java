package app.planetdominos.orders;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import progen.kernel.functions.Function;
import progen.kernel.tree.Node;
import progen.userprogram.UserProgram;
import app.gpwars.engine.Order;
import app.gpwars.engine.Planet;

/**
 * 
 * @author 100061031 y 100082433
 *
 */

public class IssueOrderOverwhelm extends Function {

	public IssueOrderOverwhelm() {
		super("List<Order>$$Planet$$Planet", "overwhelm");
	}

	@Override
	public Object evaluate(List<Node> arguments, UserProgram userProgram,
			HashMap<String, Object> returnAddr) {
		
		Planet origen = (Planet)arguments.get(0).evaluate(userProgram, returnAddr);
		Planet destino = (Planet)arguments.get(1).evaluate(userProgram, returnAddr);
		
		if ((origen == null) || (destino == null)){
			return Collections.EMPTY_LIST;
		}
		
		int numeroNavesAEnviar = 0;
		
		if (origen.NumShips() > destino.NumShips()){
			numeroNavesAEnviar = destino.NumShips();
		}else{
			numeroNavesAEnviar = origen.NumShips();
		}
		
		Order order = new Order(origen, destino, numeroNavesAEnviar);
		List<Order> listaOrdenes = new ArrayList<Order>();
		listaOrdenes.add(order);
		
		return listaOrdenes;
	}

}
