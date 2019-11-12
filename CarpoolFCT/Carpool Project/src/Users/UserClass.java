
package Users;

import java.io.Serializable;

import Date.Date;
import Rides.Ride;
import dataStructures.Iterator;
import dataStructures.NoElementException;
import dataStructures.SortedMap;
import dataStructures.SortedMapWithJavaClass;

/**
 * 
 * @author David Antunes, 55045
 * @author Carolina Duarte, 55645
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

	public UserClass(String email, String name, String password) {
		this.email = email;
		this.name = name;
		this.password = password;
		rides = new SortedMapWithJavaClass<Date, Ride>();
		lifts = new SortedMapWithJavaClass<Date, Ride>();
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
		if (rides.find(date) != null)
			return true;
		if (lifts.find(date) != null) {
			return true;
		}
		return false;
	}

	@Override
	public boolean hasRide(Date date) {
		return rides.find(date) != null;
	}

	public boolean hasLift(Date date) {
		return lifts.find(date) != null;
	}

	@Override
	public void registerRide(Ride lift) {
		lifts.insert(lift.getDate(), lift);
	}

	@Override
	public void createRide(Ride ride) {
		rides.insert(ride.getDate(), ride);
	}

	@Override
	public Ride getRide(Date date) {
		return rides.find(date);
	}

	public boolean rideHasLift(Date date) {
		Ride ride = rides.find(date);
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

	@Override
	public Iterator<Ride> iterateCreatedRides() throws NoElementException {
		if (rides.isEmpty())
			throw new NoElementException();

		return rides.values();

	}

	@Override
	public Iterator<Ride> iterateJoinedRides() throws NoElementException {
		if (lifts.isEmpty())
			throw new NoElementException();

		return lifts.values();

	}

}