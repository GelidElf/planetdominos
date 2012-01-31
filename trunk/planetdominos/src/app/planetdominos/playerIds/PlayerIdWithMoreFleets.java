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

public class PlayerIdWithMoreFleets extends Terminal {

	public PlayerIdWithMoreFleets() {
		super("playerId", "playerIdWithMoreFleets");
	}

	@Override
	public Object evaluate(List<Node> arguments, UserProgram userProgram,
			HashMap<String, Object> returnAddr) {
		Planetdominos dominos = (Planetdominos) userProgram;
		List<Integer> numPlayersFromFleets = new ArrayList<Integer>(dominos.getGame().gonzaloCaneladaLorenaPrieto_getNumPlayersFromFleets());
		if (numPlayersFromFleets.size() == 0){
			return 0; 
		}
		if (numPlayersFromFleets.size() == 1){
			return numPlayersFromFleets.get(0);
		}
		int pIDMoreFleets = numPlayersFromFleets.get(0);
		int highestNumberOfFleets = dominos.getGame().MyFleets(pIDMoreFleets).size();
		for (Integer pID:numPlayersFromFleets){
			if (highestNumberOfFleets < dominos.getGame().MyFleets(pID).size()){
				highestNumberOfFleets = dominos.getGame().MyFleets(pID).size();
				pIDMoreFleets = pID;
			}
		}
		return pIDMoreFleets;
	}
}