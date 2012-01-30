package app.gpwars.engine.bots;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import app.gpwars.engine.Game;
import app.gpwars.engine.Order;
import app.gpwars.engine.Planet;
import app.gpwars.engine.Player;

public class RandomBot extends Player {

	

	@Override
	public List<Order> DoTurn(Game game) {
		List<Order> botOrders = new ArrayList<Order>();
		
		// (1) If we current have a fleet in flight, then do nothing until it
		// arrives.
		if (game.MyFleets(this.getID()).size() >= 1) {
			return botOrders;
		}
		// (2) Pick one of my planets at random.
		Random r = new Random();
		Planet source = null;
		List<Planet> p = game.MyPlanets(this.getID());
		if (p.size() > 0) {
			source = p.get(r.nextInt(p.size()));
		}
		// (3) Pick a target planet at random.
		Planet dest = null;
		p = game.Planets();
		if (p.size() > 0) {
			dest = p.get(r.nextInt(p.size()));
		}
		// (4) Send half the ships from source to dest.
		if (source != null && dest != null) {
			int numShips = source.NumShips() / 2;
			if (numShips == 0)
				numShips = source.NumShips();
			botOrders.add(new Order(source, dest, numShips));
		}
		
		return botOrders;
	}

}

