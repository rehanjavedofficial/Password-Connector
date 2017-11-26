package com.rehanjaved;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import com.rehanjaved.data.DataMember;
import com.rehanjaved.data.FileDatabase;
import com.rehanjaved.member.Account;
import com.rehanjaved.member.Category;

public class Manager {

	// Attributes
	private static Manager manager;
	private FileDatabase database;
	private Scanner scan;
	
	/**
	 * @return single instance of manager.
	 */
	public static Manager getInstance(Scanner scan){
		
		if(manager == null){
			manager = new Manager(scan);
		}
		
		return manager;
		
	}
	
	/**
	 * Private Constructor.
	 */
	private Manager(Scanner scan){
		
		this.scan = scan;
		database = FileDatabase.getInstance();
		
	}
	
	/**
	 * Making user to login.
	 */
	public void login(String orignalPassword){
		
		String userPassword;
		int tries = 0;
		
		do{
			
			userPassword = scan.nextLine();
			if(orignalPassword.equals(userPassword)){
				return;
			}else{
				System.out.print("$$$$: Wrong Password. Try again..\n@> ");
			}
			
			tries++;
			
		} while(tries != 3);
		
		System.out.println("$$$$: Sorry. you enter wrong password for 3 times..\nSystem Closed..");
		System.exit(0);
		
	}
	
	/**
	 * Adding data into the database.
	 */
	public void addData(){
		
		//Attributes
		Account account;
		ArrayList<String> files = new ArrayList<String>();
		
		// Adding details..
		System.out.println("$$ Adding Data into the Database $$\n");
		System.out.print("Enter Title\n@> ");
		account = new Account(scan.nextLine());
		System.out.print("Enter Website Name\n@> ");
		account.setWebsiteName(scan.nextLine());
		System.out.print("Enter Website Link\n@> ");
		account.setWebsiteLink(scan.nextLine());
		
		// Selecting Category..
		System.out.print("Select Category\n");
		Category[] categories = Category.values();
		for(int i = 0; i < categories.length; i++){
			System.out.println((i+1)+": "+categories[i].toString());
		}
		System.out.print("@> ");
		account.setCategory(categories[scan.nextInt()]);
		scan.nextLine();
		
		System.out.print("Enter Email\n@> ");
		account.setEmail(scan.nextLine());
		System.out.print("Enter Username\n@> ");
		account.setUsername(scan.nextLine());
		System.out.print("Enter Password\n@> ");
		account.setPassword(scan.nextLine());
		System.out.print("Enter Phone Number\n@> ");
		account.setPhoneNumber(scan.nextLine());
		System.out.print("Enter Additional Text\n@> ");
		account.setAdditionalText(scan.nextLine());
		System.out.print("Enter Comments\n@> ");
		account.setComments(scan.nextLine());
		
		// Selecting files..
		System.out.println("$$ Copy all files into the "+FileDatabase.DATA_FOLDER);
		
		int number = 1;
		String text;
		while(true){
			
			System.out.print("Enter name of file "+number+". (Input 00 to end entering files)\n@> ");
			
			text = scan.nextLine();
			if(text.equals("00")){
				break;
			}else if(new File(FileDatabase.DATA_FOLDER+"/"+text).exists()){
				files.add(text);
				number++;
			}else{
				System.out.println("$$$: File does not exists in "+FileDatabase.DATA_FOLDER);
			}
			
		}
		account.setFiles(files);
		System.out.println(account);
		// Adding to file..
		database.addDataMember(new DataMember(account.toString(), false));
		database.upload();
		
		System.out.println("$$ Data added successfully into the file. :)");
		
	}

	/**
	 * To view the entered data..
	 */
	public void viewData() {
		
	}

	/**
	 * To remove any data.
	 */
	public void removeData() {
		
	}

	/**
	 * To change password of account..
	 */
	public void changePassword() {
		
	}
	
}
