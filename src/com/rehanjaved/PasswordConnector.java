package com.rehanjaved;

import java.util.Scanner;

import com.rehanjaved.data.FileDatabase;

public class PasswordConnector {

	// Attributes
	private Scanner scan;
	private FileDatabase database;
	private Manager manager;
	
	/**
	 * Initializing and starting
	 * program.
	 */
	public PasswordConnector(){
		
		// initialize
		scan = new Scanner(System.in);
		database = FileDatabase.getInstance();
		manager = Manager.getInstance(scan);
		
		System.out.print("$$Welcome at Password Connector$$\n\n"
				+ "**Please provide password to login into the system..\n@> ");
		
		// Making user to login.
		manager.login(database.getPassword().getDecryptedDate());
		start();
		
	}
	

	
	/**
	 * To start the program.
	 */
	private void start(){
		
		int option = 5;
		
		do{
			
			System.out.print("\n"
					+ "1: Add Data\n"
					+ "2: View Data\n"
					+ "3: Remove Data\n"
					+ "4: Change Password\n"
					+ "5: Exit\n"
					+ "@> ");
			option = scan.nextInt();
			
			switch(option){
			
			case 1: 
				scan.nextLine();
				manager.addData();
				break;
			case 2: 
				manager.viewData();
				break;
			case 3: 
				manager.removeData();
				break;
			case 4: 
				manager.changePassword();
				break;
			case 5:
				break;
			default:
				System.out.print("$$$$: Wrong Selection. Try again..\n@> ");
			
			}
			
		}while(option != 5);
		System.out.print("\n$$Good Bye. Have a nice day$$\nDeveloped by RJ APPS (http://www.rjapps.solutions)");
		
	}
	
}
