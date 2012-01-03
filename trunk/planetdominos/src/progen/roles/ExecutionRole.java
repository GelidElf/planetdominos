/**
 * 
 */
package progen.roles;

/**
 * Interfaz que define el método que se ejecutará de forma general a los distintos tipos de roles.
 * @author jirsis
 * @since 2.0
 */
public interface ExecutionRole {
	/** Constante que identifica el rol de CLIENTE */
	public static final String CLIENT_ROLE="client";
	/** Constante que identifica el rol de DISPATCHER */
	public static final String DISPATCHER_ROLE="dispatcher";
	/** Constante que identifica el rol de WORKER */
	public static final String WORKER_ROLE="worker";
	
	/**
	 * Método que se ejecutará para que empiece a funcionar la instancia de ProGen con el 
	 * rol asignado.
	 */
	public void start();
}
