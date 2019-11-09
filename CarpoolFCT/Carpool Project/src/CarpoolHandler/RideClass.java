package CarpoolHandler;

import java.io.Serializable;

import dataStructures.Iterator;
import dataStructures.List;
import dataStructures.Queue;

<<<<<<< HEAD
public class RideClass implements Ride, Comparable, Serializable{
=======
public class RideClass implements Ride, Comparable<Object>, Serializable {
>>>>>>> branch 'master' of https://github.com/David-Antunes/CarpoolFCT.git

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

	@Override
	public String getOrigin() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getDestination() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Date getDate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getHour() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getMinutes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getNumberOfSeats() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public User getUsersInQueue() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addUser() {
		// TODO Auto-generated method stub

	}

	@Override
	public User removeUser() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator<User> iterateUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}

}
