package app.planetdominos.engine.bots;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import app.gpwars.engine.Game;
import app.gpwars.engine.Order;
import app.gpwars.engine.Planet;
import app.gpwars.engine.Player;

public class BotWrapper extends Player {
	Game g;

	@Override
	public List<Order> DoTurn(Game game) {
		g = game;
		List<Order> botOrders = new ArrayList<Order>();
		//
		return botOrders;
	}

	private Boolean numShipEquals(int a, int b) {
		return a == b;
	}

	private int playerIdWithMoreShips() {
		int pIDMoreShips = 1;
		int highestNumberOfShips =g.NumShips(pIDMoreShips);
		for (Integer pID: g.getNumPlayersFromShips()){
			if (highestNumberOfShips <g.NumShips(pID)){
				highestNumberOfShips =g.NumShips(pID);
				pIDMoreShips = pID;
			}
		}
		return pIDMoreShips;
	}

	private Boolean or(Boolean a, Boolean b) {
		return a || b;
	}

	private Boolean and(Boolean a, Boolean b) {
		return a && b;
	}

	private Boolean not(Boolean a) {
		return !a;
	}

	private Boolean fleetInFlight() {
		return g.MyFleets(1).size() >= 1;
	}

	private Boolean numPlanetsGreaterThan(int a, int b) {
		return a > b;
	}

	private Object totalOfPlanetsFromPlayer(Object myId) {
		// TODO Auto-generated method stub
		return null;
	}

	private int playerIdWithLessShips() {
		int pIDLessShips = 1;
		int lowestNumberOfShips = g.NumShips(pIDLessShips);
		for (Integer pID: g.getNumPlayersFromFleets()){
			if (lowestNumberOfShips > g.NumShips(pID)){
				lowestNumberOfShips = g.NumShips(pID);
				pIDLessShips = pID;
			}
		}
		return pIDLessShips;
	}

	private int playerIdWithLessPlanets() {
		int pIDLessPlanets = 1;
		int lowestNumberOfPlanets = g.MyPlanets(pIDLessPlanets).size();
		for (Integer pID: g.getNumPlayersFromFleets()){
			if (lowestNumberOfPlanets > g.MyPlanets(pID).size()){
				lowestNumberOfPlanets = g.MyPlanets(pID).size();
				pIDLessPlanets = pID;
			}
		}
		return pIDLessPlanets;
	}

	private int playerIdWithLessFleets() {
		int pIDLessFleets = 1;
		int lowestNumberOfFleets = g.MyFleets(pIDLessFleets).size();
		for (int pID = 2; pID <= g.getNumPlayersFromFleets()); pID++){
			if (lowestNumberOfFleets > g.MyFleets(pID).size()){
				lowestNumberOfFleets = g.MyFleets(pID).size();
				pIDLessFleets = pID;
			}
		}
		return pIDLessFleets;
	}

	private Boolean numFleetsGreaterThan(int a, int b) {
		return a > b;
	}

	private int myId() {
		return 1;
	}

	private Boolean numFleetsLessThan(int a, int b) {
		return a < b;
	}

	private int totalOfFleetsFromPlayer(int playerId) {
		return g.MyFleets(playerId).size();
	}

	private int totalOfPlanetsFromPlayer(int playerId) {
		return g.MyPlanets(playerId).size();
	}

	private int totalOfShipsFromPlayer(int playerId) {
		return g.NumShips(playerId);
	}


	/*
	 * SUPPORT METHODS:
	 *
	 * The name of these methods should match the names of your GP operators,
	 * exactly as they appear in your function set. Obviously, they also should
	 * provide exactly the same functionality as the GP operators, otherwise you
	 * will get unexpected results.
	 */

	private List<Planet> My() {
		return g.MyPlanets(getID());
	}

	private List<Planet> Enemy() {
		return g.EnemyPlanets(getID());
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
