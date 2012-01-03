package app.planetdominos.conditions;

import java.util.HashMap;
import java.util.List;

import app.planetdominos.Planetdominos;

import progen.kernel.functions.Terminal;
import progen.kernel.tree.Node;
import progen.userprogram.UserProgram;

/**
 * 
 * @author 100061031 y 100082433
 *
 */

public class FleetInFlightCondition extends Terminal {

	private static final int USER_BOT_ID = 1;

	public FleetInFlightCondition() {
		super("condition", "fleetInFlight");
	}

	@Override
	public Object evaluate(List<Node> arguments, UserProgram userProgram,
			HashMap<String, Object> returnAddr) {
		Planetdominos prog = (Planetdominos) userProgram;
		return prog.getGame().MyFleets(USER_BOT_ID).size() >= 1;
	}

}