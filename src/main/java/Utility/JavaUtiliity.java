package Utility;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

/**
 * @author AVINASH D
 * This is a reusable class where we fetch random integer and
 * Today's date and after Some period Of Date
 */
public class JavaUtiliity {

	/**
	 * This is a reusable method to fetch the random integer
	 * @return 
	 */
	public int randomint() {
	
		Random r=new Random();
		int num = r.nextInt(1000);
		return num;
	}
	
	/**
	 * This method is used fetch the current date
	 * @return
	 */
	public String fetchcurrentdate() {
		Date dobj=new Date();
		SimpleDateFormat sim= new SimpleDateFormat("yyyy-MM-dd");
		String currentdate = sim.format(dobj);
		return currentdate;
		
	}
	/**
	 * This method is used fetch the date from some(given) date
	 * @return
	 */
	public String fetchdateaftersomedays() {
		Date dobj=new Date();
		SimpleDateFormat sim= new SimpleDateFormat("yyyy-MM-dd");
		String currentdate = sim.format(dobj);
		Calendar cal = sim.getCalendar();
		cal.add(Calendar.DAY_OF_MONTH, 30);
		String date = sim.format(cal.getTime());
		return date;
	}
	
}
