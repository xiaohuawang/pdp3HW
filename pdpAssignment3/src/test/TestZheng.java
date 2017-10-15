package test;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestZheng {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TestZheng test=new TestZheng();
		test.getStrings();
//		String a="abcd";
//		String res=a.substring(1, a.length()-1).toString();
//		System.out.println(res);
	}

	private void getStrings() {
	   
//		String str="qq[aa[qq";
		String str= "rrwerqq123qqasfdasdfrrwerqq84461377qqasfdasdaa654645aafrrwerqq84461378qqasfdaa654646aaasdfrrwerqq84461379qqasfdasdfrrwerqq84461376qqasfdasdf";
	    Pattern p = Pattern.compile("qq(.*?)qq");
////		Pattern p = Pattern.compile(\["(.*?)"\]);
//		
	    Matcher m = p.matcher(str);
	    ArrayList<String> strs = new ArrayList<String>();
	    while (m.find()) {
	      strs.add(m.group(1));      
	    } 
	    for (String s : strs){
	      System.out.println(s);
	    }    
	  }
		
		
//		String in = "Item(s): [item1.test],[item2.qa],[item3.production]";
//		String in="To: email Subject: Please accept our apologies for the [[event]] of your flight Dear [[first_name]] [[last_name]], ";
//		Pattern p = Pattern.compile("\\[\\[(.*?)\\]\\]");
//		Pattern p = Pattern.compile(\["(.*?)"\]);
//		Matcher m = p.matcher(in);
//
//		while(m.find()) {
//		    System.out.println(m.group(1));
//		}	
//		
//		
//	}
}
