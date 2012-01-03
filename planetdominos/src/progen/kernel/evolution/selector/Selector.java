package progen.kernel.evolution.selector;

import java.util.List;

import progen.kernel.population.Individual;
import progen.kernel.population.Population;

/**
 * Clase abstracta que define el comportamiento general de todos los selectores que estén
 * disponibles en ProGen.
 * 
 * @author jirsis
 * @since 2.0
 *
 */
public abstract class Selector{
	
	/** 
	 * Tamaño del selector. Parámetro que especifica cuantos individuos tendrá 
	 * que escoger el selector
	 */
	private int size;
	
	/**
	 * Define el número de individuos que se seleccionarán al ejecutar el operador.
	 * @param size El número de individuos a seleccionar.
	 */
	public void setSize(int size){
		this.size=size;
	}
	
	/**
	 * Devuelve el valor del tamaño de la selección de individuos
	 * @return el número de individuos a seleccionar.
	 */
	public int getSize() {
		return size;
	}
	
	/**
	 * Método de factoría para generar una instancia concreta de un selector pasado por parámetro.
	 * @param name Nombre del selector a instanciar.
	 * @param size Tamaño de las selecciones que haga.
	 * @return Instancia nueva del selector.
	 */
	public static Selector makeSelector(String name, int size){
		
		Selector selector;
		try {
			selector = (Selector)Class.forName("progen.kernel.evolution.selector."+name).newInstance();
		} catch (Exception e) {
			throw new UnknownSelectorException(name);
		}
		
		selector.setSize(size);
		return selector;
	}

	/**
	 * Se seleccionan de la población pasada por parámetro la cantidad 
	 * de individuos definidos según los parámetros. Es importante clonar
	 * los individuos seleccionados, antes de añadirlos a la lista de retorno
	 * del método para evitar efectos no deseados en las generaciones posteriores,
	 * durante la ejecución del experimento.
	 * @param pop La población de la que seleccionar los individuos.
	 * @return La colección de individuos seleccionados.
	 */
	public abstract List<Individual> select(Population pop);
	
}
