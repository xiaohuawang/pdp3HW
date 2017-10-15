package test;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class Test {

	
	public Date getLocalTime() {
		Calendar cal = Calendar.getInstance();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String currentTime = df.format(cal.getInstance().getTime());
		long localTimeLong = Timestamp.valueOf(currentTime).getTime();
		Date localTime = new Date(localTimeLong);
		// System.out.println("local time= " + localTime);

		return localTime;
	}

	public static void main(String[] args) {
		Test test=new Test();
		// TODO Auto-generated method stub
//		HashMap<String, String> newMap = new HashMap<String, String>();
//		newMap.put("aa", "bb");
//		System.out.println(newMap);
		// Random r = new Random();
		
//		String str="My name is peter";
//		System.out.println(str);
//		
//		String replaceStr=str.replaceAll("e", "a");
//		System.out.println("replaceStr="+ replaceStr);
		String res=test.getLocalTime().toString();
		
		System.out.println(res);

	}

}
