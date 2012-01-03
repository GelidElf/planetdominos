package progen.kernel.functions;

/**
 * @author jirsis
 *
 */
public abstract class NonTerminal extends Function {

	/**
	 * @param signature
	 * @param symbol
	 */
	public NonTerminal(String signature, String symbol) {
		super(signature, symbol);
	}
	
	public int hashCode(){
		return super.hashCode();
	}

}
