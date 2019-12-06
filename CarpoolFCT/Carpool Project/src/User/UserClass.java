
package User;

import java.io.Serializable;

import Date.Date;
import Ride.Ride;
import dataStructures.Iterator;
import dataStructures.NoElementException;
import dataStructures.RB;
import dataStructures.SortedMap;
/**
 * 
 * @author David Antunes, 55045
 * @author Carolina Duarte, 55645
 * 
 * 
 *
 */
public class UserClass implements User, Comparable<Object>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1733272791991809198L;
	private SortedMap<Date, Ride> rides;
	private SortedMap<Date, Ride> lifts;
	private int visits;

	private String email;
	private String name;
	private String password;

	/*
	 * Temporal Complexity: best case : O(1) worst case : O(1) Medium case : O(1)
	 */
	public UserClass(String email, String name, String password) {
		this.email = email;
		this.name = name;
		this.password = password;
		rides = new RB<Date, Ride>();
		lifts = new RB<Date, Ride>();
		visits = 0;
	}

	/*
	 * Temporal Complexity: best case : O(1) worst case : O(1) Medium case : O(1)
	 */
	@Override
	public String getName() {
		return name;
	}

	/*
	 * Temporal Complexity: best case : O(1) worst case : O(1) Medium case : O(1)
	 */
	@Override
	public String getEmail() {
		return email;
	}

	/*
	 * Temporal Complexity: best case : O(1) worst case : O(1) Medium case : O(1)
	 */
	@Override
	public String getPassword() {
		return password;
	}

	/*
	 * Temporal Complexity: best case : O(1) worst case : O(1) Medium case : O(1)
	 */
	@Override
	public int getVisits() {
		return visits;
	}

	/*
	 * Temporal Complexity: best case : O(1) worst case : O(1) Medium case : O(1)
	 */
	@Override
	public void addVisit() {
		visits++;
	}

	/*
	 * Temporal Complexity: best case : O(1) worst case : O(log(R)) Medium case :
	 * O(log(L))
	 */
	@Override
	public boolean hasSomething(Date date) {
		if (rides.find(date) != null)
			return true;
		if (lifts.find(date) != null) {
			return true;
		}
		return false;
	}

	/*
	 * Temporal Complexity: best case : O(1) worst case : O(log(L)) Medium case :
	 * O(log(L))
	 */
	@Override
	public void registerRide(Ride lift) {
		lifts.insert(lift.getDate(), lift);
	}

	/*
	 * Temporal Complexity: best case : O(1) worst case : O(log(R)) Medium case :
	 * O(log(R))
	 */
	@Override
	public void createRide(Ride ride) {
		rides.insert(ride.getDate(), ride);
	}

	/*
	 * Temporal Complexity: best case : O(1) worst case : O(log(R)) Medium case :
	 * O(log(R))
	 */
	@Override
	public Ride getRide(Date date) {
		return rides.find(date);
	}

	/*
	 * Temporal Complexity: best case : O(1) worst case : O(log(R)) Medium case :
	 * O(log(R))
	 */
	@Override
	public Ride removeCreatedRide(Date date) {
		return rides.remove(date);
	}

	/*
	 * Temporal Complexity: best case : O(1) worst case : O(log(R)) Medium case :
	 * O(log(L))
	 */
	@Override
	public Ride removeJoinedRide(Date date) {
		return lifts.remove(date);

	}

	/*
	 * Temporal Complexity: best case : O(1) worst case : O(1) Medium case : O(1)
	 */
	@Override
	public int getNumberOfRides() {
		return rides.size();
	}

	/*
	 * Temporal Complexity: best case : O(1) worst case : O(1) Medium case : O(1)
	 */
	@Override
	public int compareTo(Object o) {
		if (this == o)
			return 0;
		else {
			User cmp = (User) o;
			int value = email.compareTo(cmp.getEmail());
			if (value != 0) {
				return value;
			} else {
				value = name.compareTo(cmp.getName());
				if (value != 0) {
					return value;
				} else {
					value = password.compareTo(cmp.getPassword());
					if (value != 0) {
						return value;
					}
				}
			}
		}
		return 0;
	}

	/*
	 * Temporal Complexity: best case : O(1) worst case : O(Log(R)) average case :
	 * O(Log(R))
	 */
	@Override
	public Iterator<Ride> iterateCreatedRides() throws NoElementException {
		if (rides.isEmpty())
			throw new NoElementException();

		return rides.values();

	}

	/*
	 * Temporal Complexity: best case : O(1) worst case : O(Log(L)) average case :
	 * O(Log(L))
	 */
	@Override
	public Iterator<Ride> iterateJoinedRides() throws NoElementException {
		if (lifts.isEmpty())
			throw new NoElementException();

		return lifts.values();

	}

}