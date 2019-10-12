package CarpoolHandler;

public interface Date extends Comparable {
	
	String getDay();
	
	String getMonth();
	
	String getYear();
	
	String getFullDate();
	
	boolean isDateValid();
}
