package CarpoolHandler;

import java.util.Iterator;

public interface User {

	String getName();

	String getEmail();

	String getPassword();
	
	int getVisits();
	
	void addVisit();

	boolean hasSomething();

	void registerRide();

	void createRide();

	Iterator<Ride> iterateCreatedRides();

	Iterator<Ride> iterateJoinedRides();

	Ride removeCreatedRide();

	void removeJoinedRide();

	int getNumberOfRides();
}
