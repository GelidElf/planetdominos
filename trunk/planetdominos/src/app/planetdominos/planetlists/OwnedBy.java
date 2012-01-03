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

public class OwnedBy extends Terminal {

	public OwnedBy() {
		super("List<Planet>$$playerId", "ownedBy");
	}

	@Override
	public Object evaluate(List<Node> arguments, UserProgram userProgram,
			HashMap<String, Object> returnAddr) {
		Integer playerId = (Integer)arguments.get(0).evaluate(userProgram, returnAddr);
		Planetdominos prog = (Planetdominos) userProgram;
		return prog.getGame().MyPlanets(playerId);
	}

}
