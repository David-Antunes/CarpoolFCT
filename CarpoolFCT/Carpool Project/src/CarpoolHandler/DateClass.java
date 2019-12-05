
package CarpoolHandler;

import java.io.Serializable;

import dataStructures.NoSuchElementException;

public class DateClass implements Date, Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8835144932380068567L;
	private String day;
	private String month;
	private String year;

	/*Temporal Complexity:
	 * best case : O(1)
	 * worst case : O(1)
	 * Medium case : O(1)
	 */
	public DateClass(String date) {
		String[] split = date.split("-");
		day = split[0];
		month = split[1];
		year = split[2];
	}

	/*Temporal Complexity:
	 * best case : O(1)
	 * worst case : O(1)
	 * Medium case : O(1)
	 */
	@Override
	public String getDay() {
		return day;
	}

	/*Temporal Complexity:
	 * best case : O(1)
	 * worst case : O(1)
	 * Medium case : O(1)
	 */
	@Override
	public String getMonth() {
		return month;
	}

	/*Temporal Complexity:
	 * best case : O(1)
	 * worst case : O(1)
	 * Medium case : O(1)
	 */
	@Override
	public String getYear() {
		return year;
	}

	/*Temporal Complexity:
	 * best case : O(1)
	 * worst case : O(1)
	 * Medium case : O(1)
	 */
	@Override
	public String getFullDate() {
		return day + "-" + month + "-" + year;
	}

	/*Temporal Complexity:
	 * best case : O(1)
	 * worst case : O(1)
	 * Medium case : O(1)
	 */
	@Override
	public boolean isDateValid(String date) {
		String[] split = date.split("-");
		int auxDay = Integer.parseInt(split[0]);
		int auxMonth = Integer.parseInt(split[1]);
		int auxYear = Integer.parseInt(split[2]);
		boolean res = false;
		if (auxMonth > 0 && auxMonth <= 12) {
			switch (auxMonth) {
			case 1:
			case 3:
			case 5:
			case 7:
			case 8:
			case 10:
			case 12:
				if (auxDay > 0 && auxDay <= 31)
					res = true;
				break;
			case 4:
			case 6:
			case 9:
			case 11:
				if (auxDay > 0 && auxDay <= 30)
					res = true;
				break;
			case 2:
				if (auxYear % 4 == 0 && (auxYear % 100 != 0 || auxYear % 400 == 0)) {
					if (auxDay > 0 && auxDay <= 29)
						res = true;
				} else if (auxDay > 0 && auxDay <= 28)
					res = true;
				break;
			}
		}
		return res;
	}

	/*Temporal Complexity:
	 * best case : O(1)
	 * worst case : O(1)
	 * Medium case : O(1)
	 */
	@Override
	public int compareTo(Date o) {

		String date = o.getFullDate();
		String[] split = date.split("-");
		int auxDay = Integer.parseInt(split[0]);
		int auxMonth = Integer.parseInt(split[1]);
		int auxYear = Integer.parseInt(split[2]);

		int thisDay = Integer.parseInt(day);
		int thisMonth = Integer.parseInt(month);
		int thisYear = Integer.parseInt(year);

		int value = thisYear - auxYear;
		if (value != 0)
			return value;
		else if ((value = thisMonth - auxMonth) != 0)
			return value;
		else if ((value = thisDay - auxDay) != 0)
			return value;
		return 0;
	}

}
