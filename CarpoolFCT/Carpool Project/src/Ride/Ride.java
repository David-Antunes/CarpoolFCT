package Ride;

import User.User;
/**
 * 
 * @author David Antunes, 55045
 * @author Carolina Duarte, 55645
 * 
 * 
 *
 */
public interface Ride extends RideWrapper{



	/**
	 * Adds a user to ride 
	 * if the ride is full the user stays in a queue waiting for a seat in the ride
	 * @param user user
	 * @return 0 if the user got a seat in the ride or returns the number of people waiting if all the seats are taken
	 */
	int addUser(User user);

	/**
	 * Removes the user from the ride or the waiting line
	 * if the user was on the ride and are users in the queue the first in the queue gets a seat
	 * @param user user
	 * @return user removed
	 */
	void removeUser(String user);

	
	
	/**
	 * Returns the number of remaining seats in the ride
	 * @return number of remaining seats
	 */
	int getRemainingSeats();
	
	/**
	 * Checks if the ride as any users
	 * @return <code>true</code> if it has or <code>false</code> if does not have any
	 */
	boolean hasUsers();

	

	

}