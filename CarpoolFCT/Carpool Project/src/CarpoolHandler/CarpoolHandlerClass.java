package CarpoolHandler;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.TreeMap;

import javax.sql.rowset.spi.TransactionalWriter;

import dataStructures.NoElementException;

public class CarpoolHandlerClass implements CarpoolHandler, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7927190217409345889L;
	private java.util.Map<String, User> users;
	private java.util.Map<Date, List<Ride>> ridesInDates;
	private User currUser;

	public CarpoolHandlerClass() {
		currUser = null;
		users = new TreeMap<String, User>();
		ridesInDates = new TreeMap<Date, List<Ride>>();
	}

	public User getCurrUser() {
		return currUser;
	}

	public void hasUser(String email) throws AlreadyExistsElementException {
		if (users.containsKey(email))
			throw new AlreadyExistsElementException();

	}

	public void userExists(String email) throws NonExistingElementException {
		if (!users.containsKey(email))
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
		users.put(email, user);
	}

	public int nUsers() {
		return users.size();
	}

	@Override
	public void login(String email) {
		currUser = users.get(email);
		currUser.addVisit();

	}

	@Override
	public boolean hasUser() {
		// TODO Auto-generated method stub
		return false;
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

		Ride ride = currUser.removeCreatedRide(date);
		ridesInDates.get(date).remove(ride);
		if (ridesInDates.get(date).isEmpty())
			ridesInDates.remove(date);
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
		if (minutes <= 0 || minutes >= 60)
			throw new InvalidArgsException();
		if (duration <= 0)
			throw new InvalidArgsException();
		if (seats <= 0)
			throw new InvalidArgsException();

		Ride ride = new RideClass(currUser, origin, destiny, date, hour, minutes, duration, seats);
		currUser.createRide(ride);

		if (ridesInDates.containsKey(date)) {
			ridesInDates.get(date).add(ride);
		} else {
			List<Ride> list = new ArrayList<Ride>();
			list.add(ride);
			ridesInDates.put(date, list);
		}
	}

	public int addLift(String email, Date date)
			throws NonExistingElementException, InvalidDateException, NoRideException, AlreadyExistsElementException {

		if (!users.containsKey(email))
			throw new NonExistingElementException();
		if (!ridesInDates.containsKey(date))
			throw new InvalidDateException();
		if (currUser.hasSomething(date))
			throw new AlreadyExistsElementException();

		User user = users.get(email);

		if (!user.hasRide(date))
			throw new NoRideException();

		Ride ride = user.getRide(date);

		currUser.registerRide(ride);
		return ride.addUser(currUser);
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

		if (!users.containsKey(email))
			throw new NonExistingElementException();
		if (!date.isDateValid(date.getFullDate()))
			throw new InvalidDateException();
		User user = users.get(email);

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

		User user = users.get(email);
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
	public Iterator<Ride> iterateRidesThroEmails(String email) throws NoElementException {
		if (!users.containsKey(email))
			throw new NoElementException();
		return users.get(email).iterateCreatedRides();
	}

	@Override
	public Iterator<Ride> iterateRidesThroDays(Date date) throws NoElementException {
		if (!ridesInDates.containsKey(date))
			throw new NoElementException();
		return ridesInDates.get(date).iterator();
	}

	@Override
	public Iterator<Date> iterateAll() {
		
		return ridesInDates.keySet().iterator();
	}

}