package CarpoolHandler;

import java.io.Serializable;

import CarpoolExceptions.AlreadyExistsElementException;
import CarpoolExceptions.InvalidArgsException;
import CarpoolExceptions.InvalidDateException;
import CarpoolExceptions.InvalidPasswordException;
import CarpoolExceptions.NoRideException;
import CarpoolExceptions.NonExistingElementException;
import CarpoolExceptions.SameUserException;
import Date.Date;
import Ride.Ride;
import Ride.RideClass;
import Ride.RideInMainIterator;
import Ride.RideIterator;
import Ride.RideWrapper;
import User.User;
import User.UserClass;
import User.UserWrapper;
import dataStructures.AVL;
import dataStructures.Iterator;
import dataStructures.Map;
import dataStructures.NoElementException;
import dataStructures.RB;
import dataStructures.SepChainHashTable;
import dataStructures.SortedMap;

/**
 * 
 * @author David Antunes, 55045
 * @author Carolina Duarte, 55645
 * 
 * 
 *
 */
public class CarpoolHandlerClass implements CarpoolHandler, Serializable {

	/**
	 * U - number of Users
	 * 
	 * R - number of Rides
	 * 
	 * RIU- number of Rides in a User (<R)
	 * 
	 * UIR - number of Users in a Ride(<U)
	 * 
	 * DIR - number of Rides in a Date
	 * 
	 * LIU - number of lifts in a User(lifts where the user is present)(<R)
	 * 
	 * D - number of Dates
	 * 
	 */

	private static final long serialVersionUID = 7927190217409345889L;

	/*
	 * Holds the users registered
	 */
	private Map<String, User> users;

	/**
	 * Holds the various rides in each different date
	 */
	private SortedMap<Date, SortedMap<String, Ride>> ridesInDates;

	/**
	 * Holds the current user in session
	 */
	private User currUser;

	/*
	 * Temporal Complexity: best case : O(U) worst case : O(U) average case : O(U)
	 */
	public CarpoolHandlerClass() {
		currUser = null;
		users = new SepChainHashTable<String, User>(250);
		ridesInDates = new AVL<Date, SortedMap<String, Ride>>();
	}

	/*
	 * Temporal Complexity: best case : O(1) worst case : O(1) average case : O(1)
	 */
	@Override
	public UserWrapper getCurrUser() {
		return (UserWrapper) currUser;
	}

	/*
	 * Temporal Complexity: best case : O(1) worst case : O(U) Medium case : O(1 +
	 * occupation factor)
	 */
	@Override
	public void hasUser(String email) throws AlreadyExistsElementException {
		if (users.find(email) != null)
			throw new AlreadyExistsElementException();

	}

	/*
	 * Temporal Complexity: best case : O(1) worst case : O(U) Medium case : O(1 +
	 * occupation factor)
	 */
	@Override
	public void userExists(String email) throws NonExistingElementException {
		if (users.find(email) == null)
			throw new NonExistingElementException();
	}

	/*
	 * Temporal Complexity: best case : O(1) worst case : O(1) Medium case : O(1z)
	 */
	@Override
	public boolean hasCurrUser() {
		return (currUser != null) ? true : false;
	}

	/*
	 * Temporal Complexity: best case : O(1+ occupation factor) worst case : O(U*U)
	 * (needs to rehash) Medium case : O(1 + occupation factor)
	 */
	@Override
	public void register(String email, String name, String password) {

		User user = new UserClass(email, name, password);
		users.insert(email, user);
	}

	/*
	 * Temporal Complexity: best case : O(1) worst case : O(1) Medium case : O(1)
	 */
	@Override
	public int nUsers() {
		return users.size();
	}

	/*
	 * Temporal Complexity: best case : O(1) worst case : O(U) Medium case : O(1 +
	 * occupation factor)
	 */
	@Override
	public void login(String email) {
		currUser = users.find(email);
		currUser.addVisit();

	}

	/*
	 * Temporal Complexity: best case : O(1) worst case : O(U) Medium case : O(1 +
	 * occupation factor)
	 */
	@Override
	public void remove(Date date)
			throws InvalidDateException, NonExistingElementException, AlreadyExistsElementException {

		if (!date.isDateValid(date.getFullDate()))
			throw new InvalidDateException();

		Ride ride = currUser.getRide(date);
		if (ride == null)
			throw new NonExistingElementException();
		if (ride.hasUsers()) {
			throw new AlreadyExistsElementException();
		}

		ridesInDates.find(date).remove(currUser.getEmail());
		currUser.removeCreatedRide(date);
	}

	/*
	 * currUser.hasSomething(date) Temporal Complexity: best case : O(1) worst case
	 * : O(log(R) + log(L)) Medium case : O(log(R))
	 */
	/*
	 * createRide(ride) Temporal Complexity: best case : O(1) worst case : O(log(R))
	 * Medium case : O(log(R))
	 */
	/*
	 * Temporal Complexity: best case : O(1) worst case : O(log(U) + log(R) +
	 * log(L)) Medium case : O(log(U) + log(R))
	 */
	@Override
	public void Ride(String origin, String destiny, Date date, int hour, int minutes, int duration, int seats)
			throws InvalidArgsException, InvalidDateException {

		if (!date.isDateValid(date.getFullDate()))
			throw new InvalidArgsException();
		if (currUser.hasSomething(date))
			throw new InvalidDateException();
		if (hour <= 0 || hour >= 24)
			throw new InvalidArgsException();
		if (minutes < 0 || minutes >= 60)
			throw new InvalidArgsException();
		if (duration <= 0)
			throw new InvalidArgsException();
		if (seats <= 0)
			throw new InvalidArgsException();

		Ride ride = new RideClass(currUser, origin, destiny, date, hour, minutes, duration, seats);
		currUser.createRide(ride);

		SortedMap<String, Ride> DateInfo = ridesInDates.find(date);
		if (DateInfo != null) {
			DateInfo.insert(currUser.getEmail(), ride);
		} else {
			SortedMap<String, Ride> list = new RB<String, Ride>();
			list.insert(currUser.getEmail(), ride);
			ridesInDates.insert(date, list);
		}
	}

	/*
	 * currUser.registerRide(ride) Temporal Complexity: best case : O(1) worst case
	 * : O(log(L)) Medium case : O(log(L))
	 */
	/*
	 * ride.addUser(currUser) Temporal Complexity: best case : O(1) worst case :
	 * O(U) Medium case : O(U/2)
	 */
	/*
	 * currUser.hasSomething(date) Temporal Complexity: best case : O(1) worst case
	 * : O(log(R) + log(L)) Medium case : O(log(R))
	 */

	/*
	 * (user.getRide(date)) Temporal Complexity: best case : O(1) worst case :
	 * O(log(R)) Medium case : O(log(R))
	 */

	/*
	 * Temporal Complexity: best case : O(1) worst case : O(log(U) + log(R) +
	 * log(L)) Medium case : O(log(R))
	 */
	@Override
	public int addLift(String email, Date date) throws SameUserException, NonExistingElementException,
			InvalidDateException, NoRideException, AlreadyExistsElementException {

		User user = users.find(email);
		if (user == null)
			throw new NonExistingElementException();
		if (!date.isDateValid(date.getFullDate()))
			throw new InvalidDateException();

		Ride ride = user.getRide(date);
		if (ride == null)
			throw new NoRideException();

		if (ride.getUser().getEmail().equals(currUser.getEmail()))
			throw new SameUserException();

		if (currUser.hasSomething(date))
			throw new AlreadyExistsElementException();

		int value = ride.addUser(currUser);
		if (value == 0)
			currUser.registerRide(ride);

		return value;
	}

	/*
	 * (ride.removeUser(currUser.getEmail())) /*Temporal Complexity: best case :
	 * O(1) worst case : O(2U) average case : O(U/2)
	 */

	/*
	 * (currUser.removeJoinedRide(date)) Temporal Complexity: best case : O(1) worst
	 * case : O(log(L)) Medium case : O(log(L))
	 */

	/*
	 * Temporal Complexity: best case : O(1) worst case : O(log(L) + 2U) Medium case
	 * : O(Log(L) + U/2)
	 */
	@Override
	public void removeFromRide(Date date) throws InvalidDateException, NonExistingElementException {

		if (!date.isDateValid(date.getFullDate()))
			throw new InvalidDateException();

		Ride ride = currUser.removeJoinedRide(date);
		if (ride == null)
			throw new NonExistingElementException();

		ride.removeUser(currUser.getEmail());
	}

	/*
	 * (user.getRide(date)) Temporal Complexity: best case : O(1) worst case :
	 * O(log(R)) Medium case : O(log(R))
	 */
	/*
	 * Temporal Complexity: best case : O(1) worst case : O(log(R)) Medium case :
	 * O(log(R))
	 */
	@Override
	public RideWrapper check(String email, Date date)
			throws NoRideException, NonExistingElementException, InvalidDateException {

		User user = users.find(email);
		if (user == null)
			throw new NonExistingElementException();
		if (!date.isDateValid(date.getFullDate()))
			throw new InvalidDateException();
		Ride ride = user.getRide(date);
		if (ride == null)
			throw new NoRideException();

		return (RideWrapper) ride;
	}

	/*
	 * Temporal Complexity: best case : O(1) worst case : O(1) Medium case : O(1)
	 */
	@Override
	public String userEmail() throws NonExistingElementException {
		if (currUser == null) {
			throw new NonExistingElementException();
		}
		return currUser.getEmail();
	}

	/*
	 * Temporal Complexity: best case : O(1) worst case : O(1) Medium case : O(1)
	 */
	@Override
	public int nVisitas() {
		return currUser.getVisits();
	}

	/*
	 * Temporal Complexity: best case : O(1) worst case : O(1) Medium case : O(1)
	 */
	@Override
	public String leave() {
		String name = currUser.getName();
		currUser = null;
		return name;
	}

	/*
	 * Temporal Complexity: best case : O(1) worst case : O(log(RIU)) average case : O(log(RIU))
	 */
	@Override
	public Iterator<RideWrapper> iterateUserCreatedRides() {
		return new RideInMainIterator(currUser.iterateCreatedRides());
	}

	/*
	 * Temporal Complexity: best case : O(1) worst case : O(L) average case : O(L)
	 */
	@Override
	public Iterator<RideWrapper> iterateUserJoinedRides() {
		return new RideInMainIterator(currUser.iterateJoinedRides());
	}

	/*
	 * Temporal Complexity: best case : O(1) worst case : O(R) average case : O(R)
	 */
	@Override
	public Iterator<RideWrapper> iterateRidesThroEmails(String email)
			throws NonExistingElementException, NoElementException {
		User user = users.find(email);
		if (user == null)
			throw new NonExistingElementException();
		return new RideInMainIterator(user.iterateCreatedRides());
	}

	/*
	 * Temporal Complexity: best case : O(1) worst case : O(Log(D) + log(RID))
	 * average case : O(Log(D) + log(log(RID)))
	 */
	@Override
	public Iterator<RideWrapper> iterateRidesThroDays(Date date) throws NoElementException {
		SortedMap<String, Ride> DateInfo = ridesInDates.find(date);
		if (DateInfo == null)
			throw new NoElementException();

		return new RideInMainIterator(DateInfo.values());
	}

	/*
	 * Temporal Complexity: best case : O(1) worst case : O(Log(D) + R) average case
	 * : O(Log(D) + R)
	 */
	@Override
	public Iterator<RideWrapper> iterateAll() {

		return new RideIterator<Date, SortedMap<String, Ride>>(ridesInDates);

	}

	/*
	 * Temporal Complexity: best case : O(1) worst case : O(1) average case : O(1)
	 */
	@Override
	public boolean validPassaword(String password, int i) throws InvalidPasswordException {

		if (!(password.length() <= 6 && password.length() >= 4 && password.matches("[a-zA-Z0-9]*"))) {
			if (i == 3)
				throw new InvalidPasswordException();
			else
				return false;
		} else
			return true;

	}

	/*
	 * Temporal Complexity: best case : O(1) worst case : O(1) average case : O(1)
	 */
	@Override
	public boolean isPassCorrect(String email, String password, int i) throws InvalidPasswordException {

		User user = users.find(email);
		if (!user.getPassword().equals(password)) {
			if (i == 3)
				throw new InvalidPasswordException();
			else
				return false;
		}

		else
			return true;
	}

}