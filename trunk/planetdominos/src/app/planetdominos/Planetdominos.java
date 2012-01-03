package app.planetdominos;

import java.util.ArrayList;
import java.util.List;

import progen.kernel.population.Individual;
import progen.userprogram.UserProgram;
import app.gpwars.GPPlayer;
import app.gpwars.engine.Engine;
import app.gpwars.engine.Game;
import app.gpwars.engine.Player;
import app.gpwars.util.BotTranslator;

public class Planetdominos  extends UserProgram{

	private static final int MAXTURNS= 30;

	private static final int TOTALGAMES = 20;

	private List<String> botList;

	private Game game;

	private Engine engine;

	private Player currentPlayer;
	
	@Override
	public double fitness(Individual individual) {
		int total = TOTALGAMES;
		buildBotList();
		engine = new Engine();
		game =new Game("app\\gpwars\\maps\\map2.txt", MAXTURNS, 0);
		game.Init();
		for(int i = 0;i<TOTALGAMES;i++){
			currentPlayer = new GPPlayer(individual, this);
			int resultado = engine.runGame(game, currentPlayer, botList, true);
			if (resultado == 1)
				total--;
		}
		return total/TOTALGAMES;
	}

	private void buildBotList(){
		botList = new ArrayList<String>();
		botList.add("RandomBot");
//		botList.add("BullyBot");
//		botList.add("ProspectorBot");
	}
	
	public Game getGame() {
		return game;
	}

	public Player getCurrentPlayer() {
		return currentPlayer;
	}
	
	@Override
	public String postProcess(Individual best) {

		 String botLogic = "";

		 botLogic = BotTranslator.translate(best.toString());

		 return botLogic;

	}
	
	public int getNumberOfPlayers(){
		return botList.size()+1;
	}
	
	
}
