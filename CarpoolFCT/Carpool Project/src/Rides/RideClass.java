package Rides;

import java.io.Serializable;

import Date.Date;
import Users.User;
import dataStructures.Iterator;
import dataStructures.List;
import dataStructures.Queue;
import dataStructures.QueueInList;
import dataStructures.SinglyLinkedList;

/**
 * 
 * @author David Antunes, 55045
 * @author Carolina Duarte, 55645
 *
 */
public class RideClass implements Ride, Comparable<Ride>, Serializable {

	/**
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
	private List<User> users;

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
		users = new SinglyLinkedList<User>();
	}

	@Override
	public User getUser() {
		return user;
	}

	@Override
	public String getOrigin() {
		return origin;
	}

	@Override
	public String getDestination() {
		return destiny;
	}

	@Override
	public Date getDate() {
		return date;
	}

	@Override
	public int getHour() {
		return hour;
	}

	@Override
	public int getMinutes() {
		return minutes;
	}

	@Override
	public int getSeats() {
		return seats;
	}

	@Override
	public int getDuration() {
		return duration;
	}

	@Override
	public int getUsersInQueue() {
		return onHold.size();
	}

	public int getRemainingSeats() {
		return seats - users.size();
	}

	@Override
	public int addUser(User user) {
		if (users.size() < seats) {
			users.addLast(user);
			return 0;
		} else {
			onHold.enqueue(user);
			return onHold.size();
		}
	}

	@Override
	public User removeUser(String name) {
		Iterator<User> it = iterateUsers();
		int i = 0;
		User user = null;
		boolean found = false;
		while (it.hasNext() && !found) {
			User aux = it.next();
			if (aux.getName().equals(name)) {
				found = true;
				user = aux;
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

		return user;

	}

	@Override
	public Iterator<User> iterateUsers() {
		return users.iterator();
	}

	public boolean hasUsers() {
		return !users.isEmpty();
	}

	@Override
	public int compareTo(Ride o) {
		if (this == o) {
			return 0;
		} else {
			@SuppressWarnings("unchecked")
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