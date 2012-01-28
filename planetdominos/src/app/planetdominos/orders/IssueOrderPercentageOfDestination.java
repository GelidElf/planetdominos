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

public class IssueOrderPercentageOfDestination extends Function {

	public IssueOrderPercentageOfDestination() {
		super("List<Order>$$myPlanet$$Planet$$porcentaje", "issueOrderPercentageOfDestination");
	}

	@Override
	public Object evaluate(List<Node> arguments, UserProgram userProgram,
			HashMap<String, Object> returnAddr) {
		
		Planet origen = (Planet)arguments.get(0).evaluate(userProgram, returnAddr);
		Planet destino = (Planet)arguments.get(1).evaluate(userProgram, returnAddr);
		
		if ((origen == null) || (destino == null)){
			return Collections.EMPTY_LIST;
		}
		
		double porcentaje = (Double)arguments.get(2).evaluate(userProgram, returnAddr);
		
		int numeroNavesAEnviar = new Double(destino.NumShips()*porcentaje).intValue();
		
		if (origen.NumShips() < numeroNavesAEnviar){
			numeroNavesAEnviar = origen.NumShips();
		}
		Order order = new Order(origen, destino, numeroNavesAEnviar);
		List<Order> listaOrdenes = new ArrayList<Order>();
		listaOrdenes.add(order);
		
		return listaOrdenes;
	}

}
