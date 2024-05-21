package practice.Basics;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class GetCurrentDate {

	public static void main(String[] args) {
		
		Date date = new Date();
		System.out.println(date);
		
		SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd");
		String currentDate = simpleDate.format(date);
		System.out.println(currentDate);
		
		Calendar cal = simpleDate.getCalendar();
		cal.add(Calendar.DAY_OF_MONTH, 30);
		String dateReq = simpleDate.format(cal.getTime());
		System.out.println(dateReq);
	}
}
