package analysisLayer;

import utilitiesLayer.DataResultObject;

public abstract class Analysis {
    //The Strategy Design Pattern is being use here
    
    private String countryCode;
    private String startYear;
    private String endYear;
    
    public Analysis(String countryCode, String startYear, String endYear) {
        this.countryCode = countryCode;
        this.startYear = startYear;
        this.endYear = endYear;
    }
    
    
    
    public String getCountryCode() {
        return countryCode;
    }



    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }



    public String getStartYear() {
        return startYear;
    }



    public void setStartYear(String startYear) {
        this.startYear = startYear;
    }



    public String getEndYear() {
        return endYear;
    }



    public void setEndYear(String endYear) {
        this.endYear = endYear;
    }



    public abstract DataResultObject perform();

}
