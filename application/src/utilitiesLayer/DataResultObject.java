package utilitiesLayer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DataResultObject {
    private String chartName;
    private ArrayList<Map<String, Double>> dataResultList = new ArrayList<Map<String, Double>>();
    
    public String getChartName() {
        return chartName;
    }
    public void setChartName(String chartName) {
        this.chartName = chartName;
    }
    public List<Map<String, Double>> getDataResult() {
        return dataResultList;
    }
    public void setDataResult(Map<String, Double> dataResult) {
        this.dataResultList.add(dataResult);
    }

}
