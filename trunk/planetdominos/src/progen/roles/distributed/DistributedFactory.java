/**
 * 
 */
package progen.roles.distributed;

import progen.roles.Client;
import progen.roles.Dispatcher;
import progen.roles.ExecutionRole;
import progen.roles.ProGenFactory;
import progen.roles.Worker;

/**
 * @author jirsis
 *
 */
public class DistributedFactory extends ProGenFactory {

	/* (non-Javadoc)
	 * @see progen.roles.ProGenFactory#makeClient()
	 */
	@Override
	public Client makeClient() {
		System.err.println("NOT IMPLEMENTED YET");
		return null;
	}

	/* (non-Javadoc)
	 * @see progen.roles.ProGenFactory#makeDispatcher()
	 */
	@Override
	public Dispatcher makeDispatcher() {
		System.err.println("NOT IMPLEMENTED YET");
		return null;
	}

	/* (non-Javadoc)
	 * @see progen.roles.ProGenFactory#makeWorker()
	 */
	@Override
	public Worker makeWorker() {
		System.err.println("NOT IMPLEMENTED YET");
		return null;
	}

	@Override
	public ExecutionRole makeExecutionRole() {
		System.err.println("NOT IMPLEMENTED YET");
		return null;
	}

}
