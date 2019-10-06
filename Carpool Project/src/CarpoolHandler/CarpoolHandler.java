package CarpoolHandler;

import dataStructures.Iterator;
import dataStructures.NoElementException;

public interface CarpoolHandler {
	
	Iterator<Ride> iterateUserCreatedRides() throws NoElementException;
	
	Iterator<Ride> iterateUserJoinedRides() throws NoElementException;

	Iterator<Ride> iterateRidesThroEmails() throws NoElementException;
	
	Iterator<Ride> iterateRidesThroDays() throws NoElementException;
	
	Iterator<Ride> iterateAll() throws NoElementException;
	
	void register();
	
	void login();
	
	boolean hasUser();
	
	Ride remove();
	
	void Ride();
	
	void removeFromRide();
	
	void check();
}
