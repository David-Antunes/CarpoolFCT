package CarpoolHandler;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import dataStructures.NoElementException;

public class CarpoolHandlerClass implements CarpoolHandler, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7927190217409345889L;
	private Map<String, User> users;
	private Map<Date, List<Ride>> ridesInDates;
	private User curUser;

	public CarpoolHandlerClass() {
		curUser = null;
		users = new TreeMap<String, User>();
		ridesInDates = new TreeMap<Date, List<Ride>>();
	}

	@Override
	public Iterator<Ride> iterateUserCreatedRides() throws NoElementException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator<Ride> iterateUserJoinedRides() throws NoElementException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator<Ride> iterateRidesThroEmails() throws NoElementException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator<Ride> iterateRidesThroDays() throws NoElementException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator<Ride> iterateAll() throws NoElementException {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean hasUser(String email) {
		if (users.containsKey(email))
			return true;
		else
			return false;
	}

	@Override
	public void register(String email, String name, String password) throws UserExistsException {

		if (hasUser(email)) {
			throw new UserExistsException();
		}

		User user = new UserClass(email, name, password);
	}

	@Override
	public void login() {
		// TODO Auto-generated method stub

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
	public void Ride() {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeFromRide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void check() {
		// TODO Auto-generated method stub

	}

}
