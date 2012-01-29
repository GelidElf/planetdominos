package app.planetdominos.engine.bots;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import app.gpwars.engine.Game;
import app.gpwars.engine.Order;
import app.gpwars.engine.Planet;
import app.gpwars.engine.Player;

public class Bot300 extends Player {
	Game g;

	@Override
	public List<Order> DoTurn(Game game) {
		g = game;
		List<Order> botOrders = new ArrayList<Order>();
if ( false ) {
if ( and (numShipLessThan (totalOfShipsFromPlayer ( playerIdWithLessFleets ()  ), totalOfShipsFromPlayer ( playerIdWithLessFleets ()  ) ), numPlanetsLessThan (totalOfPlanetsFromPlayer ( playerIdWithMoreShips ()  ), totalOfPlanetsFromPlayer ( playerIdWithLessShips ()  ) ) ) ) {
if ( numFleetsEquals (totalOfFleetsFromPlayer ( playerIdWithLessFleets ()  ), totalOfFleetsFromPlayer ( playerIdWithLessFleets ()  ) ) ) {
if ( numFleetsEquals (totalOfFleetsFromPlayer ( playerIdWithLessPlanets ()  ), totalOfFleetsFromPlayer ( playerIdWithLessFleets ()  ) ) ) {
botOrders.addAll(issueOrderPercentageOfDestination ( myFastestGrowing () , slowestGrowing ( enemy ()  ), porcentaje075 ()  ) );
} 
 else {if ( numPlanetsEquals (totalOfPlanetsFromPlayer ( playerIdWithLessPlanets ()  ), totalOfPlanetsFromPlayer ( playerIdWithLessPlanets ()  ) ) ) {
botOrders.addAll(overwhelm (myWeakest () , slowestGrowing ( enemy ()  ) ));
} 
 else {if ( fleetInFlight ()  ) {
if ( or (fleetInFlight () , numShipEquals (totalOfShipsFromPlayer ( playerIdWithLessShips ()  ), totalOfShipsFromPlayer ( playerIdWithMoreShips ()  ) ) ) ) {
if ( and (numFleetsEquals (totalOfFleetsFromPlayer ( playerIdWithMorePlanets ()  ), totalOfFleetsFromPlayer ( playerIdWithLessPlanets ()  ) ), numShipGreaterThan (totalOfShipsFromPlayer ( playerIdWithMoreShips ()  ), totalOfShipsFromPlayer ( playerIdWithMorePlanets ()  ) ) ) ) {
botOrders.addAll(issueOrderPercentageOfOrigin ( myFastestGrowing () , weakest ( ownedBy ( playerIdWithMorePlanets ()  ) ), porcentaje015 ()  ) );
} 
 else {botOrders.addAll(issueOrderPercentageOfOrigin ( myWeakest () , strongest ( neutral ()  ), porcentaje025 ()  ) );
} } 
 else {botOrders.addAll(overwhelm (myFastestGrowing () , weakest ( all ()  ) ));
} } 
 else {botOrders.addAll(issueOrderPercentageOfDestination ( myWeakest () , weakest ( enemy ()  ), porcentaje020 ()  ) );
} } } } 
 else {if ( and (numShipEquals (totalOfShipsFromPlayer ( playerIdWithMorePlanets ()  ), totalOfShipsFromPlayer ( playerIdWithLessFleets ()  ) ), numPlanetsLessThan (totalOfPlanetsFromPlayer ( playerIdWithLessFleets ()  ), totalOfPlanetsFromPlayer ( playerIdWithLessFleets ()  ) ) ) ) {
botOrders.addAll(issueOrderToNearestPercentageOfDestination ( myWeakest () , all () , porcentaje085 ()  ) );
} 
 else {botOrders.addAll(overwhelm (myFastestGrowing () , weakest ( all ()  ) ));
} } } 
 else {if ( numPlanetsGreaterThan (totalOfPlanetsFromPlayer ( playerIdWithMoreShips ()  ), totalOfPlanetsFromPlayer ( playerIdWithMoreFleets ()  ) ) ) {
if ( numPlanetsLessThan (totalOfPlanetsFromPlayer ( playerIdWithLessFleets ()  ), totalOfPlanetsFromPlayer ( playerIdWithLessFleets ()  ) ) ) {
if ( numPlanetsEquals (totalOfPlanetsFromPlayer ( playerIdWithLessFleets ()  ), totalOfPlanetsFromPlayer ( playerIdWithLessPlanets ()  ) ) ) {
botOrders.addAll(issueOrderToNearestPercentageOfDestination ( myStrongest () , enemy () , porcentaje095 ()  ) );
} 
 else {if ( or (numPlanetsEquals (totalOfPlanetsFromPlayer ( playerIdWithLessFleets ()  ), totalOfPlanetsFromPlayer ( playerIdWithLessPlanets ()  ) ), and (true, numFleetsEquals (totalOfFleetsFromPlayer ( playerIdWithMorePlanets ()  ), totalOfFleetsFromPlayer ( playerIdWithLessPlanets ()  ) ) ) ) ) {
noOrder (); } 
 else {botOrders.addAll(issueOrderPercentageOfOrigin ( mySlowestGrowing () , slowestGrowing ( all ()  ), porcentaje055 ()  ) );
} } } 
 else {if ( numShipEquals (totalOfShipsFromPlayer ( playerIdWithLessFleets ()  ), totalOfShipsFromPlayer ( playerIdWithMorePlanets ()  ) ) ) {
botOrders.addAll(overwhelm (mySlowestGrowing () , strongest ( all ()  ) ));
} 
 else {botOrders.addAll(issueOrderToNearestPercentageOfDestination ( myStrongest () , enemy () , porcentaje100 ()  ) );
} } } 
 else {botOrders.addAll(issueOrderToNearestPercentageOfDestination ( myWeakest () , all () , porcentaje010 ()  ) );
} } } 
 else {if ( not ( numShipGreaterThan (totalOfShipsFromPlayer ( playerIdWithMoreFleets ()  ), totalOfShipsFromPlayer ( playerIdWithLessShips ()  ) ) ) ) {
if ( and (numPlanetsLessThan (totalOfPlanetsFromPlayer ( playerIdWithMoreShips ()  ), totalOfPlanetsFromPlayer ( playerIdWithLessFleets ()  ) ), numShipGreaterThan (totalOfShipsFromPlayer ( playerIdWithLessPlanets ()  ), totalOfShipsFromPlayer ( playerIdWithMorePlanets ()  ) ) ) ) {
noOrder (); } 
 else {if ( numFleetsLessThan (totalOfFleetsFromPlayer ( playerIdWithLessShips ()  ), totalOfFleetsFromPlayer ( playerIdWithLessPlanets ()  ) ) ) {
botOrders.addAll(issueOrderPercentageOfDestination ( myStrongest () , slowestGrowing ( ownedBy ( playerIdWithLessShips ()  ) ), porcentaje095 ()  ) );
} 
 else {botOrders.addAll(issueOrderPercentageOfOrigin ( mySlowestGrowing () , weakest ( all ()  ), porcentaje100 ()  ) );
} } } 
 else {if ( playerIDEquals (playerIdWithMoreShips () , playerIdWithMoreShips ()  ) ) {
if ( numPlanetsEquals (totalOfPlanetsFromPlayer ( playerIdWithMoreShips ()  ), totalOfPlanetsFromPlayer ( playerIdWithMoreFleets ()  ) ) ) {
botOrders.addAll(issueOrderPercentageOfOrigin ( myStrongest () , slowestGrowing ( enemy ()  ), porcentaje095 ()  ) );
} 
 else {if ( and (or (numFleetsEquals (totalOfFleetsFromPlayer ( playerIdWithLessShips ()  ), totalOfFleetsFromPlayer ( playerIdWithLessFleets ()  ) ), false ), numShipGreaterThan (totalOfShipsFromPlayer ( playerIdWithMoreFleets ()  ), totalOfShipsFromPlayer ( playerIdWithMorePlanets ()  ) ) ) ) {
noOrder (); } 
 else {botOrders.addAll(issueOrderPercentageOfOrigin ( myFastestGrowing () , slowestGrowing ( neutral ()  ), porcentaje095 ()  ) );
} } } 
 else {if ( or (numPlanetsLessThan (totalOfPlanetsFromPlayer ( playerIdWithMoreShips ()  ), totalOfPlanetsFromPlayer ( playerIdWithLessShips ()  ) ), playerIDEquals (playerIdWithLessFleets () , playerIdWithLessShips ()  ) ) ) {
noOrder (); } 
 else {if ( and (or (false, false ), numShipGreaterThan (totalOfShipsFromPlayer ( playerIdWithMoreShips ()  ), totalOfShipsFromPlayer ( playerIdWithLessFleets ()  ) ) ) ) {
botOrders.addAll(issueOrderToNearestPercentageOfDestination ( myWeakest () , all () , porcentaje085 ()  ) );
} 
 else {botOrders.addAll(overwhelm (myFastestGrowing () , weakest ( neutral ()  ) ));
} } } } } 
		
		return botOrders;
	}


	/*
	 * SUPPORT METHODS:
	 * 
	 * The name of these methods should match the names of your GP operators,
	 * exactly as they appear in your function set. Obviously, they also should
	 * provide exactly the same functionality as the GP operators, otherwise you
	 * will get unexpected results.
	 */

	//Conditions
	protected Boolean and(Boolean a, Boolean b) {
		return a && b;
	}

	protected Boolean fleetInFlight() {
		return g.MyFleets(getID()).size() >= 1;
	}

	protected Boolean not(Boolean a) {
		return !a;
	}

	protected boolean numFleetsEquals(int a, int b) {
		return a == b;
	}

	protected Boolean numFleetsGreaterThan(int a, int b) {
		return a > b;
	}

	protected Boolean numFleetsLessThan(int a, int b) {
		return a < b;
	}

	protected boolean numPlanetsEquals(int a,
			int b) {
		return a == b;
	}

	protected Boolean numPlanetsGreaterThan(int a, int b) {
		return a > b;
	}

	protected Boolean numPlanetsLessThan(int a,
			int b) {
		return a < b;
	}

	protected Boolean numShipEquals(int a, int b) {
		return a == b;
	}

	protected Boolean numShipGreaterThan(int a, int b) {
		return a > b;
	}

	protected Boolean numShipLessThan(int a, int b) {
		return a < b;
	}

	protected Boolean or(Boolean a, Boolean b) {
		return a || b;
	}

	protected Boolean playerIDEquals(int a, int b) {
		return a == b;
	}
	
	//Cuantities
	protected int totalOfFleetsFromPlayer(int playerId) {
		return g.MyFleets(playerId).size();
	}

	protected int totalOfPlanetsFromPlayer(int playerId) {
		return g.MyPlanets(playerId).size();
	}

	protected int totalOfShipsFromPlayer(int playerId) {
		return g.NumShips(playerId);
	}

	//MySelectors
	protected Planet myFastestGrowing() {
		List<Planet> planetasEntrada = g.MyPlanets(getID());
		if (planetasEntrada.size() == 0){
			return null;
		}
		Planet fastestPlanetFound = planetasEntrada.get(0);
		for (Planet p: planetasEntrada){
			if (p.GrowthRate() > fastestPlanetFound.GrowthRate()){
				fastestPlanetFound = p;
			}
		}
		return fastestPlanetFound;
	}


	protected Planet mySlowestGrowing() {
		List<Planet> planetasEntrada = g.MyPlanets(getID());
		if (planetasEntrada.size() == 0) {
			return null;
		}
		Planet slowestPlanetFound = planetasEntrada.get(0);
		for (Planet p : planetasEntrada) {
			if (p.GrowthRate() < slowestPlanetFound.GrowthRate()) {
				slowestPlanetFound = p;
			}
		}
		return slowestPlanetFound;
	}

	protected Planet myStrongest() {
		List<Planet> planetasEntrada = g.MyPlanets(getID());
		if (planetasEntrada.size() == 0){
			return null;
		}
		Planet strongestPlanetFound = planetasEntrada.get(0);
		for (Planet p: planetasEntrada){
			if (p.NumShips() > strongestPlanetFound.NumShips()){
				strongestPlanetFound = p;
			}
		}
		return strongestPlanetFound;
	}
	
	protected Planet myWeakest() {
		List<Planet> planetasEntrada = g.MyPlanets(getID());
		if (planetasEntrada.size() == 0){
			return null;
		}
		Planet strongestPlanetFound = planetasEntrada.get(0);
		for (Planet p: planetasEntrada){
			if (p.NumShips() < strongestPlanetFound.NumShips()){
				strongestPlanetFound = p;
			}
		}
		return strongestPlanetFound;
	}
	
	//Orders
	protected List<Order> overwhelm(Planet origen, Planet destino) {
		if ((origen == null) || (destino == null)) {
			return Collections.emptyList();
		}

		int numeroNavesAEnviar = 0;

		if (origen.NumShips() > destino.NumShips()) {
			numeroNavesAEnviar = destino.NumShips();
		} else {
			numeroNavesAEnviar = origen.NumShips();
		}

		Order order = new Order(origen, destino, numeroNavesAEnviar);
		List<Order> listaOrdenes = new ArrayList<Order>();
		listaOrdenes.add(order);

		return listaOrdenes;
	}
	

	
	protected Collection<? extends Order> issueOrderPercentageOfDestination(
			Planet origen, Planet destino, Double porcentaje) {
		if ((origen == null) || (destino == null)){
			return Collections.emptyList();
		}
		
		int numeroNavesAEnviar = new Double(destino.NumShips()*porcentaje).intValue();
		
		if (origen.NumShips() < numeroNavesAEnviar){
			numeroNavesAEnviar = origen.NumShips();
		}
		Order order = new Order(origen, destino, numeroNavesAEnviar);
		List<Order> listaOrdenes = new ArrayList<Order>();
		listaOrdenes.add(order);
		
		return listaOrdenes;
	}
	
	protected Collection<? extends Order> issueOrderPercentageOfOrigin(
			Planet origen, Planet destino, Double porcentaje){
		if ((origen == null) || (destino == null)){
			return Collections.emptyList();
		}
		
		int numeroNavesAEnviar = new Double(origen.NumShips()*porcentaje).intValue();
		
		Order order = new Order(origen, destino, numeroNavesAEnviar);
		List<Order> listaOrdenes = new ArrayList<Order>();
		listaOrdenes.add(order);
		
		return listaOrdenes;
	}
	
	protected Collection<? extends Order> issueOrderToNearestPercentageOfDestination(
			Planet origen, List<Planet> planetasDestino, Double porcentaje){
		
		if ((planetasDestino.size() == 0) ||(origen == null)){
			return Collections.emptyList();
		}
		
		Planet nearestPlanet = planetasDestino.get(0);
		int nearestPlanetDistance = g.distance(origen, planetasDestino.get(0));
		for (Planet p: planetasDestino){
			int distance = g.distance(origen, p);
			if (distance < nearestPlanetDistance){
				nearestPlanet = p;
				nearestPlanetDistance = distance;
			}else{
				if (distance == nearestPlanetDistance){
					if (p.GrowthRate() > nearestPlanet.GrowthRate()){
						nearestPlanet = p;
					}
				}
			}
		}
		
		int numeroNavesAEnviar = new Double(nearestPlanet.NumShips()*porcentaje).intValue();
		if (origen.NumShips() < numeroNavesAEnviar){
			numeroNavesAEnviar = origen.NumShips();
		}
		Order order = new Order(origen, nearestPlanet, numeroNavesAEnviar);
		List<Order> listaOrdenes = new ArrayList<Order>();
		listaOrdenes.add(order);
		
		return listaOrdenes;
	}
	
	protected Collection<? extends Order> issueOrderToNearestPercentageOfOrigin(
			Planet origen, List<Planet> planetasDestino, Double porcentaje){
		if ((planetasDestino.size() == 0) || (origen == null)){
			return Collections.emptyList();
		}
		
		Planet nearestPlanet = planetasDestino.get(0);
		int nearestPlanetDistance = g.distance(origen, planetasDestino.get(0));
		for (Planet p: planetasDestino){
			int distance = g.distance(origen, p);
			if (distance < nearestPlanetDistance){
				nearestPlanet = p;
				nearestPlanetDistance = distance;
			}else{
				if (distance == nearestPlanetDistance){
					if (p.GrowthRate() > nearestPlanet.GrowthRate()){
						nearestPlanet = p;
					}
				}
			}
		}
		
		int numeroNavesAEnviar = new Double(origen.NumShips()*porcentaje).intValue();
		Order order = new Order(origen, nearestPlanet, numeroNavesAEnviar);
		List<Order> listaOrdenes = new ArrayList<Order>();
		listaOrdenes.add(order);
		
		return listaOrdenes;
	}

	protected Collection<? extends Order> noOrder() {
		return Collections.emptyList();
	}
	
	protected Collection <? extends Order> Prog2(Collection<? extends Order> listaOrdenes1, Collection<? extends Order> listaOrdenes2){
		List<Order> listaOrdenes = new ArrayList<Order>();
		listaOrdenes.addAll(listaOrdenes1);
		listaOrdenes.addAll(listaOrdenes2);
		return listaOrdenes;
	}
	
	//Porcentajes
	protected Double porcentaje005() {
		return Double.valueOf(0.05);
	}

	protected Double porcentaje010() {
		return Double.valueOf(0.1);
	}

	protected Double porcentaje015() {
		return Double.valueOf(0.15);
	}

	protected Double porcentaje020() {
		return Double.valueOf(0.2);
	}

	protected Double porcentaje025() {
		return Double.valueOf(0.25);
	}

	protected Double porcentaje030() {
		return Double.valueOf(0.03);
	}

	protected Double porcentaje035() {
		return Double.valueOf(0.35);
	}

	protected Double porcentaje040() {
		return Double.valueOf(0.45);
	}

	protected Double porcentaje050() {
		return Double.valueOf(0.5);
	}

	protected Double porcentaje055() {
		return Double.valueOf(0.55);
	}

	protected Double porcentaje060() {
		return Double.valueOf(0.6);
	}

	protected Double porcentaje065() {
		return Double.valueOf(0.65);
	}

	protected Double porcentaje070() {
		return Double.valueOf(0.7);
	}

	protected Double porcentaje075() {
		return Double.valueOf(0.75);
	}

	protected Double porcentaje080() {
		return Double.valueOf(0.8);
	}

	protected Double porcentaje085() {
		return Double.valueOf(0.85);
	}

	protected Double porcentaje090() {
		return Double.valueOf(0.90);
	}

	protected Double porcentaje095() {
		return Double.valueOf(0.95);
	}

	protected Double porcentaje100() {
		return Double.valueOf(1);
	}
	
	//Planetlists
	
	protected List<Planet> all(){
		return g.Planets();
	}
	
	protected List<Planet> enemy() {
		return g.EnemyPlanets(getID());
	}
	
	protected List<Planet> my() {
		return g.MyPlanets(getID());
	}
	
	protected List<Planet> neutral(){
		return g.NeutralPlanets();
	}
	
	protected List<Planet> ownedBy(int playerId){
		return g.MyPlanets(playerId);
	}
	
	//playerIds
	protected int myId() {
		return getID();
	}

	protected int playerIdWithLessFleets() {
		int pIDLessFleets = 1;
		int lowestNumberOfFleets = g.MyFleets(pIDLessFleets).size();
		for (Integer pID : g.getNumPlayersFromFleets()) {
			if (lowestNumberOfFleets > g.MyFleets(pID).size()) {
				lowestNumberOfFleets = g.MyFleets(pID).size();
				pIDLessFleets = pID;
			}
		}
		return pIDLessFleets;
	}

	protected int playerIdWithLessPlanets() {
		int pIDLessPlanets = 1;
		int lowestNumberOfPlanets = g.MyPlanets(pIDLessPlanets).size();
		for (Integer pID : g.getNumPlayersFromPlanets()) {
			if (lowestNumberOfPlanets > g.MyPlanets(pID).size()) {
				lowestNumberOfPlanets = g.MyPlanets(pID).size();
				pIDLessPlanets = pID;
			}
		}
		return pIDLessPlanets;
	}

	protected int playerIdWithLessShips() {
		int pIDLessShips = 1;
		int lowestNumberOfShips = g.NumShips(pIDLessShips);
		for (Integer pID : g.getNumPlayersFromFleets()) {
			if (lowestNumberOfShips > g.NumShips(pID)) {
				lowestNumberOfShips = g.NumShips(pID);
				pIDLessShips = pID;
			}
		}
		return pIDLessShips;
	}

	protected int playerIdWithMoreFleets() {
		int pIDMoreFleets = 1;
		int highestNumberOfFleets = g.MyFleets(pIDMoreFleets).size();
		for (Integer pID : g.getNumPlayersFromFleets()) {
			if (highestNumberOfFleets < g.MyFleets(pID).size()) {
				highestNumberOfFleets = g.MyFleets(pID).size();
				pIDMoreFleets = pID;
			}
		}
		return pIDMoreFleets;
	}

	protected int playerIdWithMorePlanets() {
		int pIDMorePlanets = 1;
		int highestNumberOfPlanets = g.MyPlanets(pIDMorePlanets).size();
		for (Integer pID : g.getNumPlayersFromPlanets()) {
			if (highestNumberOfPlanets > g.MyPlanets(pID).size()) {
				highestNumberOfPlanets = g.MyPlanets(pID).size();
				pIDMorePlanets = pID;
			}
		}
		return pIDMorePlanets;
	}
	
	protected int playerIdWithMoreShips() {
		int pIDMoreShips = 1;
		int highestNumberOfShips = g.NumShips(pIDMoreShips);
		for (Integer pID : g.getNumPlayersFromShips()) {
			if (highestNumberOfShips < g.NumShips(pID)) {
				highestNumberOfShips = g.NumShips(pID);
				pIDMoreShips = pID;
			}
		}
		return pIDMoreShips;
	}
	
	//Selectors
	protected Planet fastestGrowing(List<Planet> planetasEntrada) {
		if (planetasEntrada.size() == 0) {
			return null;
		}
		Planet fastestGrowing = planetasEntrada.get(0);
		for (Planet p : planetasEntrada) {
			if (p.GrowthRate() > fastestGrowing.GrowthRate()) {
				fastestGrowing = p;
			}
		}
		return fastestGrowing;
	}

	protected Planet slowestGrowing(List<Planet> planetasEntrada) {
		if (planetasEntrada.size() == 0) {
			return null;
		}
		Planet slowestPlanetFound = planetasEntrada.get(0);
		for (Planet p : planetasEntrada) {
			if (p.GrowthRate() < slowestPlanetFound.GrowthRate()) {
				slowestPlanetFound = p;
			}
		}
		return slowestPlanetFound;
	}
	
	protected Planet strongest(List<Planet> planetasEntrada) {
		if (planetasEntrada.size() == 0) {
			return null;
		}
		Planet strongestPlanetFound = planetasEntrada.get(0);
		for (Planet p : planetasEntrada) {
			if (p.NumShips() > strongestPlanetFound.NumShips()) {
				strongestPlanetFound = p;
			}
		}
		return strongestPlanetFound;
	}

	protected Planet weakest(List<Planet> planetasEntrada) {
		if (planetasEntrada.size() == 0) {
			return null;
		}
		Planet weakestPlanetFound = planetasEntrada.get(0);
		for (Planet p : planetasEntrada) {
			if (p.NumShips() < weakestPlanetFound.NumShips()) {
				weakestPlanetFound = p;
			}
		}
		return weakestPlanetFound;
	}

}
