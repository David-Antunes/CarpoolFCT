package CarpoolHandler;

import java.io.Serializable;

import CarpoolExceptions.AlreadyExistsElementException;
import CarpoolExceptions.InvalidArgsException;
import CarpoolExceptions.InvalidDateException;
import CarpoolExceptions.InvalidPasswordException;
import CarpoolExceptions.NoRideException;
import CarpoolExceptions.NonExistingElementException;
import CarpoolExceptions.SameUserException;
import dataStructures.AVL;
import dataStructures.Iterator;
import dataStructures.Map;
import dataStructures.NoElementException;
import dataStructures.SepChainHashTable;
import dataStructures.SortedMap;

/**
 * 
 * @author David Antunes, 55045
 * @author Carolina Duarte, 55645
 *
 */
public class CarpoolHandlerClass implements CarpoolHandler, Serializable {

	/**
	 * U - number of user
	 * R - number of Rides
	 */
	
	
	private static final long serialVersionUID = 7927190217409345889L;

	private Map<String, User> users;

	/**
	 * Holds the various rides in each different date
	 */
	private SortedMap<Date, SortedMap<String, Ride>> ridesInDates;

	/**
	 * Holds the current user in session
	 */
	private User currUser;


	/*Temporal Complexity:
	 * best case : O(U)
	 * worst case : O(U)
	 * average case : O(U)
	 */
	public CarpoolHandlerClass() {
		currUser = null;
		users = new SepChainHashTable<String, User>(10);
		ridesInDates = new AVL<Date, SortedMap<String, Ride>>();
	}


	/*Temporal Complexity:
	 * best case : O(1)
	 * worst case : O(1)
	 * average case : O(1)
	 */
	@Override
	public UserWrapper getCurrUser() {
		return (UserWrapper) currUser;
	}


	/*Temporal Complexity:
	 * best case : O(1)
	 * worst case : O(U)
	 * Medium case : O(1 + occupation factor)
	 */
	@Override
	public void hasUser(String email) throws AlreadyExistsElementException {
		if (users.find(email) != null)
			throw new AlreadyExistsElementException();

	}

	/*Temporal Complexity:
	 * best case : O(1)
	 * worst case : O(U)
	 * Medium case : O(1 + occupation factor)
	 */
	@Override
	public void userExists(String email) throws NonExistingElementException {
		if (users.find(email) == null)
			throw new NonExistingElementException();
	}

	/*Temporal Complexity:
	 * best case : O(1)
	 * worst case : O(n)
	 * Medium case : O(1 + occupation factor)
	 */
	@Override
	public boolean hasCurrUser() {
		return (currUser != null) ? true : false;
	}

	/*Temporal Complexity:
	 * best case : O(1+ occupation factor)
	 * worst case : O(U*U) (vai fazer reash)
	 * Medium case : O(1 + occupation factor)
	 */
	@Override
	public void register(String email, String name, String password) {

		User user = new UserClass(email, name, password);
		users.insert(email, user);
	}

	/*Temporal Complexity:
	 * best case : O(1)
	 * worst case : O(1)
	 * Medium case : O(1)
	 */
	@Override
	public int nUsers() {
		return users.size();
	}

	/*Temporal Complexity:
	 * best case : O(1)
	 * worst case : O(U)
	 * Medium case : O(1 + occupation factor)
	 */
	@Override
	public void login(String email) {
		currUser = users.find(email);
		currUser.addVisit();

	}

	/*Temporal Complexity:
	 * best case : O(1)
	 * worst case : O(U)
	 * Medium case : O(1 + occupation factor)
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

	/*currUser.hasSomething(date)
	 * Temporal Complexity:
	 * best case : O(log(n))
	 * worst case : O(log(n)
	 * Medium case : O(log(n))
	 */
	
	/*createRide(ride)
	 * Temporal Complexity:
	 * best case : O(log(n))
	 * worst case : O(log(n))
	 * Medium case : O(log(n))
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
			SortedMap<String, Ride> list = new AVL<String, Ride>();
			list.insert(currUser.getEmail(), ride);
			ridesInDates.insert(date, list);
		}
		/**
		if (currUser.hasLift(date)) {
			currUser.removeJoinedRide(date);
		}
		*/
	}

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

	@Override
	public void removeFromRide(Date date) throws InvalidDateException, NonExistingElementException {

		if (!date.isDateValid(date.getFullDate()))
			throw new InvalidDateException();
		
		Ride ride = currUser.removeJoinedRide(date);	
		if (ride == null)	
			throw new NonExistingElementException();
		
		ride.removeUser(currUser.getEmail());
	}

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

	@Override
	public String userEmail() throws NonExistingElementException {
		if (currUser == null) {
			throw new NonExistingElementException();
		}
		return currUser.getEmail();
	}

	@Override
	public int nVisitas() {
		return currUser.getVisits();
	}

	@Override
	public String leave() {
		String name = currUser.getName();
		currUser = null;
		return name;
	}

	@Override
	public Iterator<RideWrapper> iterateUserCreatedRides() {
		return new RideInMainIterator(currUser.iterateCreatedRides());
	}

	@Override
	public Iterator<RideWrapper> iterateUserJoinedRides() {
		return new RideInMainIterator(currUser.iterateJoinedRides());
	}

	@Override
	public Iterator<RideWrapper> iterateRidesThroEmails(String email) throws NonExistingElementException, NoElementException {
		User user = users.find(email);	
		if (user == null)	
			throw new NonExistingElementException();
		return new RideInMainIterator(user.iterateCreatedRides());
	}

	@Override
	public Iterator<RideWrapper> iterateRidesThroDays(Date date) throws NoElementException {
		SortedMap<String, Ride> DateInfo = ridesInDates.find(date);	
		if (DateInfo == null)	
			throw new NoElementException();

		return new RideInMainIterator(DateInfo.values());
	}

	@Override
	public Iterator<RideWrapper> iterateAll() {

		return new RideIterator<Date, SortedMap<String, Ride>>(ridesInDates);

	}

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