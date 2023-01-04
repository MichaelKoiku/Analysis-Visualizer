package analysisLayer;

import utilitiesLayer.DataResultObject;

public class Context {
    //This is a context that will set the appropriate Strategy (Analysis) to be executed
    
   private Analysis analysis;
   private DataResultObject data;
    
    
    public void setStrategy(Analysis analysis) {
        this.analysis = analysis;
    }
    
    public DataResultObject executeAnalysis() {
        this.data = this.analysis.perform();
        return this.data;
    }


}
