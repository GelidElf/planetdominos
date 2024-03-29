package progen.output;

import java.util.HashMap;

import progen.context.ProGenContext;
import progen.output.dataCollectors.DataCollector;

/**
 * Representación de todos los datos históricos disponibles en la ejecución de
 * Progen. Se almacenan todos los datos históricos categorizados por tipo de
 * DataGenerator y generación de la evolución.
 * 
 * @author jirsis
 * @since 2.0
 * @see progen.output.dataCollectors
 */
public class HistoricalData {

    /** Identificador de los dator referentes a la totalidad del experimento. */
    public static final String EXPERIMENT = "-ALL";
    /**
     * Colección de todos los recolectores de datos a lo largo de las
     * generaciónes.
     */
    private HashMap<String, DataCollector> collectors;
    /** Identifica la generación actual. */
    private int currentGeneration;
    /** Total de colectores disponibles en la ejecución. */
    private int totalCollectors;

    /** Instancia única de la clase. */
    private static HistoricalData historical;

    /**
     * Constructor genérico de la clase, en la que se inicializan los valores
     * iniciales.
     */
    private HistoricalData() {
	collectors = new HashMap<String, DataCollector>();
	totalCollectors = ProGenContext.getOptionalProperty(
		"progen.datacollector.total", 0);
	this.addCollectors(HistoricalData.EXPERIMENT);
	this.newExperiment();
    }

    /**
     * Forma de obtener la única instancia que existirá a lo largo de la
     * ejecución de ProGen.
     * 
     * @return Instancia de la clase.
     */
    public static HistoricalData makeInstance() {
	if (historical == null) {
	    historical = new HistoricalData();
	}
	return historical;
    }

    /**
     * Obtiene el DataColector de la última generación, identificada por el
     * nombre del colector.
     * 
     * @param name
     *            El nombre del colector a recuperar.
     * @return El colector deseado o uno que no continene plugins en caso de que
     *         no exista.
     */
    public DataCollector getCurrentDataCollector(String name) {
	return this.getDataCollector(name + "" + currentGeneration);
    }

    /**
     * Obtiene el DataCollector indicado en el nombre.
     * 
     * @param name
     *            El nombre del DataCollector.
     * @return El Datacollector solicitado.
     */
    private DataCollector getDataCollector(String name) {
	DataCollector dataCollector = collectors.get(name);
	if (dataCollector == null) {
	    dataCollector = new DataCollector();
	}
	return dataCollector;
    }

    /**
     * Obtiene el DataColector de la generación especificada, identificada por
     * el nombre del colector.
     * 
     * @param name
     *            El nombre del colector a recuperar.
     * @param generation
     *            La generación de la que se quieren obtener los datos.
     * @return El colector deseado o <code>null</code> en caso de que no exista.
     */
    public DataCollector getDataCollector(String name, int generation) {
	return this.getDataCollector(name + "" + generation);
    }

    /**
     * Obtiene el DataCollector general del experimento, identificado por el
     * nombre del colector.
     * 
     * @param name
     *            EL nombre del colector a recuperar.
     * @return El colector deseado o <code>null</code> en caso de que no exista.
     */
    public DataCollector getDataCollectorExperiment(String name) {
	return this.getDataCollector(name + HistoricalData.EXPERIMENT);
    }

    /**
     * Actualiza el histórico para definir los colectores de la nueva
     * generación.
     */
    public void newGeneration() {
	if(currentGeneration+1<Integer.parseInt(ProGenContext.getMandatoryProperty("progen.max-generation"))){
	    currentGeneration++;
	    this.addCollectors(String.valueOf(currentGeneration));
	}
    }

    /**
     * Añade la colección de DataCollectors disponibles al histórico,
     * identifcados por una etiqueta.
     * 
     * @param tag
     *            Etiqueta que identificará al DataCollector correspondiente.
     */
    private void addCollectors(String tag) {
	for (int i = 0; i < totalCollectors; i++) {
	    DataCollector collector = new DataCollector("progen.datacollector"
		    + i);
	    collectors.put(collector.getName() + "" + tag, collector);
	}
    }

    /**
     * Devuelve el número de la generación que se está ejecutando en un momento
     * determinado.
     * 
     * @return La generación que está ejecutando.
     */
    public int getCurrentGeneration() {
	return currentGeneration;
    }

    /**
     * Al comienzo de cada experimento, es necesario definir unos valores por
     * defecto.
     */
    public void newExperiment() {
	// default value is negative because newGeneration() increase this value
	// and really start in 0
	currentGeneration = -1;
	this.newGeneration();
	DataCollector collector;
	for (String keyCollector : collectors.keySet()) {
	    collector = collectors.get(keyCollector);
	    if (!collector.getName().endsWith(HistoricalData.EXPERIMENT)) {
		collectors.remove(collector);
	    }
	}
    }

}
