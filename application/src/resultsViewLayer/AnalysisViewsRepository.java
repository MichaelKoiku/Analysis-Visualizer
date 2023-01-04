package resultsViewLayer;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

/**
 * This class is the analysis view repository. It is used to store the analysis views. 
 */
public class AnalysisViewsRepository {
    //Here the Singleton Design Pattern is being used
    
    private static AnalysisViewsRepository instance;
    private Map<String, Vector<String>> repository = new HashMap<String, Vector<String>>();

    private AnalysisViewsRepository() {}
    
    /**
     * This method is used to get the instance of the class.
     * @return instance
     */
    public static AnalysisViewsRepository getInstance() {
        if(instance == null) {
            instance = new AnalysisViewsRepository();
        }
        return instance;
    }
    
    public void addViews(String analysis, Vector<String> views) {
        repository.put(analysis, views);
    }
    
    public void removeViews(String analysis, Vector<String> views) {
        repository.put(analysis, views);
    }
    
    public Vector<String> getViews(String analysis) {
            return repository.get(analysis);
    }
    
    public void intializeWBRepo() {
        Vector<String> piechartviewsNames = new Vector<String>();
        Vector<String> seriesviewsNames = new Vector<String>();
        
        // Add the pie chart views for the pie chart only analysis
        piechartviewsNames.add("Pie Chart"); 
        
        // Add the series graph views for the series only graph analysis
        seriesviewsNames.add("Line Chart"); // Add the line chart view to the vector
        seriesviewsNames.add("Bar Chart"); // Add the bar chart view to the vector
        seriesviewsNames.add("Scatter Chart"); // Add the scatter chart view to the vector
        seriesviewsNames.add("Report");
        
        //Add to Map repo
        repository.put("Average Forest Area", piechartviewsNames);
        repository.put("Average Government Expenditure on Education", piechartviewsNames);
        
        repository.put("CO2 vs GDP", seriesviewsNames);
        repository.put("Health care vs Mortality rate", seriesviewsNames);
        repository.put("Health expenditure vs Hospital beds", seriesviewsNames);
        
        repository.put("Total popuplation", seriesviewsNames);
        repository.put("GDP per capita", seriesviewsNames);
        repository.put("Mortality", seriesviewsNames);
    }

    public void intializeOCRepo() {
        Vector<String> chartviewsNames = new Vector<String>();
        
        // Add the pie chart views for the pie chart only analysis
        chartviewsNames.add("Pie Chart"); 

        // Add the series graph views for the series only graph analysis
        chartviewsNames.add("Line Chart"); // Add the line chart view to the vector
        chartviewsNames.add("Bar Chart"); // Add the bar chart view to the vector
        chartviewsNames.add("Scatter Chart"); // Add the scatter chart view to the vector
        chartviewsNames.add("Report");
        
        // Add to Map repo
        repository.put("Display Covid Cases", chartviewsNames);
    }

}
