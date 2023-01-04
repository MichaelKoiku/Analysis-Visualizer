package utilitiesLayer;

import java.util.HashMap;
import java.util.Map;

/**
 * This class is used to store the country codes and their corresponding country names. 
 * The country codes are used to identify the country of the user.
 * Here the Singleton Design Pattern is being used
 */
public class CountryCodeRepository {
    private static CountryCodeRepository instance;
    private Map<String, String> repository;
    
    private CountryCodeRepository() {}
    
    /**
     * This method is used to get the instance of the class.
     */
    public static CountryCodeRepository getInstance() {
        if(instance == null) {
            instance = new CountryCodeRepository();
        }
        return instance;
    }
    
    public void addCountry(String country, String code) {
        repository.put(country, code);
    }
    
    public void removeCountry(String country, String code) {
        repository.put(country, code);
    }
    
    public String getCountryCode(String country) {
        try {
            return repository.get(country);
        }
        catch (Exception e) {
            throw new NullPointerException();       
        }
    }

    public Map<String, String> getRepository() {
        return repository;
    }
    
    public void intializeWBRepo() {
        repository = new HashMap<String, String>();
        repository.put("USA", "USA");
        repository.put("Canada", "CAN");
        repository.put("Austria", "AUT");
        repository.put("France", "FRA");
        repository.put("Japan", "JPN");
    }

    public void intializeOCRepo() {
        repository = new HashMap<String, String>();
        repository.put("Canada", "CAN");
    }

}
