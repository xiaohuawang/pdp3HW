package main;

import java.io.File;

public class TestFileName {

//	/Users/yangyangyy/git/Assignment3/Assignment3/src/classes/email
//	/Users/yangyangyy/git/Assignment3/Assignment3/src/email
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		String filename = (System.getProperty("user.dir") + File.separatorChar + "src" + File.separatorChar + "email");
//		String filename = (System.getProperty("user.dir") + File.separatorChar + "email");
		String filename = (System.getProperty("user.dir") + File.separatorChar + "src");
		System.out.println(filename);
	}

}
