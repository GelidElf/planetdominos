package app.planetdominos.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import app.gpwars.util.BotTranslator;

public class CreateBotFromBestIndividualFile {

	private static final String REPLACE_WILDCARD = "// ####";
	private static String LOG_FILE_NAME = "./botCreationLog.txt";
	private static String botWrapperLocation = "src/app/planetdominos/engine/bots";
	private static String botWrapperName = "BotWrapper.java";
	
	
	public static void main(String[] args) {
	
		if (args.length != 1){
			System.out.println("Falta la ruta del archivo. Saliendo.");
			return;
		}
		
		ArrayList<String> botString;
		ArrayList<String> wrapperStringBuilder;
		ArrayList<String> salida = new ArrayList<String>();
		String botName = "Bot"+System.currentTimeMillis();
		try {
			botString = getFileString(args[0]);
			String translation = BotTranslator.translate(createStringFromArray(botString,1,botString.size()-2));
			wrapperStringBuilder = getFileString(botWrapperLocation+File.separator+botWrapperName);
			for (String s:wrapperStringBuilder){
				if (s.contains(REPLACE_WILDCARD)){
					salida.add(translation);
				}else{
					if (s.contains("BotWrapper")){
						salida.add(s.replace("BotWrapper",botName));
					}else{
						salida.add(s);
					}
				}
			}
			storeFileString(salida, new File(botWrapperLocation+File.separator+botName+".java"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("Problem getting botfile");
			return;
		}
		
	}

	private static String createStringFromArray(ArrayList<String> botString, int start, int end) {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i<end;i++){
			builder.append(botString.get(i));
		}
		return builder.toString();
	}

	private static void storeFileString(List<String> outputArray, File output) {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(output));
			for (String s:outputArray){
				writer.write(s+"\n");
			}
		    writer.close();
		} catch (IOException x) {
		    System.err.format("IOException: %s%n", x);
		}
		
	}

	private static ArrayList<String> getFileString(String fileLocation) throws FileNotFoundException {
		ArrayList<String> arrayEntrada = new ArrayList<String>();
		File botBestIndividualFile = new File(fileLocation);
		Scanner scanner = new Scanner(botBestIndividualFile);
		for (;scanner.hasNextLine();arrayEntrada.add(scanner.nextLine()));
		return arrayEntrada;
	}

	private static int lineaDondeReemplazar = -1;
	
	private static void addToStringList (List<String> destino, String linea){
		if (linea.contains(REPLACE_WILDCARD))
			lineaDondeReemplazar = destino.size()-1;
		destino.add(linea);
	}
	
	
	private void saveBotCreation() {
		// TODO Auto-generated method stub
		
	}

	private void createBotInPosition() {
		// TODO Auto-generated method stub
		
	}

	private void scanPossibleBots() {
		// TODO Auto-generated method stub
		
	}

	private void readBotCreationLog() {
		
		FileReader fileReader;
		File logFile = new File(LOG_FILE_NAME);
		if (logFile.exists()){
			try {
				fileReader = new FileReader(logFile);
				BufferedReader reader = new BufferedReader(fileReader);
			} catch (FileNotFoundException e) {
				System.out.println("Unable to read from logFile at " + logFile.getAbsolutePath());
				e.printStackTrace();
			}
			
		}else{
			try {
				logFile.createNewFile();
			} catch (IOException e) {
				System.out.println("Unable to create new logFile in " + logFile.getAbsolutePath());
				e.printStackTrace();
			}
		}
		
	}
	
}
