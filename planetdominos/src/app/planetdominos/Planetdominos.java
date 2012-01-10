package app.planetdominos;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import progen.kernel.population.Individual;
import progen.userprogram.UserProgram;
import app.gpwars.GPPlayer;
import app.gpwars.engine.Engine;
import app.gpwars.engine.Game;
import app.gpwars.engine.Player;
import app.gpwars.util.BotTranslator;

public class Planetdominos extends UserProgram {

	private static final int NUMBER_OF_MAPS = 3;
	private static final int NUMBER_OF_BOTS = 1;

	private static final String MAP_DIRECTORY = "./app/gpwars/maps";
	private static final String BOT_DIRECTORY = "./app/gpwars/engine/bots";

	private static final int MAX_TURNS_PER_GAME = 60;
	private static final int TOTAL_GAMES_PER_MAP = 20;

	private static List<String> LISTA_NOMBRE_MAPAS = null;
	private static List<String> LISTA_NOMBRE_BOTS = null;

	private Game game;

	private Engine engine;

	private Player currentPlayer;

	private double totalGamesPlayed;

	private double totalFitness;

	private Individual individual;

	@Override
	public double fitness(Individual individual) {
		this.individual = individual;
		totalGamesPlayed = 0;
		totalFitness = 0;
		creaListaDeBotsSiNull();
		creaListaDeMapasSiNull();
		engine = new Engine();

		for (String nombreMapa : LISTA_NOMBRE_MAPAS) {
			totalFitness += playGamesWith(nombreMapa);
		}
		return totalFitness / totalGamesPlayed;
	}

	private double playGamesWith(String mapName) {
		double sessionFitnessResult = 0;
		for (int i = 0; i < TOTAL_GAMES_PER_MAP; i++) {
			game = new Game(MAP_DIRECTORY + "/" + mapName, MAX_TURNS_PER_GAME,
					0);
			game.Init();
			totalGamesPlayed++;
			currentPlayer = new GPPlayer(individual, this);

			sessionFitnessResult += fitnessDelBotEnPartida();
		}
		return sessionFitnessResult;
	}

	/**
	 * For each game must we give a fitness, 0 for est fitness, 1 for worst
	 * fitness
	 * 
	 * @param resultado
	 * @return
	 */
	private double fitnessDelBotEnPartida() {
		int winner = engine.runGame(game, currentPlayer, LISTA_NOMBRE_BOTS,
				false);
		int turnos = game.getNumTurns();
		switch (winner) {
		case 1:
			// ganamos
			return penalizacion(turnos);
		case 0:
			// empatamos
			return 1;
		default:
			// perdimos
			return 1;
		}
	}

	/**
	 * implementado como una linea recta
	 * 
	 * @param turnos
	 * @return
	 */
	private double penalizacion(int turnos) {
		if (turnos == MAX_TURNS_PER_GAME) {
			return 0.5;
		} else {
			return 0.25 * (turnos / MAX_TURNS_PER_GAME);
		}
	}

	private void creaListaDeMapasSiNull() {
		if (LISTA_NOMBRE_MAPAS == null) {
			creaListaMapasConMapasObligatorios();
			imprimeListaMapasSeleccionados();
		}
	}

	private void imprimeListaMapasSeleccionados() {
		System.out.println("Lista de mapas seleccionados: ");
		for (String mapName : LISTA_NOMBRE_MAPAS) {
			System.out.println("    " + mapName);
		}

	}

	private void creaListaMapasConMapasObligatorios() {
		List<String> listaMapasPorDefecto = new ArrayList<String>();
		listaMapasPorDefecto.add("map2.txt");
		listaMapasPorDefecto.add("map10.txt");
		listaMapasPorDefecto.add("map72.txt");
		if (listaMapasPorDefecto.size() > NUMBER_OF_MAPS){
			LISTA_NOMBRE_MAPAS =  listaMapasPorDefecto.subList(0, NUMBER_OF_MAPS);
		}else{
			LISTA_NOMBRE_MAPAS =  listaMapasPorDefecto;
			rellenaListaMapasAleatoriamente();
		}
	}

	private void rellenaListaMapasAleatoriamente() {
		Random r = new Random(System.currentTimeMillis());
		File directory = new File(MAP_DIRECTORY);
		File[] fileList = directory.listFiles(new MapFileNameFilter());
		if ((fileList != null) && (fileList.length != 0)) {
			for (int i = LISTA_NOMBRE_MAPAS.size(); i < NUMBER_OF_MAPS; i++) {
				int mapIndex = r.nextInt(fileList.length);
				LISTA_NOMBRE_MAPAS.add(fileList[mapIndex].getName());
			}
		}
	}

	private void creaListaDeBotsSiNull() {
		if (LISTA_NOMBRE_BOTS == null) {
			creaListaConBotsPorDefecto();
			imprimeListaDeBotsSeleccionados();
		}
	}

	private void imprimeListaDeBotsSeleccionados() {
		System.out.println("Lista de bots seleccionados: ");
		for (String botName : LISTA_NOMBRE_BOTS) {
			System.out.println("    " + botName);
		}
	}

	private void creaListaConBotsPorDefecto() {
		ArrayList<String> listaBotsDefecto = new ArrayList<String>();
		listaBotsDefecto.add("RandomBot");
		listaBotsDefecto.add("BullyBot");
		listaBotsDefecto.add("RageBot");
		listaBotsDefecto.add("FuryBot1");
		listaBotsDefecto.add("ProspectorBot");
		listaBotsDefecto.add("BullyBot");
		if (listaBotsDefecto.size() > NUMBER_OF_BOTS) {
			LISTA_NOMBRE_BOTS = listaBotsDefecto.subList(0, NUMBER_OF_BOTS); 
		} else {
			LISTA_NOMBRE_BOTS =  listaBotsDefecto;
			rellenaListaDeBotsAleatoriamente();
		}
	}

	private void rellenaListaDeBotsAleatoriamente() {
		Random r = new Random(System.currentTimeMillis());
		File directory = new File(BOT_DIRECTORY);
		File[] fileList = directory.listFiles(new BotFileNameFilter());
		if ((fileList != null) && (fileList.length != 0)) {
			for (int i = LISTA_NOMBRE_BOTS.size(); i < NUMBER_OF_BOTS; i++) {
				int mapIndex = r.nextInt(fileList.length);
				String className = fileList[mapIndex].getName();
				LISTA_NOMBRE_BOTS.add(className.substring(0,
						className.length() - 6));
			}
		}
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

	public int getNumberOfPlayers() {
		return LISTA_NOMBRE_BOTS.size() + 1;
	}

	private class MapFileNameFilter implements FilenameFilter {

		@Override
		public boolean accept(File dir, String name) {
			return (name.startsWith("map") && name.endsWith(".txt"));
		}

	}

	private class BotFileNameFilter implements FilenameFilter {

		@Override
		public boolean accept(File dir, String name) {
			return (!name.contains("actory") && (name.endsWith(".class")));
		}

	}

}
