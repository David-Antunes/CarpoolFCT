package CarpoolHandler;

import java.io.Serializable;

<<<<<<< HEAD
public class DateClass implements Date, Comparable, Serializable {
=======
public class DateClass implements Date, Comparable<Object>, Serializable {
>>>>>>> branch 'master' of https://github.com/David-Antunes/CarpoolFCT.git

	/**
	 * 
	 */
	private static final long serialVersionUID = -8835144932380068567L;
	private String day;
	private String month;
	private String year;

	@Override
	public String getDay() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getMonth() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getYear() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getFullDate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isDateValid() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}

}
