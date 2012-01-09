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
		int pIDMoreFleets = 1;
		int highestNumberOfFleets = dominos.getGame().MyFleets(pIDMoreFleets).size();
		for (int pID = 2; pID <=  dominos.getGame().getNumPlayersFromFleets(); pID++){
			if (highestNumberOfFleets < dominos.getGame().MyFleets(pID).size()){
				highestNumberOfFleets = dominos.getGame().MyFleets(pID).size();
				pIDMoreFleets = pID;
			}
		}
		return pIDMoreFleets;
	}
}