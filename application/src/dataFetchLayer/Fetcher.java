package dataFetchLayer;

import java.util.Map;

public abstract class Fetcher {
    protected String url;
    protected String country;
    protected String indicator; 
    protected String yearStart; 
    protected String yearEnd;
    
    public Fetcher(String url, String country, String indicator, String yearStart, String yearEnd) {
        this.url = url;
        this.country = country;
        this.indicator = indicator;
        this.yearStart = yearStart;
        this.yearEnd = yearEnd;
    }
    
    public Fetcher(String url, String country, String yearStart, String yearEnd) {
        this.url = url;
        this.country = country;
        this.yearStart = yearStart;
        this.yearEnd = yearEnd;
    }
    
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getIndicator() {
        return indicator;
    }

    public void setIndicator(String indicator) {
        this.indicator = indicator;
    }

    public String getYearStart() {
        return yearStart;
    }

    public void setYearStart(String yearStart) {
        this.yearStart = yearStart;
    }

    public String getYearEnd() {
        return yearEnd;
    }

    public void setYearEnd(String yearEnd) {
        this.yearEnd = yearEnd;
    }
    
    public abstract String BuildURL();
    
    public abstract Map<String, Double> GetData();
}