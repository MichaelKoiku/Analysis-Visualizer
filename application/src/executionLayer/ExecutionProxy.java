package executionLayer;

import java.util.List;

import javax.swing.JPanel;

import utilitiesLayer.UserSelectionObject;

public class ExecutionProxy {
    //Here the Proxy & Singleton Design Pattern are being used
    private static ExecutionProxy instance;
    
    private ExecutionProxy() {}
    
    public static ExecutionProxy getInstance() {
        if(instance == null) {
            instance = new ExecutionProxy();
        }
        return instance;
    }
    
    public void execute(UserSelectionObject userSelection, JPanel panel) {
        //1. Create the set up for the Facade
        String country = userSelection.getSelectedCountry();
        String yearStart = userSelection.getStartYear(); 
        String yearEnd = userSelection.getEndYear();
        String analysis = userSelection.getSelectedAnalysis();
        List<String> view = userSelection.getSelectedView();
        String source = userSelection.getSelectedSource();
        
        System.out.println("System Selection: " + country + " " + yearStart + " " + yearEnd + " " + analysis);
        
        
        //2. Create and Invoke the facade
        ExecutionFacade facade = new ExecutionFacade(country, yearStart, yearEnd, analysis, view, source);
        facade.perform(panel);
    }

}
