package app.planetdominos.engine.bots;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import app.gpwars.engine.Game;
import app.gpwars.engine.Order;
import app.gpwars.engine.Planet;
import app.gpwars.engine.Player;

public class Bot000 extends Player {
	Game g;

	@Override
	public List<Order> DoTurn(Game game) {
		g = game;
		List<Order> botOrders = new ArrayList<Order>();
if ( or (or (and (numFleetsLessThan (totalOfFleetsFromPlayer ( playerIdWithLessPlanets ()  ), totalOfFleetsFromPlayer ( playerIdWithLessPlanets ()  ) ), or (fleetInFlight () , numFleetsGreaterThan (totalOfFleetsFromPlayer ( playerIdWithLessPlanets ()  ), totalOfFleetsFromPlayer ( playerIdWithMoreFleets ()  ) ) ) ), numFleetsLessThan (totalOfFleetsFromPlayer ( playerIdWithLessPlanets ()  ), totalOfFleetsFromPlayer ( playerIdWithMoreFleets ()  ) ) ), or (numShipLessThan (totalOfShipsFromPlayer ( playerIdWithLessShips ()  ), totalOfShipsFromPlayer ( playerIdWithLessPlanets ()  ) ), numPlanetsLessThan (totalOfPlanetsFromPlayer ( playerIdWithMoreShips ()  ), totalOfPlanetsFromPlayer ( playerIdWithMoreFleets ()  ) ) ) ) ) {
if ( not ( numPlanetsGreaterThan (totalOfPlanetsFromPlayer ( myId ()  ), totalOfPlanetsFromPlayer ( playerIdWithLessShips ()  ) ) ) ) {
if ( numPlanetsLessThan (totalOfPlanetsFromPlayer ( playerIdWithMorePlanets ()  ), totalOfPlanetsFromPlayer ( playerIdWithLessShips ()  ) ) ) {
if ( not ( numPlanetsLessThan (totalOfPlanetsFromPlayer ( playerIdWithLessFleets ()  ), totalOfPlanetsFromPlayer ( playerIdWithLessShips ()  ) ) ) ) {
botOrders.addAll(issueOrderToNearestPercentageOfOrigin ( myWeakest () , enemy () , porcentaje060 ()  ) );
} 
 else {if ( numPlanetsGreaterThan (totalOfPlanetsFromPlayer ( playerIdWithLessShips ()  ), totalOfPlanetsFromPlayer ( playerIdWithMoreShips ()  ) ) ) {
botOrders.addAll(issueOrderToNearestPercentageOfOrigin ( myFastestGrowing () , all () , porcentaje075 ()  ) );
} 
 else {botOrders.addAll(overwhelm (myWeakest () , slowestGrowing ( enemy ()  ) ));
} } } 
 else {if ( numFleetsEquals (totalOfFleetsFromPlayer ( playerIdWithMoreFleets ()  ), totalOfFleetsFromPlayer ( playerIdWithLessPlanets ()  ) ) ) {
botOrders.addAll(issueOrderPercentageOfDestination ( mySlowestGrowing () , strongest ( all ()  ), porcentaje015 ()  ) );
} 
 else {botOrders.addAll(issueOrderPercentageOfDestination ( myFastestGrowing () , slowestGrowing ( enemy ()  ), porcentaje040 ()  ) );
} } } 
 else {botOrders.addAll(issueOrderPercentageOfDestination ( myWeakest () , fastestGrowing ( neutral ()  ), porcentaje040 ()  ) );

botOrders.addAll(issueOrderPercentageOfDestination ( mySlowestGrowing () , weakest ( all ()  ), porcentaje025 ()  ) );


if ( numPlanetsGreaterThan (totalOfPlanetsFromPlayer ( playerIdWithLessShips ()  ), totalOfPlanetsFromPlayer ( myId ()  ) ) ) {
botOrders.addAll(issueOrderPercentageOfDestination ( mySlowestGrowing () , weakest ( enemy ()  ), porcentaje090 ()  ) );
} 
 else {botOrders.addAll(issueOrderPercentageOfDestination ( myStrongest () , fastestGrowing ( all ()  ), porcentaje060 ()  ) );
} 
} } 
 else {if ( or (numPlanetsLessThan (totalOfPlanetsFromPlayer ( playerIdWithLessPlanets ()  ), totalOfPlanetsFromPlayer ( playerIdWithLessShips ()  ) ), numPlanetsGreaterThan (totalOfPlanetsFromPlayer ( playerIdWithLessShips ()  ), totalOfPlanetsFromPlayer ( playerIdWithLessShips ()  ) ) ) ) {
if ( numPlanetsGreaterThan (totalOfPlanetsFromPlayer ( playerIdWithMorePlanets ()  ), totalOfPlanetsFromPlayer ( playerIdWithMoreShips ()  ) ) ) {
if ( not ( numShipLessThan (totalOfShipsFromPlayer ( playerIdWithMorePlanets ()  ), totalOfShipsFromPlayer ( playerIdWithMorePlanets ()  ) ) ) ) {
botOrders.addAll(issueOrderPercentageOfDestination ( mySlowestGrowing () , slowestGrowing ( all ()  ), porcentaje095 ()  ) );
} 
 else {botOrders.addAll(issueOrderPercentageOfDestination ( myStrongest () , weakest ( ownedBy ( playerIdWithLessFleets ()  ) ), porcentaje060 ()  ) );
} } 
 else {botOrders.addAll(issueOrderToNearestPercentageOfDestination ( mySlowestGrowing () , ownedBy ( myId ()  ), porcentaje100 ()  ) );

botOrders.addAll(issueOrderPercentageOfOrigin ( myWeakest () , slowestGrowing ( ownedBy ( myId ()  ) ), porcentaje020 ()  ) );

} } 
 else {if ( playerIDEquals (playerIdWithLessShips () , playerIdWithLessFleets ()  ) ) {
if ( numShipLessThan (totalOfShipsFromPlayer ( playerIdWithMorePlanets ()  ), totalOfShipsFromPlayer ( playerIdWithLessShips ()  ) ) ) {
noOrder (); } 
 else {botOrders.addAll(issueOrderPercentageOfDestination ( mySlowestGrowing () , weakest ( all ()  ), porcentaje025 ()  ) );
} } 
 else {botOrders.addAll(issueOrderToNearestPercentageOfOrigin ( myStrongest () , ownedBy ( playerIdWithLessPlanets ()  ), porcentaje025 ()  ) );
} 
if ( numPlanetsEquals (totalOfPlanetsFromPlayer ( playerIdWithMorePlanets ()  ), totalOfPlanetsFromPlayer ( playerIdWithMoreShips ()  ) ) ) {
botOrders.addAll(issueOrderPercentageOfOrigin ( myStrongest () , weakest ( all ()  ), porcentaje005 ()  ) );
} 
 else {botOrders.addAll(overwhelm (myStrongest () , weakest ( enemy ()  ) ));
} 
} } 
		
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
		for (Integer pID : g.gonzaloCaneladaLorenaPrieto_getNumPlayersFromFleets()) {
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
		for (Integer pID : g.gonzaloCaneladaLorenaPrieto_getNumPlayersFromPlanets()) {
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
		for (Integer pID : g.gonzaloCaneladaLorenaPrieto_getNumPlayersFromFleets()) {
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
		for (Integer pID : g.gonzaloCaneladaLorenaPrieto_getNumPlayersFromFleets()) {
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
		for (Integer pID : g.gonzaloCaneladaLorenaPrieto_getNumPlayersFromPlanets()) {
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
		for (Integer pID : g.gonzaloCaneladaLorenaPrieto_getNumPlayersFromShips()) {
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
