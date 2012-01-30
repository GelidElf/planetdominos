package app.gpwars.engine.bots;

import java.util.ArrayList;
import java.util.List;

import app.gpwars.engine.Game;
import app.gpwars.engine.Order;
import app.gpwars.engine.Planet;
import app.gpwars.engine.Player;

public class ProspectorBot extends Player {
	public List<Order> DoTurn(Game game) {
		List<Order> botOrders = new ArrayList<Order>();
		
		// (1) If we current have a fleet in flight, just do nothing.
		if (game.MyFleets(this.getID()).size() >= 10) {
			return botOrders;
		}
		// (2) Find my strongest planet.
		Planet source = null;
		double sourceScore = Double.MIN_VALUE;
		for (Planet p : game.MyPlanets(this.getID())) {
			double score = (double)p.NumShips() / (1 + p.GrowthRate());
			if (score > sourceScore) {
				sourceScore = score;
				source = p;
			}
		}
		// (3) Find the weakest enemy or neutral planet.
		Planet dest = null;
		double destScore = Double.MIN_VALUE;
		for (Planet p : game.NotMyPlanets(this.getID())) {
			double score = (double)(1 + p.GrowthRate()) / p.NumShips();
			if (score > destScore) {
				destScore = score;
				dest = p;
			}
		}
		// (4) Send half the ships from my strongest planet to the weakest
		// planet that I do not own.
		if (source != null && dest != null) {
			int numShips = source.NumShips() / 2;
			if (numShips == 0)
				numShips = source.NumShips();
			botOrders.add(new Order(source, dest, numShips));
		}
		
		return botOrders;
	}

}

