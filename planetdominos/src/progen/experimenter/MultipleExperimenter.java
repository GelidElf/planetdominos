package progen.experimenter;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import progen.ProGenException;
import progen.context.ProGenContext;

/**
 * Clase que implementa el comportamiento de los experimentos múltiples. Este comportamiento
 * es tal que recupera el conjunto marcado con el nombre <code>progen.experimenter.*</code>
 * e irá generando la propiedad, eliminando del nombre el literal <code>experimenter</code>
 * para que vaya cambiando el contexto de ejecución de ProGen.
 * 
 * @author jirsis
 * @since 2.0
 */
public class MultipleExperimenter extends Experimenter {

	/**	Conjunto de propiedades que definen el experimento múltiple */
	private List<Property> properties;
	
	/** Indica el número de veces que se ejecutará el experimento */
	private int totalRepetitions;
	
	/** Indica qué repetición está ejecutando en un momento dado */
	private int currentRepetition;
	
	/** Indica si se ha terminado de ejecutar una repetición y falta por ejecutar más */
	private boolean nextRepetition;
	
	/** Indica que experimenter concreto se está ejecutando */
	private int currentExperimenter;
	
	/**
	 * Constructor genérico de la clase. Recupera todas las propiedades definidas
	 * con el nombre <code>progen.experimenter.*</code> y las irá incluyendo en 
	 * ProGenContext con un valor nuevo cada vez que se llame al método de actualización.
	 */
	public MultipleExperimenter(){
		currentRepetition=0;
		currentExperimenter=0;
		nextRepetition=false;
		totalRepetitions=ProGenContext.getOptionalProperty("progen.repetitions.experimenter", 1);
		properties=new ArrayList<Property>();
		List<String> propertiesLabels=ProGenContext.getFamilyOptions("progen.experimenter.");
		for(String label : propertiesLabels){
			properties.add(PropertyFactory.makeInstance(label));
		}
	}
	
	/* (non-Javadoc)
	 * @see progen.experimenter.Experimenter#defineValue()
	 */
	public void updateValues() {
		boolean actualizado = false;
		Property nextProp;
		if(!isDone()){
			if(nextRepetition){
				for(Property property: properties){
					property.reset();
				}
				nextRepetition=false;
				currentExperimenter=0;
			} else {
				// incremento de las propiedades
				int i = 0;
				while (i < properties.size() && !actualizado) {
					nextProp = properties.get(i);
					if (nextProp.hasNext()) {
						nextProp.nextValue();
						actualizado = true;
						currentExperimenter++;
					} else {
						i++;
					}
				}

				// si se actualizo algo, se resetean todas las propiedades
				// anteriores
				if (actualizado) {
					while (--i >= 0) {
						properties.get(i).reset();
					}
				}
			}
		}
	}

	/* (non-Javadoc)
	 * @see progen.experimenter.Experimenter#isDone()
	 */
	public boolean isDone() {
		boolean done=true;
		if(nextRepetition){
			done=false;
		}else{
			for(Property property:properties){
				done&=!property.hasNext();
			}
			
			if(done && (currentRepetition+1)<totalRepetitions){
				currentRepetition++;
				done=false;
				nextRepetition=true;
			}
		}
		return done;
	}
	
	/*
	 * (non-Javadoc)
	 * @see progen.experimenter.Experimenter#updateValues()
	 */
	public void defineValues(){
		//actualizacion de las variables
		for(Property property:properties){
			ProGenContext.setProperty(property.getLabel(), property.getValue());
		}
	}

	/*
	 * (non-Javadoc)
	 * @see progen.experimenter.Experimenter#generateResults()
	 */
	public void generateResults() {
		generateOutputDir();
		dumpContext();
		dumpResults();
	}

	/**
	 * Se encarga de preparar el conjunto de directorios para almacenar las distintas salidas.
	 * @return <code>true</code> si pudo crear las carpetas <code>false</code> en caso contrario.
	 */
	private boolean generateOutputDir() {
		boolean mkdir=true;
		String outputPath=ProGenContext.getOptionalProperty("progen.output.dir", 
				System.getProperty("user.dir")+File.separator+ProGenContext.getMandatoryProperty("progen.experiment.name")+File.separator);
		outputPath=outputPath.replaceAll("\\.",	 File.separator);
		if(!outputPath.endsWith(File.separator)){
			outputPath=outputPath+File.separator;	
		}
		outputPath=outputPath+ProGenContext.getMandatoryProperty("progen.experiment.name");
		File dir = new File(outputPath);
		if(!dir.exists()){
			//creacion de la carpeta "home" de todos los ficheros de salida
			mkdir=dir.mkdirs();
			//creacion de la carpeta de salida de los resultados
			ProGenContext.setProperty("progen.output.experiment", outputPath+File.separator+"output");
			dir=new File(ProGenContext.getMandatoryProperty("progen.output.experiment"));
			mkdir=dir.mkdir();
		}
		return mkdir;
	}
	
	/**
	 * Copia los ficheros que definen el contexto inicial del experimento. 
	 * Como mínimo copiará el master file y el fichero de definición del 
	 * experimento.
	 */
	private void dumpContext() {
		File master=new File(ProGenContext.getMandatoryProperty("progen.output.dir")+File.separator+
				ProGenContext.getMandatoryProperty("progen.experiment.name")+File.separator);
		//si solo hay un elemento, solo esta la carpeta output
		if(master.list().length==1){
			String masterFile=ProGenContext.getMandatoryProperty("progen.masterfile");
			String experimentFile=ProGenContext.getMandatoryProperty("progen.experiment.file").replace('.', File.separatorChar).replaceAll(File.separator+"txt", ".txt");
			String optionalFiles=ProGenContext.getOptionalProperty("progen.optional.files", "");
			String currentDir=System.getProperty("user.dir");
			
			copyFile(masterFile, ProGenContext.getMandatoryProperty("progen.output.dir")+File.separator+
					ProGenContext.getMandatoryProperty("progen.experiment.name"));
			copyFile(experimentFile, ProGenContext.getMandatoryProperty("progen.output.dir")+File.separator+
					ProGenContext.getMandatoryProperty("progen.experiment.name"));
			for(String file : optionalFiles.trim().split(",[ ]*")){
				copyFile(currentDir+File.separator+file, ProGenContext.getMandatoryProperty("progen.output.dir")+File.separator+
						ProGenContext.getMandatoryProperty("progen.experiment.name"));
			}
		}
	}

	/**
	 * Genera la carpeta de volcado de un experimento y copia en ella, tanto el contexto actual de dicho
	 * experimento como los resultados asociados.
	 * @return <code>true</code> si pudo crear las carpetas <code>false</code> en caso contrario.
	 */
	private boolean dumpResults() {
		boolean mkdir;
		PrintWriter context;
		//se crea la carpeta de resultados del experimento
		File experimentDir=new File(ProGenContext.getMandatoryProperty("progen.output.experiment")+File.separator+"exp-"+getCurrentRepetition()+"-"+getCurrentExperimenter());
		mkdir=experimentDir.mkdir();
		try {
			//creamos el fichero del contexto actual
			context=new PrintWriter(new BufferedWriter(new FileWriter(experimentDir.getAbsolutePath()+File.separator+"ProGen context.txt")));
			for(Property property: properties){
				context.print(property.getLabel());
				context.print("=");
				context.println(property.getValue());
			}
			context.close();
		} catch (IOException e) {
			throw new ProGenException(e.getLocalizedMessage());
		}
		return mkdir;
	}

	/**
	 * Devuelve la repetición actual.
	 * @return la repetición actual.
	 */
	public int getCurrentRepetition(){
		int repetition=currentRepetition;
		if(nextRepetition){
			repetition=currentRepetition-1;
		}
		return repetition;
	}
	
	/**
	 * Devuelve el experimenter actual.
	 * @return el experimenter actual.
	 */
	public int getCurrentExperimenter(){
		return currentExperimenter;
	}
}
