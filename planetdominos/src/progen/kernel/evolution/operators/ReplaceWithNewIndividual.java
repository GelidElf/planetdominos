package progen.kernel.evolution.operators;

import java.util.ArrayList;
import java.util.List;

import progen.kernel.evolution.GenneticOperator;
import progen.kernel.population.Individual;
import progen.kernel.population.Population;

/**
 * 
 * @author gonzalo
 *
 */
public class ReplaceWithNewIndividual extends GenneticOperator{

	@Override
	public List<Individual> evolve(Population population) {
		List<Individual> oldIndividuals = selector.select(population, 1);
		List<Individual> replacedIndividuals = new ArrayList<Individual>();
		
		replacedIndividuals.add(new Individual(oldIndividuals.get(0).getGrammars()));
		
		return replacedIndividuals;
	}

}
