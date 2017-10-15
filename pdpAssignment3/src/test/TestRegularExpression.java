package test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestRegularExpression {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String filename = (System.getProperty("user.dir") + File.separatorChar 
				+ "src" + File.separatorChar + "Flight363FromSeattleToBoston.csv");
		BufferedReader reader = new BufferedReader(new FileReader(filename));
		String line = reader.readLine();
		line = reader.readLine();
		System.out.println(line);
//		String line="aa[bb]";
		
//		Pattern p = Pattern.compile("\"[^\"]*\"");
//		Pattern p = Pattern.compile("(?<=\")(.+?)(?=\")");   
		
		
//		Pattern p = Pattern.compile("(.*?)");
//		Pattern p = Pattern.compile("\\[(.*?)\\]");
//		Pattern p = Pattern.compile("\\[\\[(.*?)\\]\\]");

//		Pattern p = Pattern.compile((["'])(?:(?=(\\?))\2.)*?\1);
		Pattern p = Pattern.compile("\"(.+?)\"");    
		Matcher m = p.matcher(line);
		
		 ArrayList<String> strs = new ArrayList<String>();
		    while (m.find()) {
		      strs.add(m.group(1));      
		    } 
		    for (String s : strs){
		      System.out.println(s);
		    }    
		  }
	
}
