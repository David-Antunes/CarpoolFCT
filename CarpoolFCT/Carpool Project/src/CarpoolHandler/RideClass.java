package CarpoolHandler;

import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;

public class RideClass implements Ride, Comparable<Object>, Serializable {

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
	private LinkedList<User> onHold;
	private LinkedList<User> users;

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
		onHold = new LinkedList<User>();
		users = new LinkedList<User>();
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
			onHold.addLast(user);
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
		if (!found) {
			it = onHold.iterator();
			i = 0;
			while (it.hasNext()) {
				User aux = it.next();
				if (aux.getName().equals(name)) {
					found = true;
					user = aux;
				} else
					i++;
			}
		}
		users.remove(i);

		if (!onHold.isEmpty()) {
			User userInLine = onHold.getFirst();
			addUser(userInLine);
			onHold.removeFirst();
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
	public int compareTo(Object o) {
		if (this == o) {
			return 0;
		} else {
			Ride cmp = (Ride) o;
			@SuppressWarnings("unchecked")
			int value = ((Comparable<Date>) date).compareTo(cmp.getDate());
			if (value != 0) {
				return value;
			} else {
				value = user.getEmail().compareTo(cmp.getUser().getEmail());
				if (value != 0)
					return value;
			}
		}
		return 0;
	}
}