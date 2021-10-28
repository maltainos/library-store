package com.webservice.app.wservice.shared.utils;

import java.security.SecureRandom;
import java.util.Random;

public class Utils {
	
	private final Random RANDOM = new SecureRandom();
	private final String APHABET = "1234567890ABCDEFGHJIKLMNOPQRSTUVXWYZabcdefghijklmnopqrstuvxwyz";
	
	public String generationBookId(int length) {
		return generatedRandomString(length);
	}
	
	public String generationBookCategoryId(int length) {
		return generatedRandomString(length);
	}
	
	public String generationContactsId(int length) {
		return generatedRandomString(length);
	}
	
	private String generatedRandomString(int length) {
		
		StringBuilder builder = new StringBuilder();
		for(int i = 0; i < length; i++) {
			builder.append(APHABET.charAt(RANDOM.nextInt(APHABET.length())));
		}
		return new String(builder);
	}

}





