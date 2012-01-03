package progen.experimenter;

import java.io.File;

import progen.context.ProGenContext;
import progen.output.outputers.OutputStore;

/**
 * Clase que implementa la funcionalidad de un experimento, 
 * en el que no se ha definido la opción de múltiples experimentos.
 * 
 * @author jirsis
 * @since 2.0
 */
public class SimpleExperimenter extends Experimenter {
	
	public SimpleExperimenter(){
		generateOutputDir();
	}

	/* (non-Javadoc)
	 * @see progen.experimenter.Experimenter#defineValue()
	 */
	public void defineValues() {
		//do nothing
	}

	/* (non-Javadoc)
	 * @see progen.experimenter.Experimenter#isDone()
	 */
	public boolean isDone() {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * @see progen.experimenter.Experimenter#updateValues()
	 */
	public void updateValues() {
		//do nothing
	}
	
	/*
	 * (non-Javadoc)
	 * @see progen.experimenter.Experimenter#generateResults()
	 */
	public void generateResults(){
		dumpContext();
		dumpResults();
	}

	/**
	 * Generará los ficheros de salida correspondientes según esté configurado en 
	 * ProGen.conf
	 */
	private void dumpResults() {
		OutputStore outputs = OutputStore.makeInstance();
		outputs.print();
		outputs.close();
	}

	/**
	 * Crea la carpeta en la que se generarán todos los ficheros de salida que estén configurados.
	 * @return <code>true</code> si se pudieron crear todas las carpetas necesarias y <code>false</code>
	 * en caso contrario.
	 */
	private boolean generateOutputDir(){
		boolean createDir=false;
		String outputPath=ProGenContext.getOptionalProperty("progen.output.experiment", 
				System.getProperty("user.dir")+File.separator+"outputs"+File.separator);
		if(!outputPath.endsWith(File.separator)){
			outputPath=outputPath+File.separator;	
		}
		outputPath=outputPath+ProGenContext.getMandatoryProperty("progen.experiment.name");
		ProGenContext.setProperty("progen.output.dir", outputPath);
		File dir = new File(outputPath);
		if(dir.exists()){
			deleteDirectory(dir);
		}
		//creacion de la carpeta "home" de todos los ficheros de salida
		createDir=dir.mkdirs();
		//creacion de la carpeta de salida de los resultados
		ProGenContext.setProperty("progen.output.experiment", outputPath+File.separator+"output"+File.separator);
		dir=new File(ProGenContext.getMandatoryProperty("progen.output.experiment"));
		createDir&=dir.mkdir();
		
		return createDir;
	}

	/**
	 * Copia de los ficheros que definen el contexto del experimento a la carpeta de 
	 * salida definida en la propiedad "progen.output.dir" o en &lt;user.bin&gt;/outputs.
	 */
	private void dumpContext(){
		String masterFile=ProGenContext.getMandatoryProperty("progen.masterfile");
		String experimentFile=ProGenContext.getMandatoryProperty("progen.experiment.file").replace('.', File.separatorChar).replace(File.separatorChar+"txt", ".txt");
		String optionalFiles=ProGenContext.getOptionalProperty("progen.optional.files", "");
		String currentDir=System.getProperty("user.dir");
		
		copyFile(masterFile, ProGenContext.getMandatoryProperty("progen.output.dir"));
		copyFile(experimentFile, ProGenContext.getMandatoryProperty("progen.output.dir"));
		for(String file : optionalFiles.trim().split(",[ ]*")){
			if (file.length()>0) {
			copyFile(currentDir+File.separatorChar+file, ProGenContext.getMandatoryProperty("progen.output.dir"));
			}
		}
	}
}
