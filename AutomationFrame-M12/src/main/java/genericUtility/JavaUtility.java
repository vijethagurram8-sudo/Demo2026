package genericUtility;

import java.util.Date;
import java.util.Random;
/**
 * this class consist of methos reated to java
 */

public class JavaUtility {
	/**
	 * this method is generate random integer number btw the range
	 * 1to 1000
	 * @return
	 */
	public int toGenerateRandomValue() {
	 Random r = new Random();
	 int randomNumber = r.nextInt(1000);
	 return randomNumber;
	}
/**
 * 
 * @return
 */
public String toGetSytemDateAndTime() {
Date d=new Date();
//sat jan 17 08:11:23 ist 2026
System.out.println(d);
String date[]=d.toString().split(" ");
String day=date[0];
String month=date[1];
String date1=date[2];
String time=date[3].replace(":", " ");
String year=date[5];
String finalDate=day+" "+month+" "+date1+" "+time+" "+year;

return finalDate;
}

}
