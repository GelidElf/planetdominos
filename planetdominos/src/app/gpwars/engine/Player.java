/**
 * 
 */
package app.gpwars.engine;

import java.util.List;


/**
 * @author cesteban
 *
 */
public abstract class Player {
	//ID number of the player (player 1, player 2, etc.)
	private int id = -1;

	public void setID(int id) {
		this.id = id;	
	}
	
	public int getID() {
		return this.id;
	}
	
	public abstract List<Order> DoTurn(Game game);
}
