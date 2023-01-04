package analysisLayer;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import dataFetchLayer.Fetcher;
import dataFetchLayer.WorldBankDataFetcher;
import utilitiesLayer.DataResultObject;

public class GDPperCapita extends Analysis {
    private final String GDP_PER_CAP_INDICATOR = "NY.GDP.PCAP.CD";
    Map<String, Double> gdpResult = new HashMap<String, Double>();
    
    
    public GDPperCapita(String countryCode, String startYear, String endYear) {
        super(countryCode, startYear, endYear);
    }
    
    public DataResultObject perform() {  
        DataResultObject data = new DataResultObject();
        
        Fetcher fetcher = new WorldBankDataFetcher(this.getCountryCode(), GDP_PER_CAP_INDICATOR, this.getStartYear(), this.getEndYear());
        gdpResult = fetcher.GetData();
   
        
        data.setChartName("GDP per Capita (US$)");
        TreeMap<String, Double> sorted = new TreeMap<>(gdpResult);
        data.setDataResult(sorted);
        
        return data;
    } 
}
