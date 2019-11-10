package CarpoolHandler;

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
