package CarpoolHandler;

import java.util.Iterator;

public interface Ride {

	/**
	 * Returns the user that has the ride
	 * @return user
	 */
	User getUser();

	/**
	 * Returns the origin of the ride
	 * @return origin
	 */
	String getOrigin();

	/**
	 * Returns the destination of the ride
	 * @return ride
	 */
	String getDestination();

	/**
	 * returns the date of the ride
	 * @return date
	 */
	Date getDate();

	/**
	 * Returns the hour of the ride
	 * @return hour
	 */
	int getHour();

	/**
	 * Returns the minutes of the ride
	 * @return minutes
	 */
	int getMinutes();
	
	/**
	 * Returns the number of seats the ride has 
	 * @return number of seats
	 */
	int getSeats();

	/**
	 * Returns the duration of ride
	 * @return duration
	 */
	int getDuration();

	/**
	 * Returns the number of users waiting in line for a place on the ride
	 * @return number of users in line
	 */
	int getUsersInQueue();

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
	User removeUser(String user);

	/**
	 * Returns iterator of users in the ride
	 * @return iterator of users
	 */
	Iterator<User> iterateUsers();
	
	/**
	 * Returns the number of remaining seats in the ride
	 * @return number of remaining seats
	 */
	int getRemainingSeats();
	
	/**
	 * Checks if the ride as any users
	 * @return <code>true</code> if it has or <code>false</code> if does not have any
	 */
	public boolean hasUsers();
}