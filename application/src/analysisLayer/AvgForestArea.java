package analysisLayer;

import java.util.HashMap;
import java.util.Map;

import dataFetchLayer.Fetcher;
import dataFetchLayer.WorldBankDataFetcher;
import utilitiesLayer.DataResultObject;

public class AvgForestArea extends Analysis {
    private final String forestAreaIndicator = "AG.LND.FRST.ZS";
    Map<String, Double> forestAreaIndicatorResult;
    Map<String, Double> Result = new HashMap<String, Double>();
    
    public AvgForestArea(String countryCode, String startYear, String endYear) {
        super(countryCode, startYear, endYear);
    }

    
    public DataResultObject perform() {
        DataResultObject data = new DataResultObject();
        
        int result = 0;
        Fetcher fetcher = new WorldBankDataFetcher(this.getCountryCode(), forestAreaIndicator, this.getStartYear(), this.getEndYear());
        forestAreaIndicatorResult = fetcher.GetData();


        for (Map.Entry<String, Double> entry : forestAreaIndicatorResult.entrySet()) {
            String key = entry.getKey();
            result += forestAreaIndicatorResult.get(key);
        }
        
        System.out.print("Map Size " + forestAreaIndicatorResult.size() + "\n");
        Result.put("Used", (result / forestAreaIndicatorResult.size()) / 100.0);
        Result.put("Not used", 1 - (result/forestAreaIndicatorResult.size() / 100.0));
        
        data.setChartName("Forest Area");
        data.setDataResult(Result);
        
        return data;
    }
}

