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

public class PlayerIdWithLessFleets extends Terminal {

	public PlayerIdWithLessFleets() {
		super("playerId", "playerIdWithLessFleets");
	}

	@Override
	public Object evaluate(List<Node> arguments, UserProgram userProgram,
			HashMap<String, Object> returnAddr) {
		Planetdominos dominos = (Planetdominos) userProgram;
		Integer[] numPlayersFromFleets = (Integer[]) dominos.getGame().getNumPlayersFromFleets().toArray();
		if (numPlayersFromFleets.length == 0){
			return 0; //Habra que ver si devolviendo 0 puede que nos haga el dropplayer
		}
		if (numPlayersFromFleets.length == 1){
			return numPlayersFromFleets[0];
		}
		int pIDLessFleets = numPlayersFromFleets[0];
		int lowestNumberOfFleets = dominos.getGame().MyFleets(pIDLessFleets).size();
		for (int i = 1; i < numPlayersFromFleets.length;i++){
			if (lowestNumberOfFleets > dominos.getGame().MyFleets(numPlayersFromFleets[i]).size()){
				lowestNumberOfFleets = dominos.getGame().MyFleets(numPlayersFromFleets[i]).size();
				pIDLessFleets = numPlayersFromFleets[i];
			}
		}
		return pIDLessFleets;
	}
}


