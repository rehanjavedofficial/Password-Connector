package com.rehanjaved;


import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import com.rehanjaved.data.DataMember;
import com.rehanjaved.data.FileDatabase;
import com.rehanjaved.member.Account;
import com.rehanjaved.member.Category;

/**
 * @author Rehan Javed
 * @Company RJ APPS
 * @category Security
 * @version 1.0.1
 * @date 2nd December 2017
 */
public class Manager {

	// Attributes
	private static Manager manager;
	private FileDatabase database;
	private Scanner scan;
	private String orignalPassword;
	
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
	public void login(){
		
		this.orignalPassword = database.getPassword().getDecryptedDate();
		
		if(!verifyPassword()){
		
			System.out.println("$$$$: Sorry. you enter wrong password for 3 times..\nSystem Closed..");
			System.exit(0);
		
		}
		
	}
	
	/**
	 * @return verified
	 */
	private boolean verifyPassword(){
		
		int tries = 0;
		String userPassword;
		
		do{
			
			userPassword = scan.nextLine();
			if(orignalPassword.equals(userPassword)){
				return true;
			}else{
				System.out.print("$$$$: Wrong Password. Try again..\n@> ");
			}
			
			tries++;
			
		} while(tries != 3);
		
		return false;
		
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
		account = new Account(database.getNewID(), scan.nextLine());
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
		account.setCategory(categories[scan.nextInt()-1]);
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
	
		System.out.println("%%%%%% Viewing Data %%%%%%\n");
		ArrayList<DataMember> members = database.getDatabase();
		
		for(DataMember member: members){
			
			System.out.println(member.getDecryptedDate()+"\n");
			
		}
		
	}

	/**
	 * To remove any data.
	 */
	public void removeData() {
		
		// old password.
		System.out.print("$$ Removing Data $$\n"
				+ "Enter ID to remove\n@> ");
		String id = scan.nextLine();
		ArrayList<DataMember> list = database.getDatabase();
		String data;
		
		for(int i = 0; i < list.size(); i++){
			
			data = list.get(i).getDecryptedDate();
			if(id.equals(data.substring(data.indexOf(' ')+1, data.indexOf('\n')))){
				
				System.out.println("$$ Data to Remove $$\n");
				System.out.println(data+"\n\nEnter master password to remove data\n@> ");
				
				if(verifyPassword()){
					
					database.removeDataMember(i);
					database.upload();
					System.out.println("\n$$ Data successfully removed from database.");
					return;
					
				}else{
					
					System.out.println("\n$$$$: You entered wrong password for 3 times.\n"
							+ "System did not delete the data from database.");
					return;
					
				}
				
			}
			
		}
		System.out.println("$$$$: ID is wrong.");
		
	}

	/**
	 * To change password of account..
	 */
	public void changePassword() {
		
		// old password.
		System.out.print("$$ Changing Password $$\nEnter old password\n@> ");
		
		if(!scan.nextLine().equals(orignalPassword)){
			System.out.println("$$$: Old password is wrong.");
			return;
		}
		
		// setting new password.
		System.out.print("Enter new password\n@> ");
		String newPassword = scan.nextLine();
		System.out.print("Enter confirm password\n@> ");
		
		if(scan.nextLine().equals(newPassword)){
			database.setPassword(new DataMember(newPassword, false));
			database.upload();
			System.out.println("$$ Password updated successfully..");
		}else{
			System.out.println("$$$: Both password does not match..");
		}
		
	}

	/**
	 * To search data.
	 */
	public void searchData() {
	
		System.out.print("$$ Searching Data $$\nSelect to search data.\n"
				+ "1: By ID\n"
				+ "2: By Title\n"
				+ "3: By Website\n"
				+ "4: By Category\n"
				+ "5: By Email\n"
				+ "6: By Username\n"
				+ "7: By Phone Number\n"
				+ "@> ");
		int option = scan.nextInt();
		
		if(option < 1 || option > 7){
			System.out.println("$$$$: You select wrong option.");
			return;
		}
		Account[] accounts = database.getParsedAccounts();
		String[] details = {"ID","Title","Website","Category","Email","Username","Phone Number"};
		Category[] categories = Category.values();
		
		if(option == 4){
			
			System.out.println("Select Category: \n");
			for(int i = 0; i < categories.length; i++){
				System.out.println((i+1)+": "+categories[i].toString());
			}
			System.out.print("@> ");
			int selectedCategory = scan.nextInt();
			
			if(selectedCategory < 1 || selectedCategory > categories.length){
				System.out.println("$$$$: You select wrong category option.");
				return;
			}
			boolean flag = false;
			for(int i = 0; i < accounts.length; i++){
				if(categories[selectedCategory-1] == accounts[i].getCategory()){
					flag = true;
					System.out.println(accounts[i]+"\n");
				}
			}
			if(!flag){
				System.out.println("$$ No any data found with this keyword "+categories[selectedCategory]);
			}
		}else{
			
			System.out.print("Enter "+details[option - 1]+" to search with.\n@> ");
			scan.nextLine();
			String text = scan.nextLine();
			boolean flag = false;
			String userDataText = "";
			
			for(int i = 0; i < accounts.length; i++){
				
				switch(option){
					
				case 1:
					userDataText = accounts[i].getId()+"";
					break;
				case 2:
					userDataText = accounts[i].getTitle()+"";
					break;
				case 3:
					userDataText = accounts[i].getWebsiteName()+"";
					break;
				case 5:
					userDataText = accounts[i].getEmail()+"";
					break;
				case 6:
					userDataText = accounts[i].getUsername()+"";
					break;
				case 7:
					userDataText = accounts[i].getPhoneNumber()+"";
					break;
				}
				
				if(userDataText.toLowerCase().contains(text.toLowerCase())){
					flag = true;
					System.out.println(accounts[i]+"\n");
				}
				
			}
			
			if(!flag){
				System.out.println("$$ No any data found with this "+text+" keyword for "+details[option-1]);
			}
			
		}
		
	}
	
	/**
	 * To update data..
	 */
	public void updateData(){
		
		System.out.print("$$ Updating Data $$\n"
				+ "Enter ID to update\n@> ");
		int id = scan.nextInt();
		Account[] accounts = database.getParsedAccounts();

		for(int i = 0; i < accounts.length; i++){

			if(id == accounts[i].getId()){

				System.out.print("$$ Data Found\n"+accounts[i]+"\n\nEnter master password to update data\n@> ");

				scan.nextLine();
				if(verifyPassword()){

					System.out.print("\nSelect to Update Field.\n"
							+ "1: Title\n"
							+ "2: Password\n"
							+ "3: Additional Text\n"
							+ "4: Comments\n"
							+ "5: Files\n"
							+ "@> ");
					int option = scan.nextInt();
					if(option < 1 || option > 5){
						System.out.println("$$$$: You select wrong option.");
					}else{
						String[] details = {"Title","Password","Additional Text","Comments"};
						if(option == 5){
							
							ArrayList<String> files = accounts[i].getFiles();
							String file;
							while(true){
								
								System.out.println("Files...");
								for(int j = 0; j < files.size(); j++){
									System.out.println("* "+files.get(j));
								}
								System.out.print("\n$$ Select Operations...\n"
										+ "1: Add File\n"
										+ "2: Remove File\n"
										+ "3: Update and Exit\n@> ");
								option = scan.nextInt();
								
								if(option == 1){
									scan.nextLine();
									System.out.print("Enter name of file to add\n@> ");
									file = scan.nextLine();
									if(new File(FileDatabase.DATA_FOLDER+"/"+file).exists()){
										files.add(file);
										System.out.println("$$ File successfully added.");
									}else{
										System.out.println("$$$$: file does not exists in folder.");
									}
								}else if(option == 2){
									scan.nextLine();
									System.out.print("Enter name of the file to delete\n@> ");
									file = scan.nextLine();
									if(files.contains(file)){
										files.remove(file);
										System.out.println("$$ File deleted successfully.");
									}else{
										System.out.println("$$$$: File name is wrong.. ");
									}
								}else if(option == 3){
									break;
								}else{
									System.out.println("$$ You select wrong option..\n");
								}
								
							}
							accounts[i].setFiles(files);
							
						}else{
							
							System.out.print("Enter updated "+details[option - 1]+"\n@> ");
							scan.nextLine();
							String updated = scan.nextLine();
							
							switch(option){
							case 1:
								accounts[i].setTitle(updated);
								break;
							case 2:
								System.out.println();
								accounts[i].setPassword(updated);
								break;
							case 3:
								accounts[i].setAdditionalText(updated);
								break;
							case 4:
								accounts[i].setComments(updated);
								break;
							}
							
						}
						database.updateDataMember(i, new DataMember(accounts[i].toString(), false));
						database.upload();
						System.out.println("$$ Data updated Successfully!! $$");
					}
					return;

				}else{

					System.out.println("\n$$$$: You entered wrong password for 3 times.\n"
							+ "System did not delete the data from database.");
					return;

				}

			}

		}
		System.out.println("$$$$: ID is wrong.");
		
	}
	
}
