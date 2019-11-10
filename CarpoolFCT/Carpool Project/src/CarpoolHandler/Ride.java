package CarpoolHandler;

import java.util.Iterator;

public interface Ride {

	User getUser();

	String getOrigin();

	String getDestination();

	Date getDate();

	int getHour();

	int getMinutes();

	int getSeats();

	int getDuration();

	int getUsersInQueue();

	int addUser(User user);

	User removeUser(String user);

	Iterator<User> iterateUsers();
	
	int getRemainingSeats();
	
	public boolean hasUsers();
}