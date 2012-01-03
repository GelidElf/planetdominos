package app.gpwars;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import progen.kernel.population.Individual;
import progen.userprogram.UserProgram;
import app.gpwars.engine.Game;
import app.gpwars.engine.Order;
import app.gpwars.engine.Player;

public class GPPlayer extends Player {

	Individual individual;
	UserProgram userProgram;

	public GPPlayer(Individual individual, UserProgram userProgram) {
		this.individual = individual;
		this.userProgram = userProgram;
	}

	/**
	 * En principio el bot generado por la GP lo único que hace es devolver la lista de 
	 * órdenes que recibe al evaluar el individuo de GP al que representa. 
	 */
	@Override
	public List<Order> DoTurn(Game game) {
		//Versión elegante sin optimizar:
		//return safeCast(individual.evaluate(userProgram));
		//Versión más cutre pero más rápida:
		List<Order> listaDeOrdenes = (List<Order>) individual.evaluate(userProgram);
		if (listaDeOrdenes == null)
			listaDeOrdenes = Collections.EMPTY_LIST;
		return listaDeOrdenes;
	}
	
	

	/**
	 * Creo un método para hacer castings seguros de Object a List<Order>. Estos castings son más seguros pero
	 * sin duda deben ser más ineficientes, aunque no sé exáctamente qué overhead añaden. Son opcionales.
	 * @param input
	 * @return
	 */
	private List<Order> safeCast (Object input) {
		List<Order> output = new ArrayList<Order>();

		if (input == null) {
			return output;
		} else {
			if (input instanceof List<?>) {
				for (Object o : ((List<?>) input).toArray()) {
					if (o instanceof Order) {
						output.add((Order)o);
					} else {
						throw new AssertionError("Cannot cast from List<?> to List<Order>: value " + o + "is not an Order.");
					}
				}
			} else {
				throw new AssertionError("Cannot cast from Object to List<?>: value " + input + "is not a List.");
			}
		}
		return output;
	}

}
