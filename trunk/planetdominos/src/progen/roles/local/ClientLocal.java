/**
 * 
 */
package progen.roles.local;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.List;

import progen.context.ProGenContext;
import progen.experimenter.Experimenter;
import progen.experimenter.ExperimenterFactory;
import progen.kernel.evolution.Elitism;
import progen.kernel.evolution.GenneticFactory;
import progen.kernel.evolution.GenneticOperator;
import progen.kernel.population.Individual;
import progen.kernel.population.Population;
import progen.output.HistoricalData;
import progen.output.outputers.OutputStore;
import progen.roles.Client;
import progen.roles.Dispatcher;
import progen.roles.ProGenFactory;
import progen.roles.Task;
import progen.userprogram.UserProgram;

/**
 * Implementación de un cliente que se conecta con un Dispatcher alojado en la
 * misma máquina virtual.
 * 
 * @author jirsis
 * 
 */
public class ClientLocal implements Client {

	/** Factoría de roles. */
	private ProGenFactory factory;

	/** Población del problema */
	private Population population;

	/** Factoría de operadores genéticos. */
	private GenneticFactory genneticFactory;

	/** Todas las salidas disponibles de ProGen. */
	private OutputStore outputs;

	/** Almacén de datos históricos. */
	private HistoricalData historical;

	/**
	 * Constructor genérico de la clase.
	 */
	public ClientLocal() {
		factory = ProGenFactory.makeInstance();
		population = new Population();
		genneticFactory = new GenneticFactory();
		outputs = OutputStore.makeInstance();
		historical = HistoricalData.makeInstance();
	}

	public Dispatcher initDispatcher() {
		return factory.makeDispatcher();
	}

	public void start() {
		Experimenter experimenter = ExperimenterFactory.makeInstance();
		Dispatcher dispatcher = this.initDispatcher();
		List<Individual> newPopulation;
		GenneticOperator operator;
		UserProgram userProgram = UserProgram.getUserProgram();
		userProgram.initialize();
		int i = 0;
		int maxGenerations = ProGenContext.getOptionalProperty(
				"progen.max-generation", Integer.MAX_VALUE);
		do {
			while (i < maxGenerations) {
				List<Task> individuals = new ArrayList<Task>();
				// convert the individuals a tasks
				for (Individual ind : population.getIndividuals()) {
					individuals.add((Task) ind);
				}
				dispatcher.asignTask(individuals, userProgram);

				// update historical data (RawFitness)
				for (Task task : dispatcher.getResults()) {
					Individual individual = (Individual) task;
					historical.getDataCollectorExperiment("ExperimentIndividualData").addValue("statistical",individual);
					historical.getCurrentDataCollector("ExperimentIndividualData").addValue("statistical",individual);
					historical.getCurrentDataCollector("PopulationData").addValue("individualMean", individual);
				}

				// reset new population
				newPopulation = new ArrayList<Individual>();
				Calendar before;
				Calendar after;
				
				Elitism elitism = new Elitism(population);
				newPopulation=elitism.propagate();
				
				while (newPopulation.size() != population.getIndividuals().size()) {
					operator = genneticFactory.getOperator();
					before = GregorianCalendar.getInstance();
					newPopulation.addAll(operator.evolve(population));
					after = GregorianCalendar.getInstance();
					historical.getCurrentDataCollector("PopulationTimeData").addValue("breeding",after.getTimeInMillis()-before.getTimeInMillis());
				}

				outputs.print();
				population.setPopulation(newPopulation);
				historical.newGeneration();
				i++;
			}
			experimenter.generateResults();
		}while (!experimenter.isDone());
		
// best = (Individual)(HistoricalData.makeInstance().getCurrentDataCollector("statistical").getPlugin("best").getValue());
//		String printable = userProgram.postProcess(best);
//		best.setPrintableIndividual(printable);
	}

}
