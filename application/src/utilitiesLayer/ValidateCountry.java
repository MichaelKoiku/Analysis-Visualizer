package utilitiesLayer;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class ValidateCountry {
    private static ArrayList<String> blacklistedCountry = new ArrayList<>();

    public ValidateCountry() {
        File file = new File("blacklist.txt"); // Create a file object for the file 'countrylist.csv'
        try (Scanner scanner = new Scanner(file)) { // Create a scanner object to read the file 'countrylist.csv'
            while (scanner.hasNextLine()) { // While there is a next line in the file
                String line = scanner.nextLine(); // Read the next line
                blacklistedCountry.add(line);
            }
        } catch (FileNotFoundException e) {
            System.out.println("ERROR");
            e.printStackTrace();
        }
    }

    // method to check if a country is in the blacklist
    public static boolean checkBlacklist(String country) {
        for (String blacklistedCountry : blacklistedCountry) {
            if (blacklistedCountry.equals(country)) {
                JOptionPane.showMessageDialog(null, "Data fetching is not allowed for this country. Please select a different country");
                return true;
            }
        }
        return false;
    }

}
