package CarpoolHandler;

import java.util.Iterator;
import dataStructures.NoElementException;
public interface CarpoolHandler {
	
	Iterator<Ride> iterateUserCreatedRides() throws NoElementException;
	
	Iterator<Ride> iterateUserJoinedRides() throws NoElementException;

	Iterator<Ride> iterateRidesThroEmails() throws NoElementException;
	
	Iterator<Ride> iterateRidesThroDays() throws NoElementException;
	
	Iterator<Ride> iterateAll() throws NoElementException;
	
	public void register(String email, String name, String password) throws UserExistsException;
	
	void login();
	
	boolean hasUser();
	
	Ride remove();
	
	void Ride();
	
	void removeFromRide();
	
	void check();
}
