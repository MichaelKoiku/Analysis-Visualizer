package utilitiesLayer;

/**
 * Country class to store the country information
 */
public class Country {
    private String countryName;
    private String countryCode;
    private String startYear;
    private String endYear;

    /**
     * Constructor for objects of class Country
     * 
     * @param countryName The name of the country
     * @param countryCode The code of the country
     */
    public Country(String countryName, String countryCode, String startYear, String endYear) {
        this.countryName = countryName;
        this.countryCode = countryCode;
        this.startYear = startYear;
        this.endYear = endYear;
    }

    /**
     * @return The name of the country
     */
    public String getCountryName() {
        return countryName;
    }

    /**
     * @return The code of the country
     */
    public String getCountryCode() {
        return countryCode;
    }

    /**
     * @return The start year of the country
     */
    public String getStartYear() {
        return startYear;
    }

    /**
     * @return The end year of the country
     */
    public String getEndYear() {
        return endYear;
    }

}