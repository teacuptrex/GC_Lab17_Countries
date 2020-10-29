package gcLab17;

import java.io.FileWriter;
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
	
	private static String fileName = "countries.txt";
	private static Path filePath = Paths.get(fileName);
	
	public static void main(String[] args) {
		
	Scanner scn = new Scanner(System.in);
	
	System.out.println("Welcome to the Countries Maintenance Application!");

	while (true) {
		System.out.println("\r\nPlease select an option below: ");
		System.out.println("1. List all Countries and their populations (list)");
		System.out.println("2. Add new Country (add) ");
		System.out.println("3. Remove a Country (remove) ");
		System.out.println("4. Update a Country's population (update)");
		System.out.println("5. Quit (quit)");
		
		String selection = scn.nextLine().toLowerCase();
		if (selection.equals("quit")||selection.equals("5")||selection.equals("q")) {
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
		} else if (selection.equals("remove")||selection.equals("3")||selection.equals("r")) {
			List<Country> things = readFile();
			int i = 1; 
			for (Country thing : things) {
				System.out.println(i++ + ". " + thing);
			}
			removeLineFromFile(Validator.getInt(scn, "Enter the number of the Country you would like to remove: "));
		} else if (selection.contentEquals("update")||selection.equals("4")||selection.equals("u")) {
			List<Country> things = readFile();
			int i = 1; 
			for (Country thing : things) {
				System.out.println(i++ + ". " + thing);
			}
			System.out.println("Enter the number of the Country you would like to update: ");
			int ln = Integer.parseInt(scn.nextLine())-1;
			updatePopulation(ln,Validator.getLong(scn, "Please enter an updated population: "));
		}
		
		else {
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
				countries.add(new Country(ln[0],Integer.parseInt(ln[1]),ln[2]));
			}
			return countries;
		} catch (NumberFormatException | IOException e) {

			e.printStackTrace();
			return new ArrayList<>();
		}
	}
	
	public static void appendLineToFile(Country thing) {

		String line = thing.getName() + "$$$" + thing.getPopulation() + "$$$" + thing.getContinent();
		
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
		long population = Validator.getLong(scn, "Enter "+ name + "'s population: ");
		String continent = Validator.getString(scn, "Which continent is " + name + " on?");
		return new Country(name, population, continent);
	}
	
	public static void removeLineFromFile(int line) {
		try {
			List<String> entries = Files.readAllLines(filePath);
			entries.remove(line-1);
			Files.deleteIfExists(filePath);
			Files.write(filePath, entries, StandardOpenOption.CREATE,
					StandardOpenOption.APPEND);
			
		} catch (NumberFormatException | IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void updatePopulation(int line, long pop) {
		try {
			List<String> entries = Files.readAllLines(filePath);
			String[] e = entries.get(line).split("\\$\\$\\$");
			e[1] = "" + pop;
			String b = String.join("$$$", e);
			entries.set(line, b);
			Files.deleteIfExists(filePath);
			Files.write(filePath, entries, StandardOpenOption.CREATE,
					StandardOpenOption.APPEND);
			
		} catch (NumberFormatException | IOException e) {
			e.printStackTrace();
		}
	}

}
