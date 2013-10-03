package awesome.calendar.project;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.springframework.stereotype.Component;

@Component
public class CurrentDate {

	Calendar calendar = Calendar.getInstance();

	public int getMonth() {
		// plus one because January is Month 0
		return calendar.get(Calendar.MONTH) + 1;
	}

	public int getDate() {
//		return calendar.get(Calendar.DATE);
		return 3;
	}

	public int getYear() {
		return calendar.get(Calendar.YEAR);
	}

	public int getMonth(String date) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			calendar.setTime(sdf.parse(date));
		} catch (Throwable t) {
			t.printStackTrace();
		}
		return calendar.get(Calendar.MONTH) + 1;
	}

	public int getDate(String date) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			calendar.setTime(sdf.parse(date));
		} catch (Throwable t) {
			t.printStackTrace();
		}
		return calendar.get(Calendar.DATE);
	}

	public int getYear(String date) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			calendar.setTime(sdf.parse(date));
		} catch (Throwable t) {
			t.printStackTrace();
		}
		return calendar.get(Calendar.YEAR);
	}

	public String getFirstDayOfMonth() {
		try {
			calendar.set(Calendar.DAY_OF_MONTH, Calendar.getInstance().getActualMinimum(Calendar.DAY_OF_MONTH));
			String date = calendar.getTime().toString();
			String firstDay = "";
			if (date.substring(0, 3).toLowerCase().trim().equals("thu")) {
				firstDay = "thur";
			} else {
				firstDay = date.substring(0, 3).toLowerCase().trim();
			}
			return firstDay;
		} catch (Throwable t) {
			t.printStackTrace();
		}
		return null;
	}

	public int getDayOfWeek() {
		return calendar.get(Calendar.DAY_OF_WEEK);
	}

	public String getStringDayOfWeek() {
		switch (getDayOfWeek()) {
		case 1:
			return "mon";
		case 2:
			return "tue";
		case 3:
			return "wed";
		case 4:
			return "thur";
		case 5:
			return "fri";
		case 6:
			return "sat";
		case 7:
			return "sun";
		}
		return null;
	}

	public int getLastDayOfMonth(int month) {
		switch (month) {
		case 1:
			return 31;
		case 2:
			return 30;
		case 3:
			return 31;
		case 4:
			return 30;
		case 5:
			return 31;
		case 6:
			return 30;
		case 7:
			return 31;
		case 8:
			return 31;
		case 9:
			return 30;
		case 10:
			return 31;
		case 11:
			return 30;
		case 12:
			return 31;
		}
		return 0;
	}

	public int getIntFirstDayOfMonth(String day) {
		if (day.equals("sun")) {
			return 0;
		} else if (day.equals("mon")) {
			return 1;
		} else if (day.equals("tue")) {
			return 2;
		} else if (day.equals("wed")) {
			return 3;
		} else if (day.equals("thur")) {
			return 4;
		} else if (day.equals("fri")) {
			return 5;
		} else if (day.equals("sat")) {
			return 6;
		} else {
			return 0;
		}
	}

	public String getStringMonth(int month) {
		switch (month) {
		case 1:
			return "january";
		case 2:
			return "february";
		case 3:
			return "march";
		case 4:
			return "april";
		case 5:
			return "may";
		case 6:
			return "june";
		case 7:
			return "july";
		case 8:
			return "august";
		case 9:
			return "september";
		case 10:
			return "october";
		case 11:
			return "november";
		case 12:
			return "december";
		}
		return null;
	}

}
