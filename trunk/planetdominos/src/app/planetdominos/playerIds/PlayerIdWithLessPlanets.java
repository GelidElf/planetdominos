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

public class PlayerIdWithLessPlanets extends Terminal {

	public PlayerIdWithLessPlanets() {
		super("playerId", "playerIdWithLessPlanets");
	}

	@Override
	public Object evaluate(List<Node> arguments, UserProgram userProgram,
			HashMap<String, Object> returnAddr) {
		Planetdominos dominos = (Planetdominos) userProgram;
		int pIDLessPlanets = 1;
		int lowestNumberOfPlanets = dominos.getGame().MyPlanets(pIDLessPlanets).size();
		for (Integer pID: dominos.getGame().getNumPlayersFromFleets()){
			if (lowestNumberOfPlanets > dominos.getGame().MyPlanets(pID).size()){
				lowestNumberOfPlanets = dominos.getGame().MyPlanets(pID).size();
				pIDLessPlanets = pID;
			}
		}
		return pIDLessPlanets;
	}

}