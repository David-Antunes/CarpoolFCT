/**
 * 
 */
package dataStructures;

/**
 * @author AED_19_20
 * @author David Antunes, 55045
 * @author Carolina Duarte, 55645
 * 
 * 
 *
 */
public class InvalidPositionException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public InvalidPositionException( )
	{
	super();
	}
	public InvalidPositionException( String msg )
	{
	super(msg);
	}
}
