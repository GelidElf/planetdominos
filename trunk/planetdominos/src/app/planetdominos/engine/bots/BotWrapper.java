package app.planetdominos.engine.bots;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import app.gpwars.engine.Game;
import app.gpwars.engine.Order;
import app.gpwars.engine.Planet;
import app.gpwars.engine.Player;

public class BotWrapper extends Player{
	Game g;

	public List<Order> DoTurn(Game game) {
		g = game;
		List<Order> botOrders = new ArrayList<Order>();

		/* Paste here the logic of your bot. Tipically, this logic should be parsed by the BotTranslator
		 * 
		 * 
		 * 
		 */
	
		return botOrders;
	}
	
	
	
	
	/*
	 * SUPPORT METHODS:
	 * 
	 * The name of these methods should match the names of your GP operators, exactly as they appear in
	 * your function set. Obviously, they also should provide exactly the same functionality as the GP 
	 * operators, otherwise you will get unexpected results.  
	 * 
	 */

	private List<Planet> My() {
		return g.MyPlanets(this.getID());
	}

	private List<Planet> Enemy() {
		return g.EnemyPlanets(this.getID());
	}

	private List<Planet> Neutral() {
		List<Planet> planets = g.Planets();
		List<Planet> neutrals = new ArrayList<Planet>();

		for (Planet p : planets) {
			if (p.Owner() == 0) {
				neutrals.add(p);
			}
		}

		return neutrals;
	}

	private Planet StrongestPlanet(List<Planet> planets) {
		Planet strongest;
		if (planets.isEmpty()) {
			strongest = null;
		} else {
			strongest = planets.get(0);
			for (Planet p : planets) {
				if (p.NumShips() > strongest.NumShips()) {
					strongest = p;
				}
			}
		}

		return strongest;
	}

	private Planet WeakestPlanet(List<Planet> planets) {
		Planet weakest;
		if (planets.isEmpty()) {
			weakest = null;
		} else {
			weakest = planets.get(0);
			for (Planet p : planets) {
				if (p.NumShips() < weakest.NumShips()) {
					weakest = p;
				}
			}
		}

		return weakest;
	}

	private Planet FastestGrowingPlanet(List<Planet> planets) {
		Planet fastestGrowing;
		if (planets.isEmpty()) {
			fastestGrowing = null;
		} else {
			fastestGrowing = planets.get(0);
			for (Planet p : planets) {
				if (p.GrowthRate() > fastestGrowing.GrowthRate()) {
					fastestGrowing = p;
				}
			}
		}

		return fastestGrowing;
	}

	private Planet RandomPlanet(List<Planet> planets) {
		Planet p;
		Random prng = new Random();

		if (planets.isEmpty()) {
			p = null;
		} else {
			p = planets.get(prng.nextInt(planets.size()));
		}

		return p;
	}


	private int translatePercent(int percent, Planet planet) {
		if (planet != null) {
			return (planet.NumShips() * percent / 100);
		} else {
			return 0;
		}
	}

}

