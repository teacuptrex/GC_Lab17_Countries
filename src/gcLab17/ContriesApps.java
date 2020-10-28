package gcLab17;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;


public class ContriesApps {
	
	private static Path filePath = Paths.get("countries.txt");
	
	public static void main(String[] args) {
		
	Scanner scn = new Scanner(System.in);
	
	System.out.println("Welcome to the Countries Maintenance Application!");

	while (true) {
		System.out.println("\r\nPlease select an option below: \r\n1. List all Countries and their populations (list) \r\n2. Add new Country (add) \r\n3. Quit (quit)");
		String selection = scn.nextLine().toLowerCase();
		if (selection.equals("quit")||selection.equals("3")||selection.equals("q")) {
			break;
		} else if (selection.equals("list")||selection.equals("1")||selection.equals("l")) {
			List<Country> things = readFile();
			int i = 1; 
			for (Country thing : things) {
				System.out.println(i++ + ". " + thing);
			}
		} else if (selection.equals("add")||selection.equals("2")||selection.equals("a")) {
			Country thing = getThingFromUser(scn);
			System.out.println("Adding " + thing);
			appendLineToFile(thing);
		} else {
			System.out.println("Invalid command.");
		}
	}
	System.out.println("Goodbye.");
	scn.close();
}
	
	public static List<Country> readFile() {
		try {
			List<String> entries = Files.readAllLines(filePath);
			List<Country> countries = new ArrayList<>();
			
			for(String entry : entries) {
				String[] ln = entry.split("\\$\\$\\$");
				countries.add(new Country(ln[0],Integer.parseInt(ln[1])));
			}
			return countries;
		} catch (NumberFormatException | IOException e) {

			e.printStackTrace();
			return new ArrayList<>();
		}
	}
	
	public static void appendLineToFile(Country thing) {

		String line = thing.getName() + "$$$" + thing.getPopulation();
		
		List<String> lines = Collections.singletonList(line);

		try {
			Files.write(filePath, lines, StandardOpenOption.CREATE,
					StandardOpenOption.APPEND);
		} catch (IOException e) {
			System.out.println("Unable to write to file.");
		}
	}
	
	private static Country getThingFromUser(Scanner scn) {
		String name = Validator.getString(scn, "Enter Country's name: ");
		int population = Validator.getLong(scn, "Enter "+ name + "'s population: ");
		return new Country(name, population);
	}

}
