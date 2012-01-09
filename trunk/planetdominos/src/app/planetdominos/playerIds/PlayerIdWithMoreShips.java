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

public class PlayerIdWithMoreShips extends Terminal {

	public PlayerIdWithMoreShips() {
		super("playerId", "playerIdWithMoreShips");
	}

	@Override
	public Object evaluate(List<Node> arguments, UserProgram userProgram,
			HashMap<String, Object> returnAddr) {
		Planetdominos dominos = (Planetdominos) userProgram;
		int pIDMoreShips = 1;
		int highestNumberOfShips = dominos.getGame().NumShips(pIDMoreShips);
		for (int pID = 2; pID <= dominos.getGame().getNumPlayersFromShips(); pID++){
			if (highestNumberOfShips < dominos.getGame().NumShips(pID)){
				highestNumberOfShips = dominos.getGame().NumShips(pID);
				pIDMoreShips = pID;
			}
		}
		return pIDMoreShips;
	}

}