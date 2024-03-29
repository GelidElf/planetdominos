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
import progen.experimenter.property.Property;
import progen.experimenter.property.PropertyFactory;
import progen.kernel.error.Info;

/**
 * Clase que implementa el comportamiento de los experimentos múltiples. Este
 * comportamiento es tal que recupera el conjunto marcado con el nombre
 * <code>progen.experimenter.*</code> e irá generando la propiedad, eliminando
 * del nombre el literal <code>experimenter</code> para que vaya cambiando el
 * contexto de ejecución de ProGen.
 * 
 * @author jirsis
 * @since 2.0
 */
public class MultipleExperimenter extends Experimenter {

    /** Conjunto de propiedades que definen el experimento múltiple */
    private List<Property> properties;

    /** Indica el número de veces que se ejecutará el experimento */
    private int totalRepetitions;

    /** Indica qué repetición está ejecutando en un momento dado */
    private int currentRepetition;

    /**
     * Indica si se ha terminado de ejecutar una repetición y falta por ejecutar
     * más
     */
    private boolean nextRepetition;

    /** Indica que experimenter concreto se está ejecutando */
    private int currentExperimenter;
    
    /** Instancia de SimpleExperimenter que calculará los resultados de un 
     * experimento determinado.
     */
    private SimpleExperimenter experimenter;
    
    private boolean defined;

    /**
     * Constructor genérico de la clase. Recupera todas las propiedades
     * definidas con el nombre <code>progen.experimenter.*</code> y las irá
     * incluyendo en ProGenContext con un valor nuevo cada vez que se llame al
     * método de actualización.
     */
    public MultipleExperimenter() {
	super();
	currentRepetition = 0;
	currentExperimenter = 0;
	nextRepetition = false;
	totalRepetitions = ProGenContext.getOptionalProperty(
		"progen.repetitions.experimenter", 1);
	properties = new ArrayList<Property>();
	List<String> propertiesLabels = ProGenContext
		.getFamilyOptions("progen.experimenter.");
	for (String label : propertiesLabels) {
	    properties.add(PropertyFactory.makeInstance(label));
	}
	experimenter=new SimpleExperimenter();
	defined=false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see progen.experimenter.Experimenter#defineValue()
     */
    @Override
    public void updateValues() {
	boolean actualizado = false;
	Property nextProp;
	if (!isDone()) {
	    defined=false;
	    if (nextRepetition) {
		for (Property property : properties) {
		    property.reset();
		}
		nextRepetition = false;
		currentExperimenter = 0;
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

    /*
     * (non-Javadoc)
     * 
     * @see progen.experimenter.Experimenter#isDone()
     */
    @Override
    public boolean isDone() {
	boolean done = true;
	if (nextRepetition) {
	    currentRepetition++;
	    done = false;
	} else {
	    for (Property property : properties) {
		done &= !property.hasNext();
	    }

	    if (done && (currentRepetition + 1) < totalRepetitions) {
		done = false;
		nextRepetition = true;
	    }
	}
	return done && defined;
    }

    /*
     * (non-Javadoc)
     * 
     * @see progen.experimenter.Experimenter#updateValues()
     */
    @Override
    public void defineValues() {
	defined=true;
	// actualizacion de las variables
	for (Property property : properties) {
	    ProGenContext.setProperty(property.getLabel(), property.getValue());
	}
    }

    /*
     * (non-Javadoc)
     * 
     * @see progen.experimenter.Experimenter#generateResults()
     */
    @Override
    public void generateResults() {
	dumpResults();
    }

    /**
     * Genera la carpeta de volcado de un experimento y copia en ella, tanto el
     * contexto actual de dicho experimento como los resultados asociados.
     * 
     */
    private void dumpResults() {
	PrintWriter context;
	// se recupera el path de la carpeta de resultados del experimento
	File experimentDir = new File(
		ProGenContext.getMandatoryProperty("progen.output.dir")+
		ProGenContext.getMandatoryProperty("progen.output.experiment"));
	try {
	    // creamos el fichero del contexto actual
	    context = new PrintWriter(new BufferedWriter(new FileWriter(
		    experimentDir.getAbsolutePath() + File.separator
			    + "current context.txt")));
	    for (Property property : properties) {
		context.print(property.getLabel());
		context.print("=");
		context.println(property.getValue());
	    }
	    context.close();
	    experimenter.generateResults();
	} catch (IOException e) {
	    throw new ProGenException(e.getLocalizedMessage());
	}
    }

    /**
     * Devuelve la repetición actual.
     * 
     * @return la repetición actual.
     */
    public int getCurrentRepetition() {
	int repetition = currentRepetition;
	if (nextRepetition) {
	    repetition = currentRepetition - 1;
	}
	return repetition;
    }

    /**
     * Devuelve el experimenter actual.
     * 
     * @return el experimenter actual.
     */
    public int getCurrentExperimenter() {
	return currentExperimenter;
    }

    /*
     * (non-Javadoc)
     * @see progen.experimenter.Experimenter#defineExperimentDir()
     */
    @Override
    public String defineExperimentDir() {
	StringBuilder experimenterDir = new StringBuilder(20);
	experimenterDir.append("exp-");
	experimenterDir.append(currentRepetition);
	experimenterDir.append("-");
	experimenterDir.append(currentExperimenter);
	return experimenterDir.toString();
    }

    /*
     * (non-Javadoc)
     * @see progen.experimenter.Experimenter#finishMessage()
     */
    @Override
    public String finishMessage() {
	StringBuilder stb=new StringBuilder(20);
	stb.append("---- ");
	stb.append(Info.get(2));
	stb.append(" ");
	stb.append(currentExperimenter);
	stb.append(" -----");
	return stb.toString();
    }
}
