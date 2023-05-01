package pkg;

import java.util.*;
import java.text.*;
import java.time.*;
import java.time.format.*;
import java.io.*;

class Localization {
	public static void main(String... args) {
		/*
		try {
			localizingNumbers();
		} catch(ParseException e) {
			e.printStackTrace();
		} */
		
		// localizingDates();
		
		// localeCategory();
		
		// resourceBundles();
		
		// formattingMessages();
		
		properties();
	}
	
	public static void locale(){
		print(Locale.getDefault()); //en_US
		print(Locale.GERMAN); //de
		print(Locale.GERMANY); //de_DE
		print(new Locale("tr", "TR")); //tr_TR
		
		Locale l1 = new Locale.Builder()
			.setLanguage("tr")
			.setRegion("TR")
			.build();
			
		print(l1);
		Locale.setDefault(l1);
		print(Locale.getDefault()); //tr_TR
	}
	
	/* Formatting data is to convert it from a structured object or primitive value into a String. 
	   Parsing data is to convert it from a String to a structured object or primitive value. 
	*/
	
	public static void localizingNumbers() throws ParseException {
		int attendance = 3_200_000;
		var us = NumberFormat.getInstance(Locale.US);
		print(us.format(attendance)); //3,200,000
		var ger = NumberFormat.getInstance(Locale.GERMANY);
		print(ger.format(attendance)); //3.200.000
		
		var usCurrency = NumberFormat.getCurrencyInstance(Locale.US);
		print(usCurrency.format(48)); //$48.00
		/* throws checked ParseException */
		print(usCurrency.parse("$48")); //48 Number object is returned.
		
		/* Customer Number Formatter 
		   # = Omit the position if no digit exists.
		   0 = Put 0 if o digit exists.
		*/	
		NumberFormat f1 = new DecimalFormat("###,###,###.0");
		print(f1.format(1234567.890)); //1,234,567.9 Number after deciamal point is rounded off.
	}
	
	public static void localizingDates() {
		print(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT).format(LocalDate.now())); // 4/21/23
		print(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM).format(LocalDate.now())); // Apr 21, 2023
		print(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG).format(LocalDate.now())); // April 21, 2023
		print(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL).format(LocalDate.now())); // Friday, April 21, 2023
		
		
		print(DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT).format(LocalTime.now())); //3:48 PM
		print(DateTimeFormatter.ofLocalizedTime(FormatStyle.MEDIUM).format(LocalTime.now())); //3:48:42 PM
		/* Error is thrown if ZonedDateTime is not used. */
		print(DateTimeFormatter.ofLocalizedTime(FormatStyle.LONG).format(ZonedDateTime.now())); //3:51:20 PM PKT
		print(DateTimeFormatter.ofLocalizedTime(FormatStyle.FULL).format(ZonedDateTime.now())); //3:51:20 PM Pakistan Standard Time
		
		print(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT).format(LocalDateTime.now())); //4/21/23, 4:12 PM
		print(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT, FormatStyle.SHORT).format(ZonedDateTime.now())); //4/21/23, 4:12 PM
		print(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM).format(LocalDateTime.now())); //Apr 21, 2023, 4:16:31 PM
		print(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG).format(ZonedDateTime.now())); //April 21, 2023 at 4:17:42 PM PKT
		print(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL).format(ZonedDateTime.now())); //Friday, April 21, 2023 at 4:17:42 PM Pakistan Standard Time
	}
	
	public static void localeCategory(){
		Locale.setDefault(new Locale("en", "US"));
		var locale = Locale.getDefault();
		var spain = new Locale("es", "ES");
		
		print(NumberFormat.getCurrencyInstance().format(48) + "," + locale.getDisplayLanguage()); //$48.00,English
		
		Locale.setDefault(Locale.Category.DISPLAY, spain);
		print(NumberFormat.getCurrencyInstance().format(48) + "," + locale.getDisplayLanguage()); //$48.00,inglés
		
		Locale.setDefault(Locale.Category.FORMAT, spain);
		print(NumberFormat.getCurrencyInstance().format(48) + "," + locale.getDisplayLanguage()); //48,00 ?,inglés
	}
	
	public static void resourceBundles() {
		var rb = ResourceBundle.getBundle("Zoo", new Locale("en", "US"));
	}
	
	// MessageFormat does not need to be imported from somewhere.
	public static void formattingMessages(){
		print(MessageFormat.format("Hello {0} {1}", "Arsalan", "Qureshi")); //Hello Arsalan Qureshi
	}
	
	public static void properties(){
		var props = new Properties();
		props.setProperty("name", "Vegeta");
		props.setProperty("designation", "Prince");
		
		print(props.getProperty("name"));
		print(props.getProperty("designation"));
		print(props.getProperty("final_form", "Super Saiyajin Blue")); //Simply get() will not compile for a default value.
		print(props.getProperty("kill_count")); //returns null
	}
	
	public static void print(Object o){
		System.out.println(o);
	}
}