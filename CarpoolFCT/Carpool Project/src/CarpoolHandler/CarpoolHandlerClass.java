package CarpoolHandler;

import java.io.Serializable;

import dataStructures.Entry;
import dataStructures.Iterator;
import dataStructures.NoElementException;
import dataStructures.SortedMap;
import dataStructures.SortedMapWithJavaClass;

public class CarpoolHandlerClass implements CarpoolHandler, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7927190217409345889L;
	private SortedMap<String, User> users;
	private SortedMap<Date, SortedMap<String, Ride>> ridesInDates;
	private User currUser;

	public CarpoolHandlerClass() {
		currUser = null;
		users = new SortedMapWithJavaClass<String, User>();
		ridesInDates = new SortedMapWithJavaClass<Date, SortedMap<String, Ride>>();
	}

	public User getCurrUser() {
		return currUser;
	}

	public void hasUser(String email) throws AlreadyExistsElementException {
		if (users.find(email) != null)
			throw new AlreadyExistsElementException();

	}

	public void userExists(String email) throws NonExistingElementException {
		if (users.find(email) == null)
			throw new NonExistingElementException();
	}

	public boolean hasCurrUser() {
		if (currUser != null)
			return true;
		else
			return false;
	}

	@Override
	public void register(String email, String name, String password) {

		User user = new UserClass(email, name, password);
		users.insert(email, user);
	}

	public int nUsers() {
		return users.size();
	}

	@Override
	public void login(String email) {
		currUser = users.find(email);
		currUser.addVisit();

	}

	@Override
	public void remove(Date date)
			throws InvalidDateException, NonExistingElementException, AlreadyExistsElementException {

		if (!date.isDateValid(date.getFullDate()))
			throw new InvalidDateException();
		if (!currUser.hasRide(date))
			throw new NonExistingElementException();
		if (currUser.rideHasLift(date)) {
			throw new AlreadyExistsElementException();
		}

		ridesInDates.find(date).remove(currUser.getEmail());
		currUser.removeCreatedRide(date);
	}

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

		if (ridesInDates.find(date) != null) {
			ridesInDates.find(date).insert(currUser.getEmail(), ride);
		} else {
			SortedMap<String, Ride> list = new SortedMapWithJavaClass<String, Ride>();
			list.insert(currUser.getEmail(), ride);
			ridesInDates.insert(date, list);
		}
		if (currUser.hasLift(date)) {
			currUser.removeJoinedRide(date);
		}
	}

	public int addLift(String email, Date date) throws SameUserException, NonExistingElementException,
			InvalidDateException, NoRideException, AlreadyExistsElementException {

		if (users.find(email) == null)
			throw new NonExistingElementException();
		if (!date.isDateValid(date.getFullDate()))
			throw new InvalidDateException();

		User user = users.find(email);

		if (!user.hasRide(date))
			throw new NoRideException();

		Ride ride = user.getRide(date);

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
		if (!currUser.hasLift(date))
			throw new NonExistingElementException();

		Ride ride = currUser.removeJoinedRide(date);
		ride.removeUser(currUser.getName());
	}

	@Override
	public Ride check(String email, Date date)
			throws NoRideException, NonExistingElementException, InvalidDateException {

		if (users.find(email) == null)
			throw new NonExistingElementException();
		if (!date.isDateValid(date.getFullDate()))
			throw new InvalidDateException();

		User user = users.find(email);

		if (!user.hasRide(date))
			throw new NoRideException();

		return user.getRide(date);
	}

	public String userEmail() throws NonExistingElementException {
		if (currUser == null) {
			throw new NonExistingElementException();
		}
		return currUser.getEmail();
	}

	public boolean validPassaword(String password, int i) throws InvalidPasswordException {

		if (!(password.length() <= 6 && password.length() >= 4 && password.matches("[a-zA-Z0-9]*"))) {
			if (i == 3)
				throw new InvalidPasswordException();
			else
				return false;
		} else
			return true;

	}

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
	public Iterator<Ride> iterateUserCreatedRides() {
		return currUser.iterateCreatedRides();
	}

	@Override
	public Iterator<Ride> iterateUserJoinedRides() {
		return currUser.iterateJoinedRides();
	}

	@Override
	public Iterator<Ride> iterateRidesThroEmails(String email) throws NonExistingElementException, NoElementException {
		if (users.find(email) == null)
			throw new NonExistingElementException();
		return users.find(email).iterateCreatedRides();
	}

	@Override
	public Iterator<Ride> iterateRidesThroDays(Date date) throws NoElementException {
		if (ridesInDates.find(date) == null)
			throw new NoElementException();

		return ridesInDates.find(date).values();
	}

	@Override
	public Iterator<Entry<Date, SortedMap<String, Ride>>> iterateAll() {

		return ridesInDates.iterator();

	}

}