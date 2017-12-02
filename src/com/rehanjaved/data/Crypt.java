package com.rehanjaved.data;

import java.io.Serializable;
import java.util.Random;

/**
 * @author Rehan Javed
 * @Company RJ APPS
 * @category Security
 * @version 1.0.1
 * @date 2nd December 2017
 */
public class Crypt implements Serializable{

	// Attributes
	private static final long serialVersionUID = 1L;
	private static Crypt crypt;
	private Random random;

	/**
	 * To Create only one instance.
	 * 
	 * @return singleton object
	 */
	public static Crypt getInstance() {

		if (crypt == null) {
			crypt = new Crypt();
		}

		return crypt;

	}
	
	/**
	 * Private Constructor.
	 */
	private Crypt(){
		
		random = new Random();
		
	}
	
	/**
	 * Encrypting the text.
	 * 
	 * @param line
	 * @return encrypted value.
	 */
	public String encrypt(String line){
		
		char[] array = (new StringBuilder(line)).reverse().toString().toCharArray();
		char[] newArray = new char[array.length * 2];
		int index = 0;
		
		for(int i = 0; i < array.length; i++){
			
			newArray[index++] = (char) (33 + random.nextInt(91 - 33));
			newArray[index++] = array[i];
			
		}
		
		return String.valueOf(newArray);
		
	}
	
	/**
	 * Decrypt the text.
	 * 
	 * @param line
	 * @return decrypted value.
	 */
	public String decrypt(String line){
		
		char[] array = line.toCharArray();
		char[] newArray = new char[array.length / 2];
		int index = 0;
		
		for(int i = 1; i < array.length; i += 2){
			
			newArray[index++] = array[i];
			
		}
		
		return (new StringBuilder(String.valueOf(newArray))).reverse().toString();
		
	}

}
