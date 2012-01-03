/**
 * 
 */
package progen.roles.distributed;

import java.util.List;

import progen.roles.Dispatcher;
import progen.roles.Task;
import progen.roles.Worker;
import progen.userprogram.UserProgram;

/**
 * @author jirsis
 *
 */
public class DispatcherLocal implements Dispatcher {

	public void start() {
		System.err.println("NOT IMPLEMENTED YET");
	}

	public void addWorker(Worker worker) {
		System.err.println("NOT IMPLEMENTED YET");
	}

	public void asignTask(List<Task> tasks, UserProgram userProgram) {
		System.err.println("NOT IMPLEMENTED YET");
		
	}

	public List<Task> getResults() {
		System.err.println("NOT IMPLEMENTED YET");
		return null;
	}

	public void stopTask() {
		System.err.println("NOT IMPLEMENTED YET");
		
	}

	public int totalTasksDone() {
		System.err.println("NOT IMPLEMENTED YET");
		return 0;
	}

}
