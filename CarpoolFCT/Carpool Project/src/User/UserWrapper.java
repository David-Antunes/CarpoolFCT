package User;
/**
 * 
 * @author David Antunes, 55045
 * @author Carolina Duarte, 55645
 * 
 * 
 *
 */
public interface UserWrapper {
	
	/**
	 * Returns the name of the user
	 * @return name - user name
	 */
	String getName();

	/**
	 * Returns the email of the user
	 * @return user email
	 */
	String getEmail();

	/**
	 * Returns the number of rides of the user 
	 * @return number of rides
	 */
	int getNumberOfRides();

}
