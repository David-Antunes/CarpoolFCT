package CarpoolHandler;

import java.util.Iterator;
import dataStructures.NoElementException;
public interface CarpoolHandler {
	
	/**
	Iterator<Ride> iterateUserCreatedRides() throws NoElementException;
	
	Iterator<Ride> iterateUserJoinedRides() throws NoElementException;

	Iterator<Ride> iterateRidesThroEmails() throws NoElementException;
	
	Iterator<Ride> iterateRidesThroDays() throws NoElementException;
	

	Iterator<Ride> iterateAll() throws NoElementException;
	***/
	public void register(String email, String name, String password) ;
	
	void login(String email);
	
	void hasUser(String email) throws AlreadyExistsElementException;
	
	void userExists(String email) throws NonExistingElementException;
	
	Ride remove();
	
	void Ride();
	
	void removeFromRide();
	
	void check();
	
	boolean hasCurUsar();
	
	int nUsers();
	
	public String userEmail() throws NonExistingElementException;
	
	public boolean validPassaword(String password, int i) throws InvalidPasswordException;
	
	public boolean isPassCorrect(String email, String password, int i) throws InvalidPasswordException;

	boolean hasUser();
	
	int nVisitas();

	}
