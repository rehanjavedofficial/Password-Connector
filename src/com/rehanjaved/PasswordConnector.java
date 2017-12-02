package com.rehanjaved;

import java.util.Scanner;

/**
 * @author Rehan Javed
 * @Company RJ APPS
 * @category Security
 * @version 1.0.1
 * @date 2nd December 2017
 */
public class PasswordConnector {

	// Attributes
	private Scanner scan;
	private Manager manager;
	
	/**
	 * Initializing and starting
	 * program.
	 */
	public PasswordConnector(){
		
		// initialize
		scan = new Scanner(System.in);
		manager = Manager.getInstance(scan);
		
		System.out.print("\n$$Welcome at Password Connector$$\n\n"
				+ "**Please provide password to login into the system..\n@> ");
		
		// Making user to login.
		manager.login();
		start();
		
	}
	

	
	/**
	 * To start the program.
	 */
	private void start(){
		
		int option = 5;
		
		do{
			
			System.out.print("\n%%%%%%%%% Menu %%%%%%%%%\n"
					+ "1: Add Data\n"
					+ "2: View Data\n"
					+ "3: Search Data\n"
					+ "4: Remove Data\n"
					+ "5: Update Data\n"
					+ "6: Change Password\n"
					+ "7: Exit\n"
					+ "@> ");
			option = scan.nextInt();
			
			switch(option){
			
			case 1: 
				scan.nextLine();
				manager.addData();
				break;
			case 2: 
				scan.nextLine();
				manager.viewData();
				break;
			case 3: 
				manager.searchData();
				break;
			case 4: 
				scan.nextLine();
				manager.removeData();
				break;
			case 5:
				manager.updateData();
				break;
			case 6: 
				scan.nextLine();
				manager.changePassword();
				break;
			case 7:
				break;
			default:
				System.out.print("$$$$: Wrong Selection. Try again..\n@> ");
			
			}
			
		}while(option != 7);
		System.out.println("\n$$Good Bye. Have a nice day$$\nDeveloped by RJ APPS (http://www.rjapps.solutions)");
		
	}
	
}
