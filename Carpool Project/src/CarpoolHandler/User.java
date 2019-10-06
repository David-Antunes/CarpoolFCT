package CarpoolHandler;

import dataStructures.Iterator;

public interface User {

	String getName();

	String getEmail();

	String getPassword();

	boolean hasSomething();

	void registerRide();

	void createRide();

	Iterator<Ride> iterateCreatedRides();

	Iterator<Ride> iterateJoinedRides();

	Ride removeCreatedRide();

	void removeJoinedRide();

	int getNumberOfRides();
}
