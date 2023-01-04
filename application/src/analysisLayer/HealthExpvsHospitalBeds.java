package analysisLayer;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import dataFetchLayer.Fetcher;
import dataFetchLayer.WorldBankDataFetcher;
import utilitiesLayer.DataResultObject;

public class HealthExpvsHospitalBeds extends Analysis {
    
    private final String HEALTH_EXP_INDICATOR = "SH.XPD.CHEX.PC.CD";
    private final String HOSPITAL_BEDS_INDICATOR = "SH.MED.BEDS.ZS";
     Map<String, Double> healthExpResult;
     Map<String, Double> hospitalBedsResult;
     Map<String, Double> ratioResult = new HashMap<String, Double>();
     
     public HealthExpvsHospitalBeds(String countryCode, String startYear, String endYear) {
         super(countryCode, startYear, endYear);
     }
     
     public DataResultObject perform() {  
         DataResultObject data = new DataResultObject();
         
         Fetcher fetcher = new WorldBankDataFetcher(this.getCountryCode(), HEALTH_EXP_INDICATOR, this.getStartYear(), this.getEndYear());
         healthExpResult = fetcher.GetData();
         fetcher.setIndicator(HOSPITAL_BEDS_INDICATOR);
         hospitalBedsResult = fetcher.GetData();
         
         for (Map.Entry<String, Double> entry : healthExpResult.entrySet()) {
             String key = entry.getKey();
             
             double result = (double)( healthExpResult.get(key).intValue() / hospitalBedsResult.get(key).intValue());
             ratioResult.put(key, result);
             System.out.println("Result " + result);
         }
         
         data.setChartName("Current health expenditure vs Hospital beds");
         TreeMap<String, Double> sorted = new TreeMap<>(ratioResult);
         data.setDataResult(sorted);
         
         return data;
     }
}
