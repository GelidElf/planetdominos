package progen.output.outputers;

import progen.kernel.error.Error;

/**
 * Excepción que será lanzada cuando se esté intentando instanciar un Outputer del que 
 * no se encuentre la clase.
 * @author jirsis
 * @since 2.0
 *
 */
public class UnknowOutputerException extends RuntimeException {

	/** Para serialización */
	private static final long serialVersionUID = -2127111332478170624L;

	/**
	 * Constructor por defecto de la excepción que recibe una descripción del error ocurrido.
	 * @param message Mensaje descriptivo de la causa de la excepción.
	 */
	public UnknowOutputerException(String message) {
		super(Error.get(25)+"("+message+")");
	}


}
