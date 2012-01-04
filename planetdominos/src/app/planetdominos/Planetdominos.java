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

	private static final String MAP_DIRECTORY = "app\\gpwars\\maps\\";

	private static final int MAXTURNS= 50;

	private static final int TOTALGAMES = 20;

	private List<String> botList;

	private Game game;

	private Engine engine;

	private Player currentPlayer;
	
	@Override
	public double fitness(Individual individual) {
		int total = TOTALGAMES*3;
		buildBotList();
		engine = new Engine();

		total -= playNGames(individual, TOTALGAMES, "map2.txt");
		total -= playNGames(individual, TOTALGAMES, "map10.txt");
		total -= playNGames(individual, TOTALGAMES, "map72.txt");
		return 1 - total/TOTALGAMES;
	}

	private int playNGames(Individual individual, int numberOfGames, String mapName) {
		game =new Game(MAP_DIRECTORY + mapName, MAXTURNS, 0);
		int totalGamesWon = 0;
		game.Init();
		for(int i = 0;i<numberOfGames;i++){
			currentPlayer = new GPPlayer(individual, this);
			int resultado = engine.runGame(game, currentPlayer, botList, true);
			if (resultado == 1)
				totalGamesWon++;
			int turns = game.getNumTurns();
			if (turns > MAXTURNS/2){
				
			}
		}
		return totalGamesWon;
	}

	private void buildBotList(){
		botList = new ArrayList<String>();
		botList.add("RandomBot");
//		botList.add("BullyBot");
//		botList.add("RageBot");
//		botList.add("FuryBot1");
//		botList.add("ProspectorBot");
//		botList.add("BullyBot");
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
