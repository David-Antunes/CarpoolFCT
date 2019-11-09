package CarpoolHandler;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

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

	/**
	 * @Override public Iterator<Ride> iterateUserCreatedRides() throws
	 *           NoElementException { // TODO Auto-generated method stub return
	 *           null; }
	 * 
	 * @Override public Iterator<Ride> iterateUserJoinedRides() throws
	 *           NoElementException { // TODO Auto-generated method stub return
	 *           null; }
	 * 
	 * @Override public Iterator<Ride> iterateRidesThroEmails() throws
	 *           NoElementException { // TODO Auto-generated method stub return
	 *           null; }
	 * 
	 * @Override public Iterator<Ride> iterateRidesThroDays() throws
	 *           NoElementException { // TODO Auto-generated method stub return
	 *           null; }
	 * 
	 * @Override public Iterator<Ride> iterateAll() throws NoElementException { //
	 *           TODO Auto-generated method stub return null; }
	 **/

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
	public Ride remove() {
		// TODO Auto-generated method stub
		return null;
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
			throws NonExistingElementException, InvalidDateException, NoRideException {

		if (!users.containsKey(email))
			throw new NonExistingElementException();
		if (!ridesInDates.containsKey(date))
			throw new InvalidDateException();

		User user = users.get(email);

		if (!user.hasRide(date))
			throw new NoRideException();

		Ride ride = user.getRide(date);
		currUser.registerRide(ride);
		return ride.addUser(currUser);
	}

	@Override
	public void removeFromRide() {
		
	}

	@Override
	public void check() {
		
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

}
