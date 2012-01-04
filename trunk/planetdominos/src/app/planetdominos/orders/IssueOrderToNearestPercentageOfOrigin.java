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
import app.planetdominos.Planetdominos;

/**
 * 
 * @author 100061031 y 100082433
 *
 */

public class IssueOrderToNearestPercentageOfOrigin extends Function {

	public IssueOrderToNearestPercentageOfOrigin() {
		super("List<Order>$$Planet$$List<Planet>$$porcentaje", "issueOrderToNearestPercentageOfOrigin");
	}

	@Override
	public Object evaluate(List<Node> arguments, UserProgram userProgram,
			HashMap<String, Object> returnAddr) {
		
		Planet origen = (Planet)arguments.get(0).evaluate(userProgram, returnAddr);
		@SuppressWarnings("unchecked")
		List<Planet> planetasDestino = (List<Planet>)arguments.get(1).evaluate(userProgram, returnAddr);
		double porcentaje = (Double)arguments.get(2).evaluate(userProgram, returnAddr);
		
		
		Planetdominos dominos = (Planetdominos) userProgram;
		
		if ((planetasDestino.size() == 0) || (origen == null)){
			return Collections.EMPTY_LIST;
		}
		
		Planet nearestPlanet = planetasDestino.get(0);
		int nearestPlanetDistance = dominos.getGame().distance(origen, planetasDestino.get(0));
		for (Planet p: planetasDestino){
			int distance = dominos.getGame().distance(origen, p);
			if (distance < nearestPlanetDistance){
				nearestPlanet = p;
				nearestPlanetDistance = distance;
			}else{
				if (distance == nearestPlanetDistance){
					if (p.GrowthRate() > nearestPlanet.GrowthRate()){
						nearestPlanet = p;
					}
				}
			}
		}
		
		int numeroNavesAEnviar = new Double(origen.NumShips()*porcentaje).intValue();
		Order order = new Order(origen, nearestPlanet, numeroNavesAEnviar);
		List<Order> listaOrdenes = new ArrayList<Order>();
		listaOrdenes.add(order);
		
		return listaOrdenes;
	}

}
