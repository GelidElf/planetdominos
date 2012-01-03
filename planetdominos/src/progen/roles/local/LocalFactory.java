package progen.roles.local;

import progen.ProGenException;
import progen.context.ProGenContext;
import progen.roles.Client;
import progen.roles.Dispatcher;
import progen.roles.ExecutionRole;
import progen.roles.ProGenFactory;
import progen.roles.UnknownRoleException;
import progen.roles.Worker;

/**
 * Fábrica concreta en la que todos los roles que se pueden generar, son de tipo Local, es decir, 
 * todos los componentes interactuan a través de los mecanismos habituales de las llamadas
 * a métodos del lenguaje.
 * @author jirsis
 * @since 2.0
 */
public class LocalFactory extends ProGenFactory {
	
	/* (non-Javadoc)
	 * @see progen.roles.ProGenFactory#makeClient()
	 */
	@Override
	public Client makeClient() {
		return new ClientLocal();
	}

	/* (non-Javadoc)
	 * @see progen.roles.ProGenFactory#makeDispatcher()
	 */
	@Override
	public Dispatcher makeDispatcher() {
		return new DispatcherLocal();
	}

	/* (non-Javadoc)
	 * @see progen.roles.ProGenFactory#makeWorker()
	 */
	@Override
	public Worker makeWorker() {
		return new WorkerLocal();
	}

	/* (non-Javadoc)
	 * @see progen.roles.ProGenFactory#makeExecutionRole()
	 */
	@Override
	public ExecutionRole makeExecutionRole() {
		ExecutionRole exec=null;
		String element=ProGenContext.getOptionalProperty("progen.role.element",ExecutionRole.CLIENT_ROLE);
		String roleClass=null;
		if(element.equals(ExecutionRole.CLIENT_ROLE)){
			roleClass=ProGenContext.getOptionalProperty("progen.role."+element+".class", "ClientLocal");
		}else if (element.equals(ExecutionRole.DISPATCHER_ROLE)){
			roleClass=ProGenContext.getOptionalProperty("progen.role."+element+".class", "DispatcherLocal");
		}else if (element.equals(ExecutionRole.WORKER_ROLE)){
			roleClass=ProGenContext.getOptionalProperty("progen.role."+element+".class", "WorkerLocal");
		}else{
			throw new UnknownRoleException(element);
		}
		
		try {
			exec=(ExecutionRole)Class.forName("progen.roles.local."+roleClass).newInstance();
		}catch (Exception e) {
			throw new ProGenException(e.getMessage());
		}
		return exec;
	}

}
