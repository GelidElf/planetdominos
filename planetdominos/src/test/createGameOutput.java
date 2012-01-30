package test;

import java.util.ArrayList;
import java.util.List;

import app.gpwars.engine.Engine;
import app.gpwars.engine.Game;
import app.gpwars.engine.Player;
import app.gpwars.viewer.ViewGame;
import app.planetdominos.engine.bots.BotDandoloTodo;

public class createGameOutput {

	public static void main(String[] args) {

		Engine engine = new Engine();

		Game game = new Game("src/app/gpwars/maps/ourmap1.txt", 250, 0, true);
		game.Init();

		Player currentPlayer = new BotDandoloTodo();
		List<String> LISTA_NOMBRE_BOTS = new ArrayList<String>();
		LISTA_NOMBRE_BOTS.add("ProspectorBot");
		LISTA_NOMBRE_BOTS.add("BullyBot");
		LISTA_NOMBRE_BOTS.add("RageBot");
		System.out.println("Gana: "+ engine.runGame(game, currentPlayer, LISTA_NOMBRE_BOTS, true));
		ViewGame.main(args);
	}

}
