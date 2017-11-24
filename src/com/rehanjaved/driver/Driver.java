package com.rehanjaved.driver;

import com.rehanjaved.crypto.Crypt;
import com.rehanjaved.member.Category;
import com.rehanjaved.member.Account;

public class Driver {

	/**
	 * To execute the program.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		Crypt crypt = Crypt.getInstance();
		Account account = new Account("Facebook",Category.SOCIAL, "http://www.facebook.com","rehanjaved.official@gmail.com",
				"born4diee","password123","03048100127","no text","comments",null, null);	
		String encrypt = crypt.encrypt(account.toString());
		System.out.println(encrypt);
		System.out.println(crypt.decrypt(encrypt));
		
		
	}

}
