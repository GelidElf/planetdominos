package app.planetdominos.myselectors;

import java.util.HashMap;
import java.util.List;

import progen.kernel.functions.Function;
import progen.kernel.tree.Node;
import progen.userprogram.UserProgram;
import app.gpwars.engine.Planet;
import app.planetdominos.Planetdominos;

/**
 * 
 * @author 100061031 y 100082433
 *
 */

public class FastestGrowing extends Function {

	public FastestGrowing() {
		super("myPlanet", "myFastestGrowing");
	}

	@Override
	public Object evaluate(List<Node> arguments, UserProgram userProgram,
			HashMap<String, Object> returnAddr) {
		Planetdominos dominos = (Planetdominos) userProgram;
		List<Planet> planetasEntrada = dominos.getGame().MyPlanets(1);
		if (planetasEntrada.size() == 0){
			return null;
		}
		Planet fastestPlanetFound = planetasEntrada.get(0);
		for (Planet p: planetasEntrada){
			if (p.GrowthRate() < fastestPlanetFound.GrowthRate()){
				fastestPlanetFound = p;
			}
		}
		return fastestPlanetFound;
	}

}
