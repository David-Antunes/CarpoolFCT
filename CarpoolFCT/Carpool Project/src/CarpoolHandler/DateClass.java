package CarpoolHandler;

import java.io.Serializable;

public class DateClass implements Date, Comparable<Object>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8835144932380068567L;
	private String day;
	private String month;
	private String year;

	public DateClass(String date) {
		String[] split = date.split("-");
		day = split[0];
		month = split[1];
		year = split[2];
	}

	@Override
	public String getDay() {
		return day;
	}

	@Override
	public String getMonth() {
		return month;
	}

	@Override
	public String getYear() {
		return year;
	}

	@Override
	public String getFullDate() {
		return day + "-" + month + "-" + year;
	}

	@Override
	public boolean isDateValid() {
		return false;
	}

	@Override
	public int compareTo(Object o) {

		Date aux = (Date) o;
		return getFullDate().compareTo(aux.getFullDate());
	}

}
