package analysisLayer;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import dataFetchLayer.Fetcher;
import dataFetchLayer.WorldBankDataFetcher;
import utilitiesLayer.DataResultObject;

public class TotalPopulation extends Analysis {
    private final String TOTAL_POPULATION_INDICATOR = "SP.POP.TOTL";
    Map<String, Double> populationResult = new HashMap<String, Double>();
    
    
    public TotalPopulation(String countryCode, String startYear, String endYear) {
        super(countryCode, startYear, endYear);
    }
    
    public DataResultObject perform() {  
        DataResultObject data = new DataResultObject();
        
        Fetcher fetcher = new WorldBankDataFetcher(this.getCountryCode(), TOTAL_POPULATION_INDICATOR, this.getStartYear(), this.getEndYear());
        populationResult = fetcher.GetData();
   
        
        data.setChartName("Total Population");
        TreeMap<String, Double> sorted = new TreeMap<>(populationResult);
        data.setDataResult(sorted);
        
        return data;
    } 
}
