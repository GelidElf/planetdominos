package app.planetdominos;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import progen.context.ProGenContext;
import progen.kernel.population.Individual;
import progen.userprogram.UserProgram;
import app.gpwars.GPPlayer;
import app.gpwars.engine.Engine;
import app.gpwars.engine.Game;
import app.gpwars.engine.Player;
import app.gpwars.util.BotTranslator;

public class Planetdominos extends UserProgram {

	private static final String MAP_DIRECTORY = "./app/gpwars/maps";
	private static final String BOT_DIRECTORY = "./app/gpwars/engine/bots";
	
	private static Integer NUMBER_OF_MAPS = Integer.parseInt(ProGenContext.getMandatoryProperty("dominos.fitness.numberOfMaps"));
	private static Integer NUMBER_OF_BOTS = Integer.parseInt(ProGenContext.getMandatoryProperty("dominos.fitness.numberOfBots"));
	private static Integer MAX_TURNS_PER_GAME = Integer.parseInt(ProGenContext.getMandatoryProperty("dominos.fitness.maxTurnsPerGame"));
	private static Integer TOTAL_GAMES_PER_MAP = Integer.parseInt(ProGenContext.getMandatoryProperty("dominos.fitness.totalGamesPerMap"));

	private static List<String> LISTA_NOMBRE_MAPAS = null;
	private static List<List<String>> LISTA_NOMBRE_BOTS = null;

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
		compruebaYCargaVariablesDesdeElContexto();
		creaListaDeBotsSiNull();
		creaListaDeMapasSiNull();
		engine = new Engine();

		for (String nombreMapa : LISTA_NOMBRE_MAPAS) {
			totalFitness += playGamesWith(nombreMapa);
		}
		return totalFitness / totalGamesPlayed;
	}

	private void compruebaYCargaVariablesDesdeElContexto() {

		NUMBER_OF_MAPS = Integer.parseInt(ProGenContext.getMandatoryProperty("dominos.fitness.numberOfMaps"));
		NUMBER_OF_BOTS = Integer.parseInt(ProGenContext.getMandatoryProperty("dominos.fitness.numberOfBots"));
		MAX_TURNS_PER_GAME = Integer.parseInt(ProGenContext.getMandatoryProperty("dominos.fitness.maxTurnsPerGame"));
		TOTAL_GAMES_PER_MAP = Integer.parseInt(ProGenContext.getMandatoryProperty("dominos.fitness.totalGamesPerMap"));
		
	}

	private double playGamesWith(String mapName) {
		double sessionFitnessResult = 0;
		for (int i = 0; i < TOTAL_GAMES_PER_MAP; i++) {
			game = new Game(MAP_DIRECTORY + "/" + mapName, MAX_TURNS_PER_GAME,
					0,false);
			game.Init();
			//totalGamesPlayed++;
			currentPlayer = new GPPlayer(individual, this);

			sessionFitnessResult += fitnessDelBotVsCadaBot();
		}
		return sessionFitnessResult;
	}

	private double fitnessDelBotVsCadaBot(){
		double total = 0.0;
		for (List<String> botList: LISTA_NOMBRE_BOTS){
			total += fitnessDelBotEnPartida(botList);
			totalGamesPlayed++;
		}
		return total/LISTA_NOMBRE_BOTS.size();
	}
	
	/**
	 * For each game we must give a fitness, 0 for est fitness, 1 for worst
	 * fitness
	 * 
	 * @param resultado
	 * @return
	 */
	private double fitnessDelBotEnPartida(List<String> botList) {
		int winner = engine.runGame(game, currentPlayer, botList,
				false);
		int turnos = game.getNumTurns();
		switch (winner) {
		case 1:
			// ganamos
			return penalizacion(turnos);
		case 0:
			// empatamos
			return 0.75;
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
		if (turnos >= MAX_TURNS_PER_GAME) {
			return 0.5;
		} else {
			return 0.25 * (turnos / MAX_TURNS_PER_GAME);
		}
	}

	private void creaListaDeMapasSiNull() {
		if (LISTA_NOMBRE_MAPAS == null) {
			cargaListaMapasConMapasObligatorios();
			imprimeListaMapasSeleccionados();
		}
	}

	private void imprimeListaMapasSeleccionados() {
		System.out.println("Lista de mapas seleccionados: ");
		for (String mapName : LISTA_NOMBRE_MAPAS) {
			System.out.println("    " + mapName);
		}

	}

	private void cargaListaMapasConMapasObligatorios() {
		String[] mapNames = ProGenContext.getMandatoryProperty("dominos.fitness.maps").split(",");
		List<String> listaMapasPorDefecto = new ArrayList<String>();
		for (String mapName:mapNames){
			if (!mapName.equals("") && mapName != null)
				listaMapasPorDefecto.add(mapName);
		}
		if (mapNames.length > NUMBER_OF_MAPS){
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
		for (List<String> botName : LISTA_NOMBRE_BOTS) {
			System.out.println("    " + botName);
		}
	}

	private void creaListaConBotsPorDefecto() {
		String[] botNames = ProGenContext.getMandatoryProperty("dominos.fitness.bots").split(",");
		List<List<String>> listaBotsDefecto = new ArrayList<List<String>>();
		for (String botName:botNames){
			if (!botName.equals("") && botName != null){
				List<String> parte = new ArrayList<String>();
				for (String bot:botName.split(":")){
					parte.add(bot);
				}
				listaBotsDefecto.add(parte);
				ArrayList<String> alteradosArrayList;
				for (int i = 0; i<parte.size()-1;i++){
					alteradosArrayList = new ArrayList<String>();
					alteradosArrayList.addAll(parte.subList(i+1, parte.size()));
					alteradosArrayList.addAll(parte.subList(0, i+1));
					listaBotsDefecto.add(alteradosArrayList);
				}
			}
		}
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
				List<String> parte = new ArrayList<String>();
				parte.add(className.substring(0,
						className.length() - 6));
				LISTA_NOMBRE_BOTS.add(parte);
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
