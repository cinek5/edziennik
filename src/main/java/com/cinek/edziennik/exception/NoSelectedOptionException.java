package com.cinek.edziennik.exception;

public class NoSelectedOptionException extends Exception {
	static String message = "No selected option in course add form";
	   public NoSelectedOptionException() {
		   super(message);
	   }
			
}
