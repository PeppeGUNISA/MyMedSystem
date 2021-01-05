/**
 * 
 */
package exception;

/**
 * @author Cristian
 *
 */
public class NotRegisteredException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2183617409837351597L;

	public NotRegisteredException() {
		super("Utente non presente!");
	}
	
	public NotRegisteredException(String message) {
		super(message);
	}
}
