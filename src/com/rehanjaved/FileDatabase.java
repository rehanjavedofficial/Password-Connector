package com.rehanjaved;

public class FileDatabase {

	private static FileDatabase fileDatabase;
	
	public static FileDatabase getInstance(){
		
		if(fileDatabase == null){
			fileDatabase = new FileDatabase();
		}
		
		return fileDatabase;
		
	}
	
}
