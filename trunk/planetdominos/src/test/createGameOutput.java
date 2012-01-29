package test;

import java.util.ArrayList;
import java.util.List;

import app.gpwars.engine.Engine;
import app.gpwars.engine.Game;
import app.gpwars.engine.Player;
import app.planetdominos.engine.bots.Bot300;

public class createGameOutput {

	public static void main(String[] args) {

		Engine engine = new Engine();

		Game game = new Game("src/app/gpwars/maps/ourmap1.txt", 200, 0, true);
		game.Init();

		Player currentPlayer = new Bot300();
		List<String> LISTA_NOMBRE_BOTS = new ArrayList<String>();
		LISTA_NOMBRE_BOTS.add("RandomBot");
		LISTA_NOMBRE_BOTS.add("RandomBot");
		LISTA_NOMBRE_BOTS.add("RandomBot");
		engine.runGame(game, currentPlayer, LISTA_NOMBRE_BOTS, true);
	}

}
