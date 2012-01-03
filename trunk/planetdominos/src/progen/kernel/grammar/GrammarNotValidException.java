
package progen.kernel.grammar;

import progen.kernel.error.Error;

/**
 * @author jirsis
 *
 */
public class GrammarNotValidException extends RuntimeException {

	/** Para serialización */
	private static final long serialVersionUID = 12775764959942792L;

	public GrammarNotValidException() {
		super(Error.get(255));
	}
}
