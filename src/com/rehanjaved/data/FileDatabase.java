package com.rehanjaved.data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;

import com.rehanjaved.member.Account;
import com.rehanjaved.member.Category;

/**
 * @author Rehan Javed
 * @Company RJ APPS
 * @category Security
 * @version 1.0.1
 * @date 2nd December 2017
 */
public class FileDatabase {

	// Attributes
	private static FileDatabase fileDatabase;
	public static final String DATA_FOLDER = "data";
	private final File wallet = new File("wallet.rj");
	private ArrayList<DataMember> database = null;
	private int id = 0;
	private DataMember password = null;
	
	/**
	 * To Create only one instance.
	 * 
	 * @return singleton object
	 */
	public static FileDatabase getInstance(){
		
		if(fileDatabase == null){
			
			fileDatabase = new FileDatabase();
			
		}
		
		return fileDatabase;
		
	}
	
	/**
	 * Private constructor.
	 */
	private FileDatabase(){
		
		loadDatabase();
		
	}
	
	public int getNewID(){
		
		this.id++;
		upload();
		return id;
		
	}
	
	/**
	 * To add new data member is database.
	 * 
	 * @param member
	 */
	public void addDataMember(DataMember member){
		
		this.database.add(member);
		
	}
	
	/**
	 * Removing data member using index.
	 * 
	 * @param id
	 * @return data member
	 */
	public DataMember removeDataMember(int id){
		
		return this.database.remove(id);
		
	}
	
	/**
	 * Removing data member using object.
	 * 
	 * @param member
	 * @return result
	 */
	public boolean removeDataMember(DataMember member){
		
		return this.database.remove(member);
		
	}
	
	/**
	 * Updating data member with new object.
	 * 
	 * @param member
	 * @param id
	 */
	public void updateDataMember(int id, DataMember member){
		
		this.database.set(id, member);
		
	}
	
	/**
	 * To check is database empty or not.
	 * 
	 * @return result.
	 */
	public boolean isEmpty(){
		
		return this.database.isEmpty();
		
	}
	
	/**
	 * @return password
	 */
	public DataMember getPassword() {
	
		return password;
	
	}

	/**
	 * Setting new value of password
	 * 
	 * @param password
	 */
	public void setPassword(DataMember password) {
	
		this.password = password;
	
	}

	/**
	 * Getting data.
	 * 
	 * @param index
	 * @return data
	 */
	public String getData(int index) {

		if(index >= 0 && index < database.size())
			return database.get(index).getDecryptedDate();
		else 
			return null;
	
	}
	
	/**
	 * @return database
	 */
	public ArrayList<DataMember> getDatabase() {

		return database;
	
	}

	/**
	 * To load database from the file.
	 */
	@SuppressWarnings("unchecked")
	public void loadDatabase(){
		
		if(!this.wallet.exists()){
			
			try {
				wallet.createNewFile();
			} catch (IOException e) {
				System.out.println("$$$$: File not exists, and error occured while creating database.");
				return;
			}
			
		}
		
		File data = new File(DATA_FOLDER);
		if(!data.exists()){
			data.mkdir();
		}
		
		try {
			
			ObjectInputStream stream = new ObjectInputStream(new 
					FileInputStream(wallet));
		
			this.id = Integer.parseInt(((DataMember) stream.readObject()).getDecryptedDate());
			this.password = (DataMember) stream.readObject();
			this.database = (ArrayList<DataMember>) stream.readObject();
			
			stream.close();
			
		} catch (FileNotFoundException e) {
			System.out.println("$$$$: Database File not exists.");
		} catch (IOException e) {
		
			if(this.password == null){
				this.password = new DataMember("admin", false);
			}
			
			if(this.database == null){
				this.database = new ArrayList<DataMember>();
			}
			
			upload();
			
		} catch (ClassNotFoundException e) {
			System.out.println("$$$$: Trying to read wrong input from database.");
		}
		
	}
	
	/**
	 * To update the data in database.
	 */
	public void upload(){
		
		try {
			
			ObjectOutputStream stream = new ObjectOutputStream(new 
					FileOutputStream(wallet));
		
			stream.writeObject(new DataMember(this.id+"", false));
			stream.writeObject(this.password);
			stream.writeObject(this.database);
			stream.flush();
			
			stream.close();
			
		} catch (FileNotFoundException e) {
			System.out.println("$$$$: Database File not exists.");
		} catch (IOException e) {
			System.out.println("$$$$: Error occured while writing data to file.");
		}
		
	}
	
	public Account[] getParsedAccounts(){
		
		Account[] accounts = new Account[database.size()];
		String[] data;
		String tempCategory;
		Category category;
		ArrayList<String> files;
		
		for(int i = 0; i < accounts.length; i++){
			
			data = database.get(i).getDecryptedDate().split("\n");
			tempCategory = data[3].substring(data[3].indexOf(':')+2);
			
			if(tempCategory.equals("SOCIAL")){
				category = Category.SOCIAL;
			}else if(tempCategory.equals("CRYPTOCURRENCY")){
				category = Category.CRYPTOCURRENCY;
			}else if(tempCategory.equals("OFFICIAL")){
				category = Category.OFFICIAL;
			}else if(tempCategory.equals("BUYING")){
				category = Category.BUYING;
			}else{
				category = Category.OTHER;
			}
			
			files = new ArrayList<String>(Arrays.asList(data[11].trim().substring(data[11].indexOf(':')+3, data[11].length() - 1)));
			
			accounts[i] = new Account(Integer.parseInt(data[0].substring(data[0].indexOf(':')+2)),
					data[1].substring(data[1].indexOf(':')+2),data[2].substring(data[2].indexOf(':')+2),
					category,data[4].substring(data[4].indexOf(':')+2),data[5].substring(data[5].indexOf(':')+2),
					data[6].substring(data[6].indexOf(':')+2),data[7].substring(data[7].indexOf(':')+2),
					data[8].substring(data[8].indexOf(':')+2),data[9].substring(data[9].indexOf(':')+2),
					data[10].substring(data[10].indexOf(':')+2),files);
			
		}
		
		return accounts;
		
	}
	
}
