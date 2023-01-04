package analysisLayer;

import java.util.HashMap;
import java.util.Map;

import dataFetchLayer.Fetcher;
import dataFetchLayer.WorldBankDataFetcher;
import utilitiesLayer.DataResultObject;

public class AvgGovExpOnEducation extends Analysis{
	private final String govExpenditureOnEducationIndicator = "SE.XPD.TOTL.GD.ZS";
    Map<String, Double> govExpenditureOnEducationResult;
    Map<String, Double> Result = new HashMap<String, Double>();

    public AvgGovExpOnEducation(String countryCode, String startYear, String endYear) {
        super(countryCode, startYear, endYear);
    }
   
    public DataResultObject perform() {
        DataResultObject data = new DataResultObject();
        
        Fetcher fetcher = new WorldBankDataFetcher(this.getCountryCode(), govExpenditureOnEducationIndicator, this.getStartYear(), this.getEndYear());
        govExpenditureOnEducationResult = fetcher.GetData();

        int result = 0;
        for (Map.Entry<String, Double> entry : govExpenditureOnEducationResult.entrySet()) {
            String key = entry.getKey();
            result += govExpenditureOnEducationResult.get(key);
        }

        System.out.print("Map Size " + govExpenditureOnEducationResult.size() + "\n");
        Result.put("On Education", (result / govExpenditureOnEducationResult.size()) / 100.0);
        Result.put("On other purposes", 1 - (result / govExpenditureOnEducationResult.size() / 100.0));

        data.setChartName("Government Expenditure");
        data.setDataResult(Result);
        
        return data;
        
    }
} 