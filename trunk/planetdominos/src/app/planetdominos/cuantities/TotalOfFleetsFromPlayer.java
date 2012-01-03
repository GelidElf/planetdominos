package app.planetdominos.cuantities;

import java.util.HashMap;
import java.util.List;

import progen.kernel.functions.Function;
import progen.kernel.tree.Node;
import progen.userprogram.UserProgram;
import app.planetdominos.Planetdominos;

/**
 * 
 * @author 100061031 y 100082433
 *
 */

public class TotalOfFleetsFromPlayer extends Function {

	public TotalOfFleetsFromPlayer() {
		super("numFleets$$playerId", "totalOfFleetsFromPlayer");
	}

	@Override
	public Object evaluate(List<Node> arguments, UserProgram userProgram,
			HashMap<String, Object> returnAddr) {
		Planetdominos dominos = (Planetdominos) userProgram;
		Integer playerId = (Integer)arguments.get(0).evaluate(userProgram, returnAddr);
		return dominos.getGame().MyFleets(playerId).size();
	}

}