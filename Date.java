package CarpoolHandler;

public interface Date{
	
	int getDay();
	
	int getMonth();
	
	int getYear();
	
	String getFullDate();
	
	boolean isDateValid(String date);
}
