package com.example.TpFinalJava.utilidades;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Datetime {
	
	  public static Date getDate() {
	        return Calendar.getInstance().getTime();
	    }

	public Date fromString(String string) throws ParseException {
		
		
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS", Locale.ENGLISH);
		Date date = format.parse(string);

		return date;
		
	}
}
