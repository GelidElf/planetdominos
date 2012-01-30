package app.gpwars.engine.bots;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import app.gpwars.engine.Game;
import app.gpwars.engine.Order;
import app.gpwars.engine.Planet;
import app.gpwars.engine.Player;

public class RageBot extends Player {

	@Override
	public List<Order> DoTurn(Game game) {
		List<Order> botOrders = new ArrayList<Order>();

		for (Planet source : game.MyPlanets(this.getID())) {
			if (source.NumShips() < 10 * source.GrowthRate()) {
				continue;
			}
			Planet dest = null;
			int bestDistance = 999999;
			for (Planet p : game.EnemyPlanets(this.getID())) {
				int dist = game.distance(source, p);
				if (dist < bestDistance) {
					bestDistance = dist;
					dest = p;
				}
			}
			if (dest != null) {
				if (source.NumShips() == 0)
					return Collections.emptyList();
				botOrders.add(new Order(source, dest, source.NumShips()));
			}
		}
		
		return botOrders;
	}

}
