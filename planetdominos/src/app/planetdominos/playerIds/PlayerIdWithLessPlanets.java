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
		Integer[] numPlayersFromPlanets = (Integer[]) dominos.getGame().getNumPlayersFromPlanets().toArray();
		if(numPlayersFromPlanets.length == 0){
			return 0; 
		}
		if(numPlayersFromPlanets.length == 1){
			return numPlayersFromPlanets[0];
		}
		int pIDLessPlanets = numPlayersFromPlanets[0];
		int lowestNumberOfPlanets = dominos.getGame().MyPlanets(pIDLessPlanets).size();
		for (int i = 1; i < numPlayersFromPlanets.length; i++){
			if (lowestNumberOfPlanets > dominos.getGame().MyPlanets(numPlayersFromPlanets[i]).size()){
				lowestNumberOfPlanets = dominos.getGame().MyPlanets(numPlayersFromPlanets[i]).size();
				pIDLessPlanets = numPlayersFromPlanets[i];
			}
		}
		return pIDLessPlanets;
	}

}