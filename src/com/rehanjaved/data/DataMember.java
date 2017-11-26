package com.rehanjaved.data;

import java.io.Serializable;

public class DataMember implements Serializable{

	// Attributes
	private static final long serialVersionUID = 1L;
	private String data;
	private boolean encrypted;
	private Crypt crypt;
	
	/**
	 * Constructor.
	 * 
	 * @param data
	 * @param encrypt
	 */
	public DataMember(String data, boolean encrypted) {
	
		this.data = data;
		this.encrypted = encrypted;
	
		crypt = Crypt.getInstance();
		
		if(!encrypted){
			this.data = crypt.encrypt(data);
			this.encrypted = true;
		}
		
	}

	/**
	 * @return data
	 */
	public String getData() {
	
		return data;
	
	}
	
	/**
	 * Setting new value of data
	 * @param data
	 */
	public void setData(String data) {
	
		this.data = data;
	
	}
	
	/**
	 * @return encrypted
	 */
	public boolean isEncrypted() {
	
		return encrypted;
	
	}
	/**
	 * Setting new value of encrypted
	 * @param encrypted
	 */
	public void setEncrypted(boolean encrypted) {
	
		this.encrypted = encrypted;
	
	}
	
	/**
	 * Decrypt the text if it is
	 * already encrypted.
	 * 
	 * @return decrypted data
	 */
	public String getDecryptedDate(){
		
		return !this.encrypted ? this.data : crypt.decrypt(this.data);
		
	}
	
}
