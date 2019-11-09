package CarpoolHandler;

import java.io.Serializable;

/*
import dataStructures.Iterator;
import dataStructures.Map;
*/
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class UserClass implements User, Comparable, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1733272791991809198L;
	private java.util.Map<Date, Ride> rides;
	private java.util.Map<Date, Ride> lifts;
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
	public void registerRide(Ride lift) {
		lifts.put(lift.getDate(), lift);
	}

	@Override
	public void createRide(Ride ride) {
		rides.put(ride.getDate(), ride);
	}

	/**
	 * @Override public Iterator<Ride> iterateCreatedRides() { // TODO
	 *           Auto-generated method stub return null; }
	 * 
	 * @Override public Iterator<Ride> iterateJoinedRides() { // TODO Auto-generated
	 *           method stub return null; }
	 **/
	@Override
	public Ride removeCreatedRide() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeJoinedRide() {
		// TODO Auto-generated method stub

	}

	@Override
	public int getNumberOfRides() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}

}
