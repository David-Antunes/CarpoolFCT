package CarpoolExceptions;

/**
 * 
 * @author David Antunes, 55045
 * @author Carolina Duarte, 55645
 *
 */
public class SameUserException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SameUserException() {
		super();
	}

	public SameUserException(String msg) {
		super(msg);
	}

}
