package CarpoolHandler;

import java.util.Iterator;

import dataStructures.NoElementException;

public interface CarpoolHandler {

	Iterator<Ride> iterateUserCreatedRides() throws NoElementException;

	Iterator<Ride> iterateUserJoinedRides() throws NoElementException;

	Iterator<Ride> iterateRidesThroEmails(String email) throws NoElementException;

	Iterator<Ride> iterateRidesThroDays(Date date) throws NoElementException;

	Iterator<Date> iterateAll();

	public void register(String email, String name, String password);

	void login(String email);

	void hasUser(String email) throws AlreadyExistsElementException;

	void userExists(String email) throws NonExistingElementException;

	void remove(Date date) throws InvalidDateException, NonExistingElementException, AlreadyExistsElementException;

	void Ride(String origin, String destiny, Date date, int hour, int minutes, int duration, int seats)
			throws InvalidArgsException, InvalidDateException;

	void removeFromRide(Date date) throws InvalidDateException, NonExistingElementException;

	int addLift(String email, Date date) throws SameUserException, NonExistingElementException, InvalidDateException,
			NoRideException, AlreadyExistsElementException;

	Ride check(String email, Date date) throws NoRideException, NonExistingElementException, InvalidDateException;

	boolean hasCurrUser();

	int nUsers();

	public String userEmail() throws NonExistingElementException;

	public User getCurrUser();

	public boolean validPassaword(String password, int i) throws InvalidPasswordException;

	public boolean isPassCorrect(String email, String password, int i) throws InvalidPasswordException;

	boolean hasUser();

	int nVisitas();

	String leave();

}