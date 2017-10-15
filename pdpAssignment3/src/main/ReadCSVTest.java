package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ReadCSVTest {

	Customer customer;
	List<Customer> customerList = new ArrayList<Customer>();
	// List<Customer> customerList=new ArrayList<Customer>();
	// String filename = (File.separatorChar + "src" + File.separatorChar
	// + "Flight363FromSeattleToBoston.csv");
	// String filename = (System.getProperty("user.dir") + File.separatorChar +
	// "src" + File.separatorChar
	// + "Flight363FromSeattleToBoston.csv");

	public String getFileName(String filename) {
		filename = (System.getProperty("user.dir") + File.separatorChar + filename);
//		filename = (System.getProperty("user.dir") + File.separatorChar + "src" + File.separatorChar + filename);
		return filename;
	}

	// firstname, lastname,address,city,country,state,zip,phone,email,reward,
	public void setCustomerValue(int inputValue, String value, Customer customer) {

		switch (inputValue) {
		case 0:
			customer.setFirstname(value);
			break;
		case 1:
			customer.setLastname(value);
			break;
		case 2:
			customer.setAddress(value);
			break;
		case 3:
			customer.setCity(value);
			break;
		case 4:
			customer.setCountry(value);
			break;
		case 5:
			customer.setState(value);
			break;
		case 6:
			customer.setZip(value);
			break;
		case 7:
			customer.setPhone(value);
			break;
		case 8:
			customer.setEmail(value);
			break;
		case 9:
			customer.setReward(value);
			break;
		}
	}

	public List<Customer> readFile(String filename) throws Exception {
		
		filename= this.getFileName(filename);
		BufferedReader reader = new BufferedReader(new FileReader(filename));
		HashMap<Integer, List<String>> myMap = new HashMap<Integer, List<String>>();
		List<String> tempList = null;
		int countLine = 0;
		String line = reader.readLine();
		line = reader.readLine();

		while (line != null) {
			// System.out.println(line);
			String parts[] = line.split(",");

			tempList = new ArrayList<String>();
			customer = new Customer();
			for (int i = 0; i < parts.length; i++) {
				tempList.add(parts[i].substring(1, parts[i].length() - 1).toString());
				setCustomerValue(i, parts[i].substring(1, parts[i].length() - 1).toString(), customer);
			}
			myMap.put(++countLine, tempList);
			customerList.add(customer);

			line = reader.readLine();
		}
		System.out.println();
		System.out.println();

		// print map
		for (int i = 1; i < myMap.size(); i++) {
			System.out.println(myMap.get(i));
		}

		// System.out.println(customerList.get(0).getAirinfo().getDate());
		// System.out.println(customerList.get(0).getEmail());
		// System.out.println(customerList.get(0).getAirinfo().getEvent());
		// System.out.println(customerList.get(0).getFirstname());
		// System.out.println(customerList.get(0).getLastname());
		// System.out.println(customerList.get(0).getAddress());

		return customerList;
	}

	public Customer readFileSelf(String filename,Customer me) throws Exception {
		
		filename= this.getFileName(filename);
		BufferedReader reader = new BufferedReader(new FileReader(filename));
//		HashMap<Integer, String> myselfMap = new HashMap<Integer, String>();
		String line = reader.readLine();

		while (line != null) {
			// System.out.println(line);
			String parts[] = line.split(",");

			for (int i = 0; i < parts.length; i++) {
				setCustomerValue(i, parts[i].substring(1, parts[i].length() - 1).toString(), me);
			}
			line = reader.readLine();
		}
		return me;
	}

	
	// public static void main(String[] args) {
	// // TODO Auto-generated method stub
	// ReadCSVTest test = new ReadCSVTest();
	//
	// // String
	// //
	// res="/Users/yangyangyy/Documents/workspace/Assignment3/src/Flight3FromVancouverToSeattle.csv";
	// // System.out.println(res);
	// System.out.println(test.filename);
	//
	// try {
	// test.readFile(test.filename);
	// } catch (Exception e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// System.out.println();
	// System.out.println();
	// System.out.println("size= "+test.customerList.size());
	// System.out.println(test.customerList.get(50).getCity());
	//
	// }

}
