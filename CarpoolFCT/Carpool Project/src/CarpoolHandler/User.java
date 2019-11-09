package CarpoolHandler;

public interface User {

	String getName();

	String getEmail();

	String getPassword();

	int getVisits();

	void addVisit();

	boolean hasSomething(Date date);

	void registerRide(Ride lift);

	void createRide(Ride ride);

	/**
	 * Iterator<Ride> iterateCreatedRides();
	 * 
	 * Iterator<Ride> iterateJoinedRides();
	 */
	Ride removeCreatedRide();

	void removeJoinedRide();

	int getNumberOfRides();
}
