
package CarpoolHandler;

import java.util.Iterator;

import dataStructures.NoElementException;

public interface User {

	String getName();

	String getEmail();

	String getPassword();

	int getVisits();

	void addVisit();

	boolean hasSomething(Date date);

	boolean hasRide(Date date);

	public boolean hasLift(Date date);

	void registerRide(Ride lift);

	void createRide(Ride ride);

	Ride getRide(Date date);

	Iterator<Ride> iterateCreatedRides() throws NoElementException;

	Iterator<Ride> iterateJoinedRides() throws NoElementException;

	Ride removeCreatedRide(Date date);

	Ride removeJoinedRide(Date date);

	int getNumberOfRides();

	public boolean rideHasLift(Date date);
}