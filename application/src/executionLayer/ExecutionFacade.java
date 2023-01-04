package executionLayer;

import java.util.List;

import javax.swing.JPanel;

import analysisLayer.AnalysisFactory;
import analysisLayer.Context;
import resultsViewLayer.SubjectFactory;
import resultsViewLayer.ViewerFactory;
import resultsViewLayer.Subject;

public class ExecutionFacade {
    //Here the Facade Design Pattern is being used
    
    private String country;
    private String yearStart; 
    private String yearEnd;
    private String analysis;
    private List<String> view;
    private String source;
    
    public ExecutionFacade(String country, String yearStart, String yearEnd, String analysis, List<String> view, String source) {
        this.country = country;
        this.yearStart = yearStart;
        this.yearEnd = yearEnd;
        this.analysis = analysis;
        this.view = view;
        this.source = source;
    }

    public void perform(JPanel panel) {
        //1. Create the Subject for the Analysis and Viewers
        System.out.println("Step 1 begin");
        SubjectFactory subjectFactory = new SubjectFactory();
        Subject subject = subjectFactory.createSubject();
        System.out.println("Step 1 complete");
        
        //2. Create the Viewers for the Subject & attach the Viewers to the Subject
        System.out.println("Step 2 begin");
        ViewerFactory viewerFactory = new ViewerFactory();
        //For loop
        for(int i = 0; i < view.size(); i++) {
            viewerFactory.createView(this.view.get(i), subject);
        }
        System.out.println("Step 2 complete");
        
        
        //3. Do analysis and set the Data of the subject
        System.out.println("Step 3 begin");
        
        if(this.source.equals("Open Covid")) {
        	AnalysisFactory factory = new AnalysisFactory(this.analysis, this.country, this.yearStart, this.yearEnd);
            Context context = new Context();
            context.setStrategy(factory.createAnalysis());
            subject.setData(context.executeAnalysis());
        }
        else {
        	AnalysisFactory factory = new AnalysisFactory(this.analysis, this.country, this.yearStart, this.yearEnd);
            Context context = new Context();
            context.setStrategy(factory.createAnalysis());
            subject.setData(context.executeAnalysis());
        }
        
//        AnalysisFactory factory = new AnalysisFactory(this.analysis, this.country, this.yearStart, this.yearEnd);
//        Context context = new Context();
//        context.setStrategy(factory.createAnalysis());
//        subject.setData(context.executeAnalysis());
        System.out.println("Step 3 complete");
        
        //4. Use the subject to notify the viewers
        System.out.println("Step 4 begin");
        subject.notifyObservers(panel);
        System.out.println("Step 4 end");
    }
}
