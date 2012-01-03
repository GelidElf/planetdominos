package progen.kernel.evolution.selector;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import progen.kernel.population.Individual;
import progen.kernel.population.Population;

/**
 * Implementación de la forma de seleccionar individuos como torneo.
 * Esta forma de seleccionar individuos de la población consiste en 
 * elegir de forma aleatoria una muestra de tantos individuos como 
 * esté definido en el tamaño del selector y luego, se devolverá
 * ese conjunto ordenado en función del valor del adjustedFitness de cada uno de ellos;
 * quedando en las primeras posiciones aquellos individuos que tenga este valor mayor.
 * @author jirsis
 * @since 1.0
 *
 */
public class Tournament extends Selector{

	/*
	 * (non-Javadoc)
	 * @see progen.kernel.evolution.selector.Selector#select(progen.kernel.population.Population)
	 */
	@Override
	public List<Individual> select(Population pop) {
		List<Individual> selection = new ArrayList<Individual>();
		for(int i=0;i<super.getSize();i++){
			selection.add(pop.getIndividuals().get((int)(Math.random()*pop.getIndividuals().size())).clone());
		}
		
		Collections.sort(selection, new IndividualComparator());
		
		return selection;
	}

}
