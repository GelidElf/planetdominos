package app.planetdominos.playerIds;

import java.util.ArrayList;
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
		List<Integer> numPlayersFromPlanets = new ArrayList<Integer>(dominos.getGame().gonzaloCaneladaLorenaPrieto_getNumPlayersFromPlanets());
		if(numPlayersFromPlanets.size() == 0){
			return 0; 
		}
		if(numPlayersFromPlanets.size() == 1){
			return numPlayersFromPlanets.get(0);
		}
		int pIDLessPlanets = numPlayersFromPlanets.get(0);
		int lowestNumberOfPlanets = dominos.getGame().MyPlanets(pIDLessPlanets).size();
		for (Integer pID:numPlayersFromPlanets){
			if (lowestNumberOfPlanets > dominos.getGame().MyPlanets(pID).size()){
				lowestNumberOfPlanets = dominos.getGame().MyPlanets(pID).size();
				pIDLessPlanets = pID;
			}
		}
		return pIDLessPlanets;
	}

}