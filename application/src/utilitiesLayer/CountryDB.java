package utilitiesLayer;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class is used to read the data from the 'countrylist.csv' file and store it in an array list.
 * 
 * Order of data from 'countrylist.csv': [0] Country Code, [1] "Country Name, Full ", [2] "Country Name, Abbreviation", [3] Country Comments, [4] ISO2-digit Alpha, [5] ISO3-digit Alpha, [6] Start Valid Year, [7] End Valid Year
 */
public class CountryDB {
    private ArrayList<Country> countryDB = new ArrayList<>(); // Array list to store all the countries in the database (countrylist.csv)

    /**
     * Constructor for objects of class CountryDB
     */
    public CountryDB() {
        File file = new File("countrylist.csv"); // Create a file object for the file 'countrylist.csv'
        try (Scanner scanner = new Scanner(file)) { // Create a scanner object to read the file 'countrylist.csv'
            while (scanner.hasNextLine()) { // While there is a next line in the file
                String line = scanner.nextLine(); // Read the next line
                String[] countryData = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)"); // Regex to split the line by commas, but not commas inside quotes
                String[] values = new String[4]; // Array to store the values of the country
                if (!countryData[2].equals("") || !countryData[2].equals("N/A")) { // If the country name is not empty or N/A
                    values[0] = countryData[2]; // Country Name, Full
                }
                if (!countryData[5].equals("") || !countryData[5].equals("N/A")) { // If the ISO3-digit Alpha is not empty or N/A
                    values[1] = countryData[5]; // ISO3-digit Alpha (Country Code)
                } else { // If the ISO3-digit Alpha is empty or N/A add to the blacklist
                    values[1] = "N/A";
                }
                if (!countryData[6].equals("") || !countryData[6].equals("N/A")) { // If the Start Valid Year is not empty or N/A
                    values[2] = countryData[6]; // Start Valid Year
                } else { // If the Start Valid Year is empty or N/A add to the blacklist
                    values[2] = "N/A";
                }
                if (countryData[7].equals("Now")) { // If the end year is 'Now', set the end year to 2022
                    values[3] = "2022"; // Get the current year
                } else if (!countryData[7].equals("") || !countryData[7].equals("N/A")) { // If the end year is not empty or N/A
                    values[3] = countryData[7]; // End Valid Year
                } else { // If the end year is empty or N/A add to the blacklist
                    values[3] = "N/A";
                }
                countryDB.add(new Country(values[0], values[1], values[2], values[3])); // Create a new country object and add it to the array list
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Getter for countryDB
     * 
     * @return the countryDB array list
     */
    public ArrayList<Country> getCountryDB() {
        return countryDB;
    }

    /**
     * Get the country name from the index
     * 
     * @param string the index of the country in the array list
     * @return the country at index i in the countryDB array list
     */
    public Country getCountry(String string) {
        // loop through the array list
        for (int i = 0; i < countryDB.size(); i++) {
            // if the country code is equal to the string
            if (countryDB.get(i).getCountryName().equals(string)) {
                return countryDB.get(i);
            }
        }
        return null;
    }

}