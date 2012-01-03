package app.gpwars.engine;

/**
 * An Order is composed by a source planet, a destination planet and a number of ships.
 * Encodes an order to send a fleet from one planet to another. 
 * @author cesteban
 *
 */
public class Order {
	private Planet sourcePlanet;
	private Planet destinationPlanet;
	private int numShips;
	
	
	/**
	 * @param sourcePlanet
	 * @param destinationPlanet
	 * @param numShips
	 */
	public Order(Planet sourcePlanet, Planet destinationPlanet, int numShips) {
		super();
		this.sourcePlanet = sourcePlanet;
		this.destinationPlanet = destinationPlanet;
		this.numShips = numShips;
	}


	/**
	 * @return the sourcePlanet
	 */
	public Planet getSourcePlanet() {
		return sourcePlanet;
	}


	/**
	 * @return the destinationPlanet
	 */
	public Planet getDestinationPlanet() {
		return destinationPlanet;
	}


	/**
	 * @return the numShips
	 */
	public int getNumShips() {
		return numShips;
	}
	
	
	
	
}
