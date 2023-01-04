package resultsViewLayer;

public class SubjectFactory {
    //Here the Factory Design Pattern is being used
    
    private Subject subject;
    
    public Subject createSubject() {
        this.subject = new Subject();
            
        return this.subject;
    }

}
