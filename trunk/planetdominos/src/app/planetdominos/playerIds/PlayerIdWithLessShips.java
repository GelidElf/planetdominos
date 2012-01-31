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

public class PlayerIdWithLessShips extends Terminal {

	public PlayerIdWithLessShips() {
		super("playerId", "playerIdWithLessShips");
	}

	@Override
	public Object evaluate(List<Node> arguments, UserProgram userProgram,
			HashMap<String, Object> returnAddr) {
		Planetdominos dominos = (Planetdominos) userProgram;
		int pIDLessShips = 1;
		int lowestNumberOfShips = dominos.getGame().NumShips(pIDLessShips);
		for (Integer pID: dominos.getGame().gonzaloCaneladaLorenaPrieto_getNumPlayersFromFleets()){
			if (lowestNumberOfShips > dominos.getGame().NumShips(pID)){
				lowestNumberOfShips = dominos.getGame().NumShips(pID);
				pIDLessShips = pID;
			}
		}
		return pIDLessShips;
	}

}