package CarpoolHandler;

import dataStructures.Iterator;
import dataStructures.List;
import dataStructures.Map;
import dataStructures.NoElementException;

public class CarpoolHandlerClass implements CarpoolHandler {
	
	private Map<String, User> users;
	private Map<Date, List<Ride>> ridesInDates;
	private User curUser;

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

	@Override
	public void register() {
		// TODO Auto-generated method stub
		
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
