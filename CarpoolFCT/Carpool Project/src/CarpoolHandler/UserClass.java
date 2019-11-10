package CarpoolHandler;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import dataStructures.NoElementException;

public class UserClass implements User, Comparable<Object>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1733272791991809198L;
	private Map<Date, Ride> rides;
	private Map<Date, Ride> lifts;
	private int visits;

	private String email;
	private String name;
	private String password;

	public UserClass(String email, String name, String password) {
		this.email = email;
		this.name = name;
		this.password = password;
		rides = new TreeMap<Date, Ride>();
		lifts = new TreeMap<Date, Ride>();
		visits = 0;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getEmail() {
		return email;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public int getVisits() {
		return visits;
	}

	@Override
	public void addVisit() {
		visits++;
	}

	@Override
	public boolean hasSomething(Date date) {
		if (rides.containsKey(date))
			return false;
		if (lifts.containsKey(date))
			return false;
		return true;
	}

	@Override
	public boolean hasRide(Date date) {
		return rides.containsKey(date);
	}

	public boolean hasLift(Date date) {
		return lifts.containsKey(date);
	}

	@Override
	public void registerRide(Ride lift) {
		lifts.put(lift.getDate(), lift);
	}

	@Override
	public void createRide(Ride ride) {
		rides.put(ride.getDate(), ride);
	}

	@Override
	public Ride getRide(Date date) {
		return rides.get(date);
	}

	public boolean rideHasLift(Date date) {
		Ride ride = rides.get(date);
		return ride.hasUsers();
	}

	@Override
	public Ride removeCreatedRide(Date date) {

		return rides.remove(date);
	}

	@Override
	public Ride removeJoinedRide(Date date) {

		return lifts.remove(date);

	}

	@Override
	public int getNumberOfRides() {
		return rides.size();
	}

	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Iterator<Ride> iterateCreatedRides() throws NoElementException {
		if (rides.isEmpty())
			throw new NoElementException();

		return rides.values().iterator();

	}

	@Override
	public Iterator<Ride> iterateJoinedRides() throws NoElementException {
		if (lifts.isEmpty())
			throw new NoElementException();

		return lifts.values().iterator();

	}

}