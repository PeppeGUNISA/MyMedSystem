/**
 * 
 */
package exception;

/**
 * @author Cristian
 *
 */
public class AlreadyRegisteredException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8693866123865235645L;

	public AlreadyRegisteredException() {
		super("Username o cf già registrati.");
	}
	
	public AlreadyRegisteredException(String message) {
		super(message);
	}

}
