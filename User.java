package CarpoolHandler;

import java.util.Iterator;

import dataStructures.NoElementException;

public interface User {

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
	 * Returns password of the user
	 * @return user password
	 */
	String getPassword();

	/**
	 * Returns the number of times the user registers
	 * @return visit number
	 */
	int getVisits();

	/**
	 * Increments the number of visits of the user
	 */
	void addVisit();

	/**
	 * Checks if the user has any rides or lifts in the given date
	 * @param date date
	 * @return <code>true</code> if the user has or <code>false</code> if does not have anything
	 */
	boolean hasSomething(Date date);

	/**
	 * Checks if the user has any rides in the given date
	 * @param date date
	 * @return <code>true</code> if the user has or <code>false</code> if does not have any ride
	 */
	boolean hasRide(Date date);

	/**
	 * Checks if the user has any lifts in the given date
	 * @param date date
	 * @return <code>true</code> if the user has or <code>false</code> if does not have any lift
	 */
	public boolean hasLift(Date date);

	/**
	 * Registers another lift to the  user
	 * @param lift lift
	 */
	void registerRide(Ride lift);

	/**
	 * Registers another ride to the user
	 * @param ride ride
	 */
	void createRide(Ride ride);

	/**
	 * Returns the ride of the given date
	 * @param date date
	 * @return ride
	 */
	Ride getRide(Date date);

	/**
	 * Returns iterator of the rides of the user
	 * @return iterator of rides
	 * @throws NoElementException when there is no rides
	 */
	Iterator<Ride> iterateCreatedRides() throws NoElementException;

	/**
	 * Returns iterator of lifts of the user
	 * @return iterator of lifts
	 * @throws NoElementException when there is no lifts
	 */
	Iterator<Ride> iterateJoinedRides() throws NoElementException;

	/**
	 * Removes the ride of the user in the given date
	 * @param date date
	 * @return ride removed
	 */
	Ride removeCreatedRide(Date date);

	/**
	 * Remove the lift of the user in the given date
	 * @param date date 
	 * @return lift removed
	 */
	Ride removeJoinedRide(Date date);

	/**
	 * Returns the number of rides of the user 
	 * @return number of rides
	 */
	int getNumberOfRides();

	/**
	 * Checks if the ride whit the given name has any lifts from other users
	 * @param date date
	 * @return <code>true</code> if it has or <code>false</code> if does not have any 
	 */
	public boolean rideHasLift(Date date);
}