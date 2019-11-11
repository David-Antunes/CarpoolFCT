package CarpoolHandler;


import dataStructures.Entry;
import dataStructures.Iterator;
import dataStructures.NoElementException;
import dataStructures.SortedMap;

public interface CarpoolHandler {

	/**
	 * Returns a iterator of the ride of the current user
	 * @return iterator of rides
	 * @throws NoElementException when the current user does not have any rides
	 */
	Iterator<Ride> iterateUserCreatedRides() throws NoElementException;

	/**
	 * Returns a iterator of lifts of the current user
	 * @return iterator of lifts
	 * @throws NoElementException when the current user does not have any lifts
	 */
	Iterator<Ride> iterateUserJoinedRides() throws NoElementException;

	/**
	 * Returns a iterator of rides of the user with the given email
	 * @param email email of the user
	 * @return iterator of rides
	 * @throws NonExistingElementException when does not exist any user with the given email
	 * @throws NoElementException when the user does not have any rides
	 */
	Iterator<Ride> iterateRidesThroEmails(String email) throws NonExistingElementException, NoElementException;

	/**
	 * Returns a iterator of the rides that happen in the given date
	 * @param date date
	 * @return iterator of rides
	 * @throws NoElementException when there are not any dates in the given date
	 */
	Iterator<Ride> iterateRidesThroDays(Date date) throws NoElementException;

	/**
	 * Returns a iterator with all the date where are rides
	 * @return iterator of dates
	 */
	Iterator<Entry<Date, SortedMap<String, Ride>>> iterateAll();

	/**
	 * Registers a new user to the program with the given email, name ande password
	 * @param email email
	 * @param name name
	 * @param password password
	 */
	public void register(String email, String name, String password);

	/**
	 * Starts the session of the user whit the given email
	 * @param email email
	 */
	void login(String email);

	/**
	 * Checks if there is a user with the same email
	 * @param email email
	 * @throws AlreadyExistsElementException when there is already a user with the same email
	 */
	void hasUser(String email) throws AlreadyExistsElementException;

	/**
	 * Checks is the user with the given email exists
	 * @param email email
	 * @throws NonExistingElementException when there is no user whit the given email
	 */
	void userExists(String email) throws NonExistingElementException;

	/**
	 * Removes the ride with the given date from the current user from everywhere
	 * @param date date
	 * @throws InvalidDateException when the date is invalid
	 * @throws NonExistingElementException when there is no ride with the date from the current user 
	 * @throws AlreadyExistsElementException when that ride already has lifts from other users
	 */
	void remove(Date date) throws InvalidDateException, NonExistingElementException, AlreadyExistsElementException;

	/**
	 * Creates a new ride for the current user whit the given origin, destiny, date,
	 * hour, minutes, duration and seats
	 * @param origin origin
	 * @param destiny destiny
	 * @param date date
	 * @param hour hour
	 * @param minutes minutes
	 * @param duration duration
	 * @param seats seats
	 * @throws InvalidArgsException  when any of the arguments are invalid 
	 * @throws InvalidDateException when the date is invalid
	 */
	void Ride(String origin, String destiny, Date date, int hour, int minutes, int duration, int seats)
			throws InvalidArgsException, InvalidDateException;

	/**
	 * Removes the current user from the ride he has a lift in the given date
	 * @param date date
	 * @throws InvalidDateException when the date is not valid
	 * @throws NonExistingElementException when the current user does not have any lifts that day
	 */
	void removeFromRide(Date date) throws InvalidDateException, NonExistingElementException;

	/**
	 * Adds a lift to the current user in the ride of the user with the given email in the given date
	 * @param email email of the user
	 * @param date date
	 * @return 0 if the user has a seat on the ride or returns the number of users waiting for a seat
	 * @throws SameUserException when the current user and the user whit the ride are the same
	 * @throws NonExistingElementException when there is no user with the given email
	 * @throws InvalidDateException when the date is invalid
	 * @throws NoRideException when the user does not have any rides in that date
	 * @throws AlreadyExistsElementException when the current user already has a ride or a lift in that date
	 */
	int addLift(String email, Date date) throws SameUserException, NonExistingElementException, InvalidDateException,
			NoRideException, AlreadyExistsElementException;

	/**
	 * Returns the ride of the user with the given email in the given date
	 * @param email email of the user
	 * @param date date
	 * @return ride
	 * @throws NoRideException when the user has no ride in that date
	 * @throws NonExistingElementException when there is no user with the email given
	 * @throws InvalidDateException when the date is invalid
	 */
	Ride check(String email, Date date) throws NoRideException, NonExistingElementException, InvalidDateException;

	/**
	 * Checks if there is any user whit the session initiated
	 * @return <code>true</code> if there is not or <code>false</code> if there is
	 */
	boolean hasCurrUser();

	/**
	 * Returns the number of users 
	 * @return number of users
	 */
	int nUsers();

	/**
	 * Return email of the current user
	 * @return email 
	 * @throws NonExistingElementException when there is no current user 
	 */
	public String userEmail() throws NonExistingElementException;

	/**
	 * Returns the current user
	 * @return current user
	 */
	public User getCurrUser();

	/**
	 * Checks if the password given is valid
	 * @param password password
	 * @param i number of attempts
	 * @return <code>true</code> if is valid or <code>false</code> if is not
	 * @throws InvalidPasswordException when the number of attempts is exceeded
	 */
	public boolean validPassaword(String password, int i) throws InvalidPasswordException;

	/**
	 * Checks if the password given matches the password of the user wiht the given email
	 * @param email email of the user
	 * @param password password
	 * @param i number of attempts
	 * @return <code>true</code> if is matches or <code>false</code> if does not match
	 * @throws InvalidPasswordException when the number of attempts is exceeded
	 */
	public boolean isPassCorrect(String email, String password, int i) throws InvalidPasswordException;



	/**
	 * Returns the number of visits of the user that started a session
	 * @return
	 */
	int nVisitas();

	/**
	 * Ends the current session
	 * @return name of the user
	 */
	String leave();

}