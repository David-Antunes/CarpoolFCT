package CarpoolHandler;

import java.io.Serializable;

/**
import dataStructures.Iterator;
import dataStructures.List;
import dataStructures.Map;
*/

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
	private java.util.Map<String, User> users;
	private java.util.Map<Date, List<Ride>> ridesInDates;
	private User curUser;

	public CarpoolHandlerClass() {
		curUser = null;
		users = new TreeMap<String, User>();
		ridesInDates = new TreeMap<Date, List<Ride>>();
	}

	/**
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
	**/
	public void hasUser(String email) throws AlreadyExistsElementException
	{
		if (users.containsKey(email)) 
			throw new AlreadyExistsElementException();
		
	
	}
	
	public void userExists(String email) throws NonExistingElementException{
		if(!users.containsKey(email))
			throw new NonExistingElementException();
	}
	
	public boolean hasCurUsar() {
		if (curUser != null)
			return true;
		else 
			return false;
	}

	@Override
	public void register(String email, String name, String password)  {


		User user = new UserClass(email, name, password);
		users.put(email, user);
	}

	public int nUsers() {
		return users.size();
	}
	@Override
	public void login(String email) {
		curUser = users.get(email);
		curUser.addVisit();

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
	
	public String userEmail() throws NonExistingElementException {
		if(curUser == null) {
			throw new NonExistingElementException();
		}
		return curUser.getEmail();
	}
	
	public boolean validPassaword(String password ,int i) throws InvalidPasswordException {
		
		
		if (!(password.length() <= 6 && password.length() >= 4 && password.matches("[a-zA-Z0-9]*")) ) {
			if(i == 3)
				throw new InvalidPasswordException();
			else 
				return false;
		}
		else
			return true;
			
		}
	
	public boolean isPassCorrect(String email, String password, int i) throws InvalidPasswordException {
		
		User user = users.get(email);
		if(!user.getPassword().equals(password)) {
			if(i == 3)
				throw new InvalidPasswordException();
			else
				return false;
		}
		
		else
			return true;
	}
	
	public int nVisitas() {
		return curUser.getVisits();
	}


}
