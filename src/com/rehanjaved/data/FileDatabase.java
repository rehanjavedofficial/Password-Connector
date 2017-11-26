package com.rehanjaved.data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class FileDatabase {

	// Attributes
	private static FileDatabase fileDatabase;
	public static final String DATA_FOLDER = "data";
	private final File wallet = new File("wallet.rj");
	private ArrayList<DataMember> database = null;
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
	public void updateDataMember(DataMember member, int id){
		
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
	
}
