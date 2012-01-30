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

public class PlayerIdWithLessFleets extends Terminal {

	public PlayerIdWithLessFleets() {
		super("playerId", "playerIdWithLessFleets");
	}

	@Override
	public Object evaluate(List<Node> arguments, UserProgram userProgram,
			HashMap<String, Object> returnAddr) {
		Planetdominos dominos = (Planetdominos) userProgram;
		List<Integer> numPlayersFromFleets = new ArrayList<Integer>(dominos.getGame().getNumPlayersFromFleets());
		if (numPlayersFromFleets.size() == 0){
			return 0; //Habra que ver si devolviendo 0 puede que nos haga el dropplayer
		}
		if (numPlayersFromFleets.size() == 1){
			return numPlayersFromFleets.get(0);
		}
		int pIDLessFleets = numPlayersFromFleets.get(0);
		int lowestNumberOfFleets = dominos.getGame().MyFleets(pIDLessFleets).size();
		for (Integer pID:numPlayersFromFleets){
			if (lowestNumberOfFleets > dominos.getGame().MyFleets(pID).size()){
				lowestNumberOfFleets = dominos.getGame().MyFleets(pID).size();
				pIDLessFleets = pID;
			}
		}
		return pIDLessFleets;
	}
}


