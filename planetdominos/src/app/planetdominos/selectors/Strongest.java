package app.planetdominos.selectors;

import java.util.HashMap;
import java.util.List;

import progen.kernel.functions.Function;
import progen.kernel.tree.Node;
import progen.userprogram.UserProgram;
import app.gpwars.engine.Planet;

/**
 * 
 * @author 100061031 y 100082433
 *
 */

public class Strongest extends Function {

	public Strongest() {
		super("Planet$$List<Planet>", "strongest");
	}

	@Override
	public Object evaluate(List<Node> arguments, UserProgram userProgram,
			HashMap<String, Object> returnAddr) {
		@SuppressWarnings("unchecked")
		List<Planet> planetasEntrada = (List<Planet>)arguments.get(0).evaluate(userProgram, returnAddr);
		if (planetasEntrada.size() == 0){
			return null;
		}
		Planet strongestPlanetFound = planetasEntrada.get(0);
		for (Planet p: planetasEntrada){
			if (p.NumShips() > strongestPlanetFound.NumShips()){
				strongestPlanetFound = p;
			}
		}
		return strongestPlanetFound;
	}

}
