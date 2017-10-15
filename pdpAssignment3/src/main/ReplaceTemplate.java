package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReplaceTemplate {

	public String filename;
	// List<Customer> customerList;
	Customer customer;
	ReadEmailTemplate readEmailTemplate = new ReadEmailTemplate();
	private ReadCSVTest readCSVTest;
	public HashMap<Integer, String> templateMap;
	public HashMap<String, String> newTemplateMap;
	public List<HashMap<String, String>> newMapList;

	public String getTemplateFileName(String filename) {

		 filename = (System.getProperty("user.dir") + File.separatorChar +filename);
//		filename = (System.getProperty("user.dir") + File.separatorChar + "src" + File.separatorChar + filename);
		return filename;
	}

	public void modifyMap(List<Customer> customerList, String templateFileName) throws Exception {

		System.out.println("modify----------");
		// readEmailTemplate.getFileName();
		readCSVTest = new ReadCSVTest();

		newMapList = new ArrayList<HashMap<String, String>>();
		filename = this.getTemplateFileName(templateFileName);

		System.out.println("filename= "+filename);
		templateMap = readEmailTemplate.readFile(filename);

		// get a new hashmap of template
		for (int customerIndex = 0; customerIndex < customerList.size(); customerIndex++) {
			newTemplateMap = new HashMap<String, String>();
			for (int templateIndex = 1; templateIndex <= templateMap.size(); templateIndex++) {
				System.out.println(readEmailTemplate.templateMap.get(templateIndex));
				// System.out.println("result=
				// "+getCustomerValue(readEmailTemplate.templateMap.get(i),customer));
				newTemplateMap.put(readEmailTemplate.templateMap.get(templateIndex), getCustomerValue(
						readEmailTemplate.templateMap.get(templateIndex), customerList.get(customerIndex)));
			}
			// System.out.println();
			// System.out.println("new map =" + newTemplateMap);
			newMapList.add(newTemplateMap);
		}

		// call the update template and write the txt
		for (int cutomerIndex = 0; cutomerIndex < newMapList.size(); cutomerIndex++) {
			System.out.println(newMapList.get(cutomerIndex));
			updateEmailTemplate(cutomerIndex);
		}

		// return newTemplateMap;
	}

	public List<String> modifyMyself(Customer me, String templateFileName) throws Exception {
		System.out.println("modify---------------Myself----------");
		filename = this.getTemplateFileName(templateFileName);
		templateMap = readEmailTemplate.readFile(filename);
		newTemplateMap = new HashMap<String, String>();
		for (int templateIndex = 1; templateIndex <= templateMap.size(); templateIndex++) {
			System.out.println(readEmailTemplate.templateMap.get(templateIndex));

			newTemplateMap.put(readEmailTemplate.templateMap.get(templateIndex),
					getCustomerValue(readEmailTemplate.templateMap.get(templateIndex), me));
		}
		System.out.println("new map =" + newTemplateMap);
		List<String> res=updateMyEmailTemplate(me);
		return res;
	}

	// firstname, lastname,address,city,country,state,zip,phone,email,reward,
	// event, departureCity, destinationCity,date;

	// help function
	public String getCustomerValue(String inputValue, Customer customer) {

		// System.out.println("come update----------");
		// customer = new Customer();
		switch (inputValue) {
		case "first_name":
			return customer.getFirstname();

		case "last_name":
			return customer.getLastname();

		case "address":
			return customer.getAddress();

		case "city":
			return customer.getCity();

		case "country":
			return customer.getCountry();

		case "state":
			return customer.getState();

		case "zip":
			return customer.getZip();

		case "phone":
			return customer.getPhone();

		case "email":
			return customer.getEmail();

		case "rewards":
			return customer.getReward();

		case "event":
			return customer.getAirinfo().getEvent();

		case "departure-city":
			return customer.getAirinfo().getDepartureCity();

		case "destination-city":
			return customer.getAirinfo().getDestinationCity();

		case "Date":
			return customer.getAirinfo().getDate().toString();

		}
		return "input not found";
	}

	// update the template
	public void updateEmailTemplate(int cutomerIndex) throws Exception {

		System.out.println("come to update-----------");

		BufferedReader reader = new BufferedReader(new FileReader(filename));
		String line = reader.readLine();
		int count = 0;
		List<String> res = new ArrayList<String>();
		// System.out.println("map.size= "+newTemplateMap.size());
		// System.out.println(line);
		while (line != null) {

			// get the things inside [[ ]]
			Pattern p = Pattern.compile("\\[\\[(.*?)\\]\\]");
			Matcher m = p.matcher(line);

			while (m.find()) {

				System.out.println(m.group(1));
				// System.out.println(newTemplateMap.get(m.group(1)));
				// System.out.println();
				line = line.replace(m.group(0), newMapList.get(cutomerIndex).get(m.group(1)));
				// System.out.println(replaceStr);

			}
			res.add(line);

			line = reader.readLine();
		}

		System.out.println("result--------------------");
		for (int i = 0; i < res.size(); i++) {
			System.out.println(res.get(i));
		}
		writeEmail(res, cutomerIndex);
	}

	public List<String> updateMyEmailTemplate(Customer me) throws Exception {

		System.out.println("come to update Myself-----------");
		BufferedReader reader = new BufferedReader(new FileReader(filename));
		List<String> res = new ArrayList<String>();
		String line = reader.readLine();

		while (line != null) {
			Pattern p = Pattern.compile("\\[\\[(.*?)\\]\\]");
			Matcher m = p.matcher(line);

			while (m.find()) {

				System.out.println(m.group(1));
				// System.out.println(newTemplateMap.get(m.group(1)));
				// System.out.println();
				line = line.replace(m.group(0), newTemplateMap.get(m.group(1)));
				// System.out.println(replaceStr);

			}
			res.add(line);
			line = reader.readLine();
		}
//		for (int i = 0; i < res.size(); i++) {
//			System.out.println(res.get(i));
//		}
		return res;
	}

	// write the email
	public void writeEmail(List<String> res, int cutomerIndex) {

		PrintWriter writer;
		String filenamePart = "email" + File.separatorChar + "template" + (cutomerIndex + 1) + ".txt";
//		String filename = (System.getProperty("user.dir") + File.separatorChar + "src" + File.separatorChar + filenamePart);
		 String filename = (System.getProperty("user.dir") + File.separatorChar +filenamePart);
		try {
			writer = new PrintWriter(new File(filename));

			// initilize iterator for the hashmap

			for (int i = 0; i < res.size(); i++) {

				// write each key, value pair to each line
				writer.println(res.get(i));

			}
			// close the writer
			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	// public static void main(String[] args) {
	// // ReplaceTemplate replace = new ReplaceTemplate();
	// // ReadCSVTest readCSVTest=new ReadCSVTest();
	// // List<Customer> customerList=new ArrayList<Customer>();
	// // // try {
	// // // readCSVTest.readFile(readCSVTest.filename);
	// // // System.out.println(readCSVTest.customerList.size());
	// // // } catch (Exception e) {
	// // // // TODO Auto-generated catch block
	// // // e.printStackTrace();
	// // // }
	// //
	// // try {
	// // customerList= readCSVTest.readFile(readCSVTest.filename);
	// // System.out.println(customerList.size());
	// // replace.modifyMap(customerList.get(0));
	// // } catch (Exception e) {
	// // // TODO Auto-generated catch block
	// // e.printStackTrace();
	// // }
	//
	// ReplaceTemplate replace = new ReplaceTemplate();
	//
	// try {
	// replace.updateEmailTemplate();
	// } catch (Exception e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	//
	// }
}
