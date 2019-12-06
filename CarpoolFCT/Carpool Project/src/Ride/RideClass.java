package Ride;

import java.io.Serializable;

import Date.Date;
import User.User;
import User.UserWrapper;
import dataStructures.Iterator;
import dataStructures.List;
import dataStructures.NoElementException;
import dataStructures.Queue;
import dataStructures.QueueInList;
import dataStructures.SinglyLinkedList;
	
/**
 * 
 * @author David Antunes, 55045
 * @author Carolina Duarte, 55645
 * 
 * 
 *
 */
public class RideClass implements Ride, Comparable<Ride>, Serializable {
	/**
	 * U - number of Users
	 * 
	 * R - number of Rides
	 * 
	 * RIU- number of Rides in a User (<R)
	 * 
	 * UIR - number of Users in a Ride(<U)
	 * 
	 * DIR - number of Rides in a Date
	 * 
	 * LIU - number of lifts in a User(lifts where the user is present)(<R)
	 * 
	 * D - number of Dates
	 * 
	 */
	private static final long serialVersionUID = 8079752577096162829L;
	private User user;
	private String origin;
	private String destiny;
	private Date date;
	private int hour;
	private int minutes;
	private int duration;
	private int seats;
	private Queue<User> onHold;
	private List<String> users;

	/*
	 * Temporal Complexity: best case : O(1) worst case : O(1) average case : O(1)
	 */
	public RideClass(User user, String origin, String destiny, Date date, int hour, int minutes, int duration,
			int seats) {
		this.user = user;
		this.origin = origin;
		this.destiny = destiny;
		this.date = date;
		this.hour = hour;
		this.minutes = minutes;
		this.duration = duration;
		this.seats = seats;
		onHold = new QueueInList<User>();
		users = new SinglyLinkedList<String>();
	}

	/*
	 * Temporal Complexity: best case : O(1) worst case : O(1) average case : O(1)
	 */
	@Override
	public UserWrapper getUser() {
		return (UserWrapper) user;
	}

	/*
	 * Temporal Complexity: best case : O(1) worst case : O(1) average case : O(1)
	 */
	@Override
	public String getOrigin() {
		return origin;
	}

	/*
	 * Temporal Complexity: best case : O(1) worst case : O(1) average case : O(1)
	 */
	@Override
	public String getDestination() {
		return destiny;
	}

	/*
	 * Temporal Complexity: best case : O(1) worst case : O(1) average case : O(1)
	 */
	@Override
	public Date getDate() {
		return date;
	}

	/*
	 * Temporal Complexity: best case : O(1) worst case : O(1) average case : O(1)
	 */
	@Override
	public int getHour() {
		return hour;
	}

	/*
	 * Temporal Complexity: best case : O(1) worst case : O(1) average case : O(1)
	 */
	@Override
	public int getMinutes() {
		return minutes;
	}

	/*
	 * Temporal Complexity: best case : O(1) worst case : O(1) average case : O(1)
	 */
	@Override
	public int getSeats() {
		return seats;
	}

	/*
	 * Temporal Complexity: best case : O(1) worst case : O(1) average case : O(1)
	 */
	@Override
	public int getDuration() {
		return duration;
	}

	/*
	 * Temporal Complexity: best case : O(1) worst case : O(1) average case : O(1)
	 */
	@Override
	public int getUsersInQueue() {
		return onHold.size();
	}

	/*
	 * Temporal Complexity: best case : O(1) worst case : O(1) average case : O(1)
	 */
	public int getRemainingSeats() {
		return seats - users.size();
	}

	/*
	 * Temporal Complexity: best case : O(1) worst case : O(1) average case : O(1)
	 */
	@Override
	public int addUser(User user) {

		if (users.size() < seats) {
			users.addLast(user.getEmail());
			return 0;
		} else {
			onHold.enqueue(user);
			return onHold.size();
		}
	}

	/*
	 * Temporal Complexity: best case : O(1) worst case : O(2U) average case : O(U)
	 */
	@Override
	public void removeUser(String email) {
		Iterator<String> it = iterateUsers();
		int i = 0;

		boolean found = false;
		while (it.hasNext() && !found) {
			String aux = it.next();
			if (aux.equals(email)) {
				found = true;

			} else
				i++;
		}

		users.remove(i);

		found = false;
		while (!onHold.isEmpty() && !found) {
			User userInLine = onHold.dequeue();
			if (!userInLine.hasSomething(date)) {
				addUser(userInLine);
				found = true;
				userInLine.registerRide(this);
			}
		}

	}

	/*
	 * Temporal Complexity: best case : O(1) worst case : O(1) average case : O(1)
	 */
	@Override
	public Iterator<String> iterateUsers() throws NoElementException {
		if (users.isEmpty())
			throw new NoElementException();
		return users.iterator();
	}

	/*
	 * Temporal Complexity: best case : O(1) worst case : O(1) average case : O(1)
	 */
	public boolean hasUsers() {
		return !users.isEmpty();
	}

	/*
	 * Temporal Complexity: best case : O(1) worst case : O(1) average case : O(1)
	 */
	@Override
	public int compareTo(Ride o) {
		if (this == o) {
			return 0;
		} else {
			int value = ((Comparable<Date>) date).compareTo(o.getDate());
			if (value != 0) {
				return value;
			} else {
				value = user.getEmail().compareTo(o.getUser().getEmail());
				if (value != 0)
					return value;
			}
		}
		return 0;
	}

}