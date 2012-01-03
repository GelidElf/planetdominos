package progen.kernel.evolution.selector;

import java.io.Serializable;
import java.util.Comparator;
import progen.kernel.population.Individual;

/**
 * Clase que implementa un comparador de Individual en función del 
 * adjustedFitness de cada uno de ellos. De tal forma que es mejor
 * el individuo que tiene un <i>adjustedFitness</i> mayor que el otro.
 * 
 * @author jirsis
 * @since 2.0
 */
public class IndividualComparator implements Comparator<Individual>, Serializable{

	/** Para serialización */
	private static final long serialVersionUID = 6330661847981449597L;

	/*
	 * (non-Javadoc)
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	public int compare(Individual o1, Individual o2) {
		return (int)(o2.getAdjustedFitness()-o1.getAdjustedFitness());
	}

}
