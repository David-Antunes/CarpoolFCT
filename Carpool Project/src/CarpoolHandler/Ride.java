package CarpoolHandler;

import dataStructures.Iterator;

public interface Ride {
	
	String getOrigin();
	
	String getDestination();
	
	Date getDate();
	
	String getHour();
	
	String getMinutes();
	
	int getNumberOfSeats();
	
	User getUsersInQueue();
	
	void addUser();
	
	User removeUser();
	
	Iterator<User> iterateUsers();
}
