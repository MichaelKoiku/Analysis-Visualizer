package analysisLayer;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import dataFetchLayer.Fetcher;
import dataFetchLayer.WorldBankDataFetcher;
import utilitiesLayer.DataResultObject;

public class CO2vsGDP extends Analysis {
    
	private final String CO2_INDICATOR = "EN.ATM.CO2E.PC";
	private final String GDP_INDICATOR = "NY.GDP.PCAP.CD";
	 Map<String, Double> co2Result;
     Map<String, Double> gdpResult;
     Map<String, Double> ratioResult = new HashMap<String, Double>();
     
     public CO2vsGDP(String countryCode, String startYear, String endYear) {
         super(countryCode, startYear, endYear);
     }

	public DataResultObject perform() {  
	    DataResultObject data = new DataResultObject();
	    
		Fetcher fetcher = new WorldBankDataFetcher(this.getCountryCode(), CO2_INDICATOR, this.getStartYear(), this.getEndYear());
		co2Result = fetcher.GetData();
		fetcher.setIndicator(GDP_INDICATOR);
		gdpResult = fetcher.GetData();
		
		for (Map.Entry<String, Double> entry : co2Result.entrySet()) {
		    String key = entry.getKey();
		    
		    double result = (co2Result.get(key) * 1000.0 / gdpResult.get(key));
		    ratioResult.put(key, result);
		    System.out.println("Result " + result);
		}
		
		data.setChartName("CO2 emissions vs GDP per capita");
		TreeMap<String, Double> sorted = new TreeMap<>(ratioResult);
		data.setDataResult(sorted);
		
		return data;
	}
}
