  
package CarpoolHandler;

import java.io.Serializable;

public class DateClass implements Date, Comparable<Object>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8835144932380068567L;
	private int day;
	private int month;
	private int year;

	public DateClass(String date) {
		String[] split = date.split("-");
		day = Integer.parseInt(split[0]);
		month = Integer.parseInt(split[1]);
		year = Integer.parseInt(split[2]);
	}

	@Override
	public int getDay() {
		return day;
	}

	@Override
	public int getMonth() {
		return month;
	}

	@Override
	public int getYear() {
		return year;
	}

	@Override
	public String getFullDate() {
		return day + "-" + month + "-" + year;
	}

	@Override
	public boolean isDateValid(String date) {
		String[] split = date.split("-");
		int auxDay = Integer.parseInt(split[0]);
		int auxMonth = Integer.parseInt(split[1]);
		int auxYear = Integer.parseInt(split[2]);
		if (auxDay <= 0 || auxDay > 31)
			return false;
		else if (auxMonth <= 0 || auxMonth > 12)
			return false;
		else if (auxYear <= 0)
			return false;
		return true;
	}

	@Override
	public int compareTo(Object o) {
		/**
		Date aux = (Date) o;
		return getFullDate().compareTo(aux.getFullDate());
		*/
		Date aux = (Date) o;
		if(this.getMonth() < aux.getMonth()) 
			return -1;
		if(this.getMonth() > aux.getMonth())
			return 1;
		if(this.getDay() < aux.getDay())
			return -1;
		else
			return 1;
	}

}
