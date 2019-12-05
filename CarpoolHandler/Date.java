package CarpoolHandler;

public interface Date extends Comparable<Date>{
	
	/**
	 * Returns the day of the date
	 * @return day
	 */
	String getDay();
	
	/**
	 * Returns the month of the date
	 * @return month
	 */
	String getMonth();
	
	/**
	 * Returns the year of the date
	 * @return year
	 */
	String getYear();
	
	/**
	 * Returns the full date (day+month+year) of the date
	 * @return full date
	 */
	String getFullDate();
	
	/**
	 * Checks if the date is valid
	 * @param date date
	 * @return <code>true</code> if is or <code>false</code> if is invalid
	 */
	boolean isDateValid(String date);
}
