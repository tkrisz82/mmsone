package tkrisz82.rentacar.services;

import java.sql.Date;
import java.util.concurrent.TimeUnit;

public class DateHandler {

	public long getDateDiffPlusOne(Date date1, Date date2) {
	    long diffInMillies = date2.getTime() - date1.getTime();
	    return Math.abs(TimeUnit.DAYS.convert(diffInMillies,TimeUnit.MILLISECONDS)) + 1;
	}
	
	
	
	
}
