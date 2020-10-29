package gcLab17;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Validator {
	
	public static String getString(Scanner scnr, String prompt) {
		System.out.print(prompt);
		String input = scnr.nextLine();
		return input;
	}
	
	/**
	 * Prompt the user until they enter a valid integer.
	 */
	public static int getInt(Scanner scnr, String prompt) {
		System.out.print(prompt);
		int input = scnr.nextInt();
		scnr.nextLine(); // <-- clear remainder of line.
		return input;
	}
	
	public static long getLong(Scanner scnr, String prompt) {
		System.out.print(prompt);
		long input = scnr.nextLong();
		scnr.nextLine(); // <-- clear remainder of line.
		return input;
	}
	
	/**
	 * Prompt the user until they enter a valid double.
	 */
	public static double getDouble(Scanner scnr, String prompt) {
		System.out.print(prompt);
		double input = scnr.nextDouble();
		scnr.nextLine(); // <-- clear remainder of line.
		return input;
	}
	
	/**
	 * Prompt the user until they enter a valid integer between min and max
	 * inclusive.
	 */
	public static int getIntInRange(Scanner scnr, String prompt, int min, int max) {
		boolean withIn = false;
		int input = getInt(scnr, prompt);
		
		while (withIn == false) {
			
			if (input <= max && input >= min) {
				withIn = true;
			} else {
				withIn = false;
				System.out.println("Your number was not within the range: ");
				input = getInt(scnr,prompt);
			} 
			
		}
		// TODO keep trying until the input is in the range (inclusive)
		// NOTE: for this one, don't worry about the user entering non-integers; assume
		// that getInt takes care of that.
		return input;
	}
	
	/**
	 * Prompt the user until they enter a valid integer equal to or greater than min.
	 */
	public static int getIntAtOrAbove(Scanner scnr, String prompt, int min) {
		int input = getInt(scnr, prompt);
		// TODO keep trying until the input is in the range
		// NOTE: for this one, don't worry about the user entering non-integers; assume
		// that getInt takes care of that.
		return input;
	}
	
	public static boolean getYesNo(Scanner scn, String prompt) {
		String userInput;
		boolean yesOrNo;
		Set<String> validYes = new HashSet<>(Arrays.asList("yes","y","yeah","yep","1","ok","okay"));
		Set<String> validNo = new HashSet<>(Arrays.asList("no","nope","n","0","nah"));
		
		do {
			System.out.println(prompt);
			userInput = scn.nextLine().toLowerCase();
			if(validYes.contains(userInput)) {
				yesOrNo = true;
				break;
			} else if (validNo.contains(userInput)) {
				yesOrNo = false;
				break;
			} else {
				
			} 
		} while(true);
		
		return yesOrNo;
		
	}


}

