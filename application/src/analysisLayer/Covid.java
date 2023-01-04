package analysisLayer;

import java.util.Map;


import dataFetchLayer.OpenCovidFetcher;
import utilitiesLayer.DataResultObject;

public class Covid extends Analysis {
    Map<String, Double> results;

    public Covid(String countryCode, String startYear, String endYear) {
        super(countryCode, startYear, endYear);
    }
    
    public DataResultObject perform() {
        DataResultObject data = new DataResultObject();
        OpenCovidFetcher fetcher = new OpenCovidFetcher(this.getCountryCode(), this.getStartYear()+"-01-01", this.getEndYear()+"-12-31");
        results = fetcher.GetData();
        data.setChartName("Covid Cases");
        data.setDataResult(results);
             
        return data;

    }
}
