package main;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandInputMain {

	public AirInfo airinfo;
	public String templateFileName;
	public String csvFileName;
	public SendEmailToMyself sendEmailToMyself;
	// public String outputFileName;

	public void getAirInfo(String[] args) {
		System.out.println("get input");
		airinfo = new AirInfo();
		for (String temp : args) {

			if (temp.contains(".txt")) {
				System.out.println("email template name= " + temp);
				templateFileName = temp;
				// airinfo.setDestinationCity(m.group(1));

			}

			if (temp.contains(".csv")) {
				// get the things inside [[ ]]
				// Pattern p = Pattern.compile("qq(.*?)qq");
				csvFileName = temp;
				System.out.println("csv file name= " + csvFileName);

				Pattern p1 = Pattern.compile("From(.*?)To");
				Pattern p2 = Pattern.compile("To(.*).csv");
				// Pattern p = Pattern.compile("\\[\\[(.*?)\\]\\]");

				Matcher m1 = p1.matcher(temp);
				Matcher m2 = p2.matcher(temp);

				while (m1.find()) {
					// System.out.println("comeeeeeeee");
					System.out.println("departure city = " + m1.group(1));
					airinfo.setDepartureCity(m1.group(1));
				}

				while (m2.find()) {
					// System.out.println("comeeeeeeee");
					System.out.println("arrival city = " + m2.group(1));
					airinfo.setDestinationCity(m2.group(1));
				}

			}
			if (temp.contains("arrival")) {
				System.out.println("event = " + "arrival");
				airinfo.setEvent("arrival");
			}

			if (temp.contains("departure")) {
				System.out.println("event = " + "departure");
				airinfo.setEvent("departure");
			}
		}

	}

	public Date getLocalTime() {
		Calendar cal = Calendar.getInstance();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String currentTime = df.format(cal.getInstance().getTime());
		long localTimeLong = Timestamp.valueOf(currentTime).getTime();
		Date localTime = new Date(localTimeLong);

		return localTime;
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		ReadCSVTest readCSVTest = new ReadCSVTest();
		ReadEmailTemplate readEmailTemplate = new ReadEmailTemplate();
		CommandInputMain commandInputTest = new CommandInputMain();
		ReplaceTemplate replaceTemplate = new ReplaceTemplate();
		SendEmailToMyself sendEmailToMyself = new SendEmailToMyself();
		List<Customer> customerList = new ArrayList<Customer>();
		List<String> inputList = new ArrayList<String>();

		commandInputTest.getAirInfo(args);

		// throw exception when no template file is provided
		if (commandInputTest.templateFileName == null) {
			throw new IOException(" --email-template is  given  without  providing appropriate arguments:" + "\nUsage:"
					+ "\n--email-template <file>        accepts a filename that holds the email template."
					+ "\n--output-dir <path>            accepts a filename that holds the email template."
					+ "\n--csv-file <path>              accepts the name of the csv file to process, in a following format Flight<id>From<departure-city>To<destination-city>.csv"
					+ "\n--event <details>              specifies if the delay refers to departure/arrival"
					+ "\nFor example"
					+ "\n--email-template    email-template.txt    --output-dir    emails    --csv-file    "
					+ "Flight363FromSeattleToBoston.csv –-event arrival");

		}

		// if (commandInputTest.outputFileName == null) {
		// throw new IOException(" --output-dir is given without providing
		// appropriate arguments:"
		// +"\nUsage:"
		// +"\n--email-template <file> accepts a filename that holds the email
		// template."
		// +"\n--output-dir <path> accepts a filename that holds the email
		// template."
		// +"\n--csv-file <path> accepts the name of the csv file to process, in
		// a following format
		// Flight<id>From<departure-city>To<destination-city>.csv"
		// +"\n--event <details> specifies if the delay refers to
		// departure/arrival"
		// +"\nFor example"
		// +"\n--email-template email-template.txt --output-dir emails
		// --csv-file "
		// +"Flight363FromSeattleToBoston.csv –-event arrival");
		// }

		// throw exception when no csv file has been provided
		if (commandInputTest.csvFileName == null) {
			throw new IOException(" .csv file is not given properly" + "\nUsage:"
					+ "\n--email-template <file>        accepts a filename that holds the email template."
					+ "\n--output-dir <path>            accepts a filename that holds the email template."
					+ "\n--csv-file <path>              accepts the name of the csv file to process, in a following format Flight<id>From<departure-city>To<destination-city>.csv"
					+ "\n--event <details>              specifies if the delay refers to departure/arrival"
					+ "\nFor example"
					+ "\n--email-template    email-template.txt    --output-dir    emails    --csv-file    "
					+ "Flight363FromSeattleToBoston.csv –-event arrival");
		}

		// throw exception when no departure city has been provided
		if (commandInputTest.airinfo.getDepartureCity().equals("")) {
			throw new IOException(" -csv-file argument does not contain departure-city " + "\nUsage:"
					+ "\n--email-template <file>        accepts a filename that holds the email template."
					+ "\n--output-dir <path>            accepts a filename that holds the email template."
					+ "\n--csv-file <path>              accepts the name of the csv file to process, in a following format Flight<id>From<departure-city>To<destination-city>.csv"
					+ "\n--event <details>              specifies if the delay refers to departure/arrival"
					+ "\nFor example"
					+ "\n--email-template    email-template.txt    --output-dir    emails    --csv-file    "
					+ "Flight363FromSeattleToBoston.csv –-event arrival");
		}

		// throw exception when no arrival city has been provided
		if (commandInputTest.airinfo.getDestinationCity().equals("")) {
			throw new IOException(" -csv-file argument does not contain destination-city " + "\nUsage:"
					+ "\n--email-template <file>        accepts a filename that holds the email template."
					+ "\n--output-dir <path>            accepts a filename that holds the email template."
					+ "\n--csv-file <path>              accepts the name of the csv file to process, in a following format Flight<id>From<departure-city>To<destination-city>.csv"
					+ "\n--event <details>              specifies if the delay refers to departure/arrival"
					+ "\nFor example"
					+ "\n--email-template    email-template.txt    --output-dir    emails    --csv-file    "
					+ "Flight363FromSeattleToBoston.csv –-event arrival");
		}

		Date date = commandInputTest.getLocalTime();
		commandInputTest.airinfo.setDate(date);

		System.out.println(commandInputTest.airinfo.getDepartureCity());
		System.out.println(commandInputTest.airinfo.getDestinationCity());
		System.out.println(commandInputTest.airinfo.getEvent());
		System.out.println(commandInputTest.airinfo.getDate());
		System.out.println();

		System.out.println("--------------program start--------------");

		// for (String temp : inputList) {

		// if (temp.contains(".txt")) {
		// // test.getCSVFileName(temp);
		// }
		// if (temp.contains("")) {
		// test.getCSVFileName(temp);
		// }
		// }

		// String departureCity = "Seattle";
		// String destinationCity = "Boston";
		// String event = "arrival";

		// AirInfo airinfo = new AirInfo();
		// airinfo.setDate(date);
		// airinfo.setDepartureCity(departureCity);
		// airinfo.setDestinationCity(destinationCity);
		// airinfo.setEvent(event);

		try {
			customerList = readCSVTest.readFile(commandInputTest.csvFileName);
			System.out.println(customerList.size());

			// for (int i = 0; i < 1; i++) {
			for (int i = 0; i < customerList.size(); i++) {
				customerList.get(i).setAirinfo(commandInputTest.airinfo);
			}

			// System.out.println("---------come---------");
			// System.out.println("info= " +
			// customerList.get(0).getAirinfo().getDate());
			// System.out.println("info= " + customerList.get(0).getEmail());
			// System.out.println("info= " +
			// customerList.get(0).getAirinfo().getEvent());
			// System.out.println("info= " +
			// customerList.get(0).getFirstname());
			// System.out.println("info= " + customerList.get(0).getLastname());
			// System.out.println("info= " + customerList.get(0).getAddress());

			for (int i = 0; i < 1; i++) {
				// for (int i = 0; i < customerList.size(); i++) {

				replaceTemplate.modifyMap(customerList, commandInputTest.templateFileName);
			}

			System.out.println("do you want to send an email for yourself?(y/n)");
			Scanner sc = new Scanner(System.in);
			if (sc.nextLine().equalsIgnoreCase("y")) {
				Customer me = new Customer();
				me.setAirinfo(commandInputTest.airinfo);
				sendEmailToMyself.sendEmailToMe(me, commandInputTest.templateFileName);
			} else {
				System.exit(0);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// // System.out.println(customerList.get(0).getAirinfo().getDate());
	}

}
