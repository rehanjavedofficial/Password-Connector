package com.rehanjaved.member;

import java.util.ArrayList;
import java.util.Arrays;

public class Account{

	// Attributes..
	private String websiteName;
	private Category category;
	private String websiteLink;
	private String email;
	private String username;
	private String password;
	private String phoneNumber;
	private String additionalText;
	private String comments;
	private ArrayList<String> notes;
	private ArrayList<String> files;
	
	/**
	 * Constructors.
	 * 
	 * @param websiteName
	 * @param category
	 * @param websiteLink
	 * @param email
	 * @param username
	 * @param password
	 * @param phoneNumber
	 * @param additionalText
	 * @param comments
	 * @param notes
	 */
	public Account(String websiteName, Category category, String websiteLink, String email, String username,
			String password, String phoneNumber, String additionalText, String comments, ArrayList<String> notes,
			ArrayList<String> files) {
		
		this.websiteName = websiteName;
		this.category = category;
		this.websiteLink = websiteLink;
		this.email = email;
		this.username = username;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.additionalText = additionalText;
		this.comments = comments;
		
		if(notes == null){
			this.notes = new ArrayList<String>();
		}else{
			this.notes = notes;
		}
		
		if(files == null){
			this.files = new ArrayList<String>();
		}else{
			this.files = notes;
		}
	
	}

	/**
	 * @return websiteName
	 */
	public String getWebsiteName() {
		
		return websiteName;
	
	}

	/**
	 * Setting new value of websiteName
	 * @param websiteName
	 */
	public void setWebsiteName(String websiteName) {
	
		this.websiteName = websiteName;
	
	}

	/**
	 * @return category
	 */
	public Category getCategory() {
		
		return category;

	}

	/**
	 * Setting new value of category
	 * @param category
	 */
	public void setCategory(Category category) {
	
		this.category = category;
	
	}

	/**
	 * @return websiteLink
	 */
	public String getWebsiteLink() {
	
		return websiteLink;
	
	}

	/**
	 * Setting new value of websiteLink
	 * @param websiteLink
	 */
	public void setWebsiteLink(String websiteLink) {
	
		this.websiteLink = websiteLink;
	
	}

	/**
	 * @return email
	 */
	public String getEmail() {
	
		return email;
	
	}

	/**
	 * Setting new value of email
	 * @param email
	 */
	public void setEmail(String email) {
	
		this.email = email;
	
	}

	/**
	 * @return username
	 */
	public String getUsername() {
	
		return username;
	
	}

	/**
	 * Setting new value of username
	 * @param username
	 */
	public void setUsername(String username) {
	
		this.username = username;
	
	}

	/**
	 * @return password
	 */
	public String getPassword() {
	
		return password;
	
	}

	/**
	 * Setting new value of password
	 * @param password
	 */
	public void setPassword(String password) {
	
		this.password = password;
	
	}

	/**
	 * @return phoneNumber
	 */
	public String getPhoneNumber() {
	
		return phoneNumber;
	
	}

	/**
	 * Setting new value of phoneNumber
	 * @param phoneNumber
	 */
	public void setPhoneNumber(String phoneNumber) {
	
		this.phoneNumber = phoneNumber;
	
	}

	/**
	 * @return additionalText
	 */
	public String getAdditionalText() {
	
		return additionalText;
	
	}

	/**
	 * Setting new value of additionalText
	 * @param additionalText
	 */
	public void setAdditionalText(String additionalText) {
	
		this.additionalText = additionalText;
	
	}

	/**
	 * @return comments
	 */
	public String getComments() {
	
		return comments;
	
	}

	/**
	 * Setting new value of comments
	 * @param comments
	 */
	public void setComments(String comments) {
	
		this.comments = comments;
	
	}

	/**
	 * @return notes
	 */
	public ArrayList<String> getNotes() {
	
		return notes;
	
	}

	/**
	 * Setting new value of notes
	 * @param notes
	 */
	public void setNotes(ArrayList<String> notes) {
	
		this.notes = notes;
	
	}

	/**
	 * @return files
	 */
	public ArrayList<String> getFiles() {

		return files;
	
	}

	/**
	 * Setting new value of files
	 * @param files
	 */
	public void setFiles(ArrayList<String> files) {
	
		this.files = files;
	
	}

	/**
	 * Details of object.
	 */
	@Override
	public String toString() {
		
		return "Account >> Website Name: " + websiteName + "\nCategory: " + category + "\nLink: " + websiteLink
				+ "\nEmail: " + email + "\nUsername: " + username + "\nPassword: " + password + "\nPhone Number: "
				+ phoneNumber + "\nAdditional Text: " + additionalText + "\nComments: " + comments + "\nNotes: "
				+ Arrays.toString(notes.toArray(new String[]{})) + "\nFiles: "
						+ Arrays.toString(files.toArray(new String[]{}));
	
	}
	
}
