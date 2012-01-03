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
		int pIDLessFleets = 1;
		int lowestNumberOfFleets = dominos.getGame().MyFleets(pIDLessFleets).size();
		for (int pID = 2; pID <= dominos.getNumberOfPlayers(); pID++){
			if (lowestNumberOfFleets > dominos.getGame().MyFleets(pID).size()){
				lowestNumberOfFleets = dominos.getGame().MyFleets(pID).size();
				pIDLessFleets = pID;
			}
		}
		return pIDLessFleets;
	}
}