package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReadEmailTemplate {

	public HashMap<Integer, String> templateMap;


	public void getFileName() {
		
	}

	public HashMap<Integer, String> readFile(String filename) throws Exception {

//		System.out.println("come");
		BufferedReader reader = new BufferedReader(new FileReader(filename));
		templateMap = new HashMap<Integer, String>();
		String line = reader.readLine();
		int count = 0;

		while (line != null) {

			// get the things inside [[ ]]
			Pattern p = Pattern.compile("\\[\\[(.*?)\\]\\]");
			Matcher m = p.matcher(line);
			
			while (m.find()) {
//				System.out.println(m.group(1));
				templateMap.put(++count, m.group(1));
			}
			// System.out.println(line);
			line = reader.readLine();
		}

		System.out.println("map= "+templateMap);
		System.out.println();
		return templateMap;
	}

//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		ReadEmailTemplate test = new ReadEmailTemplate();
//
//		// String
//		// res="/Users/yangyangyy/Documents/workspace/Assignment3/src/Flight3FromVancouverToSeattle.csv";
//		// System.out.println(res);
//		System.out.println(test.filename);
//
//		try {
//			test.readFile(test.filename);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//	}
}
