package analysisLayer;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import dataFetchLayer.Fetcher;
import dataFetchLayer.WorldBankDataFetcher;
import utilitiesLayer.DataResultObject;

public class HealthCareMortalityRate extends Analysis {
	private final String Problems_in_accessing_health_care_Indicator = "AG.LND.FRST.ZS";
    private final String Mortality_rate_infant_Indicator = "SP.DYN.IMRT.IN";
    Map<String, Double> Forest_area_Result;
    Map<String, Double> Mortality_rate_infant_Result;
    
    public HealthCareMortalityRate(String countryCode, String startYear, String endYear) {
        super(countryCode, startYear, endYear);
    }

    
  //Values are hardcorded for now but we will pass values gotten from UserSelectionObject in UserSelectionFacade
  //Fetcher will also be sigleton in next delivery, so we can have 1 Instance of WordBankfetcher
    public DataResultObject perform() {
        DataResultObject data = new DataResultObject();
        
        Fetcher fetcher = new WorldBankDataFetcher(this.getCountryCode(), Problems_in_accessing_health_care_Indicator, this.getStartYear(), this.getEndYear());
        Forest_area_Result = fetcher.GetData();
        fetcher.setIndicator(Mortality_rate_infant_Indicator);
        Mortality_rate_infant_Result = fetcher.GetData();

        System.out.println("Data = " + Arrays.asList(Forest_area_Result));
        System.out.println("Data = " + Arrays.asList(Mortality_rate_infant_Result));
        
        data.setChartName("Health Access vs Mortality Rate");
        TreeMap<String, Double> sorted = new TreeMap<>(Forest_area_Result);
        data.setDataResult(sorted);
        sorted.clear();
        sorted.putAll(Mortality_rate_infant_Result);
        data.setDataResult(sorted);
        
        return data;
    }
}
