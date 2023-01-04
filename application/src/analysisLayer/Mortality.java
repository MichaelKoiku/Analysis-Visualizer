package analysisLayer;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import dataFetchLayer.Fetcher;
import dataFetchLayer.WorldBankDataFetcher;
import utilitiesLayer.DataResultObject;

public class Mortality extends Analysis {
    private final String MORTALITY_INDICATOR = "SP.DYN.IMRT.IN";
    Map<String, Double> mortalityResult = new HashMap<String, Double>();
    
    
    public Mortality(String countryCode, String startYear, String endYear) {
        super(countryCode, startYear, endYear);
    }
    
    public DataResultObject perform() {  
        DataResultObject data = new DataResultObject();
        
        Fetcher fetcher = new WorldBankDataFetcher(this.getCountryCode(), MORTALITY_INDICATOR, this.getStartYear(), this.getEndYear());
        mortalityResult = fetcher.GetData();
   
        
        data.setChartName("Mortality rate");
        TreeMap<String, Double> sorted = new TreeMap<>(mortalityResult);
        data.setDataResult(sorted);
        
        return data;
    } 
    
}
