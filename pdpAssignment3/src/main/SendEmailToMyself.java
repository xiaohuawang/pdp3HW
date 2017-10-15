package main;

import java.util.List;


public class SendEmailToMyself {

	
	private String filenameMe = "MyselfFligh363FromSeattleToBoston.csv";

	public void sendEmailToMe(Customer me, String templateFileName) throws Exception {

		ReadCSVTest readCSVTest = new ReadCSVTest();
		ReplaceTemplate replaceTemplate=new ReplaceTemplate();
		StringBuilder content=new StringBuilder();
		
		me = readCSVTest.readFileSelf(filenameMe, me);
		
//		System.out.println("info= " + me.getAirinfo().getDate());
//		System.out.println("info= " + me.getEmail());
//		System.out.println("info= " + me.getAirinfo().getEvent());
//		System.out.println("info= " + me.getFirstname());
//		System.out.println("info= " + me.getLastname());
//		System.out.println("info= " + me.getAddress());
//		
//		System.out.println(templateFileName);
		
		List<String> res=replaceTemplate.modifyMyself(me, templateFileName);
		
		for(int i=0;i<res.size();i++){
			content.append(res.get(i));
			content.append("\n");
		}
		
		System.out.println(content.toString());
		SendEmailTest test=new SendEmailTest(me.getEmail(),"what's the title",content.toString());
	}
}
