package app.planetdominos.planetlists;

import java.util.HashMap;
import java.util.List;

import app.planetdominos.Planetdominos;

import progen.kernel.functions.Terminal;
import progen.kernel.tree.Node;
import progen.userprogram.UserProgram;

/**
 * 
 * @author 100061031 y 100082433
 *
 */

public class Neutral extends Terminal {

	public Neutral() {
		super("List<Planet>", "neutral");
	}

	@Override
	public Object evaluate(List<Node> arguments, UserProgram userProgram,
			HashMap<String, Object> returnAddr) {
		Planetdominos prog = (Planetdominos) userProgram;
		return prog.getGame().NeutralPlanets();
	}

}