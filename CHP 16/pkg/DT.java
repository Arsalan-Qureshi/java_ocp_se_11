package pkg;

import java.time.*;
import java.time.format.*;

class DT {
	public static void main(String... args) {
		print(LocalDate.now()); //2023-04-20
		print(LocalTime.now()); //22:55:13.011310
		print(LocalDateTime.now()); //2023-04-20T22:55:13.011310
		print(ZonedDateTime.now()); //2023-04-20T22:55:13.012308700+05:00[Asia/Karachi]
		print(LocalDate.of(2020, Month.OCTOBER, 20)); //2020-10-20
		print(LocalDate.of(2020, 10, 20)); //2020-10-20
		print(LocalDateTime.of(2020, 10, 20, 6, 15, 30)); //2020-10-20T06:15:30
		
		LocalDate date = LocalDate.of(2020, 10, 20);
		LocalTime time = LocalTime.of(5, 23, 17);
		LocalDateTime dt = LocalDateTime.of(date, time);
		
		print(date.getDayOfWeek()); //TUESDAY
		print(date.getMonth()); //OCTOBER
		print(date.getYear()); //2020
		print(date.getDayOfYear()); //294
		
		print(date.format(DateTimeFormatter.ISO_LOCAL_DATE)); //2020-10-20
		print(time.format(DateTimeFormatter.ISO_LOCAL_TIME)); //05:23:17
		print(dt.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)); //2020-10-20T05:23:17
		
		/* MMMM = October, MMM = Jul, MM = 07, M = 7, a = am/pm, z = Time Zone Name, Z = Time Zone Offset, u/y/Y = Year*/
		var dtf = DateTimeFormatter.ofPattern("MMMM dd, yyyy 'at' hh:mm a z Z");
		print(ZonedDateTime.now().format(dtf)); // April 21, 2023 at 05:33 AM PKT +0500
	}
	
	public static void print(Object o){
		System.out.println(o);
	}
}