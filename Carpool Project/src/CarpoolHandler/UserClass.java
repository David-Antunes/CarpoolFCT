package CarpoolHandler;

import java.io.Serializable;

import dataStructures.Iterator;
import dataStructures.Map;

public class UserClass implements User, Comparable, Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1733272791991809198L;
	private Map<Date, Ride> rides;
	private Map<Date, Ride> lifts;
	private int numberOfVisits;

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getEmail() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasSomething() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void registerRide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void createRide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Iterator<Ride> iterateCreatedRides() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator<Ride> iterateJoinedRides() {
		// TODO Auto-generated method stub
		return null;
	}

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
