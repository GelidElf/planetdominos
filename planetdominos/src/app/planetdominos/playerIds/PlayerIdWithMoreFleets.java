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

public class PlayerIdWithMoreFleets extends Terminal {

	public PlayerIdWithMoreFleets() {
		super("playerId", "playerIdWithMoreFleets");
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
		int pIDMoreFleets = numPlayersFromFleets[0];
		int highestNumberOfFleets = dominos.getGame().MyFleets(pIDMoreFleets).size();
		for (int i = 1; i < numPlayersFromFleets.length;i++){
			if (highestNumberOfFleets < dominos.getGame().MyFleets(numPlayersFromFleets[i]).size()){
				highestNumberOfFleets = dominos.getGame().MyFleets(numPlayersFromFleets[i]).size();
				pIDMoreFleets = numPlayersFromFleets[i];
			}
		}
		return pIDMoreFleets;
	}
}