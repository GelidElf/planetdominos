package progen.kernel.grammar.validations;

import progen.kernel.grammar.Grammar;

/**
 * Clase que encadena y comprueba las distintas comprobaciones que se realizan 
 * para comprobar que una gramática está bien formada y puede generar
 * árboles evaluables en la aplicación.
 * 
 * @author jirsis
 * @since 2.0
 * @see progen.kernel.grammar.validations
 */
public class GrammarCheck {
	/** La gramática a comprobar. **/
	private Grammar grammar;
	
	/** 
	 * Constructor genérico de la clase que recibe como parámetro la gramática a 
	 * comprobar.
	 * @param grammar La gramática a comprobar.
	 */
	public GrammarCheck(Grammar grammar){
		this.grammar=grammar;
	}
	
	/**
	 * Método que realiza todas las validaciones necesarias sobre la gramática, 
	 * devolviendo <code>true</code> si es correcta.
	 * @return <code>true</code> si cumple con todas las comprobaciones.
	 */
	public boolean validate(){
		return new GrammarNonTerminalSymbolProduction().validate(grammar)
		&& new InaccesibleProductions().validate(grammar)
		&& new SuperfluousProductions().validate(grammar);
	}
	

}
