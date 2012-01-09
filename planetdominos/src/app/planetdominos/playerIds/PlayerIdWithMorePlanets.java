package app.planetdominos.playerIds;

import java.util.HashMap;
import java.util.List;

import progen.kernel.functions.Terminal;
import progen.kernel.tree.Node;
import progen.userprogram.UserProgram;
import app.planetdominos.Planetdominos;

/**
 *
 * @author 100061031 y 100082433
 *
 */

public class PlayerIdWithMorePlanets extends Terminal {

	public PlayerIdWithMorePlanets() {
		super("playerId", "playerIdWithMorePlanets");
	}

	@Override
	public Object evaluate(List<Node> arguments, UserProgram userProgram,
			HashMap<String, Object> returnAddr) {
		Planetdominos dominos = (Planetdominos) userProgram;
		int pIDMorePlanets = 1;
		int highestNumberOfPlanets = dominos.getGame().MyPlanets(pIDMorePlanets).size();
		for (Integer pID: dominos.getGame().getNumPlayersFromFleets()){
			if (highestNumberOfPlanets < dominos.getGame().MyPlanets(pID).size()){
				highestNumberOfPlanets = dominos.getGame().MyPlanets(pID).size();
				pIDMorePlanets = pID;
			}
		}
		return pIDMorePlanets;
	}

}