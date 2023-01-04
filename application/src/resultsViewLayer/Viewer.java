package resultsViewLayer;

import javax.swing.JPanel;

public abstract class Viewer {
    //Here the Observer Design Pattern is being used
    //This class is the Subscriber class
    
    private Subject subject;
    
    public Viewer(Subject subject) {
        this.subject = subject;
        subject.attach(this);
    }
    
    public abstract void draw(JPanel panel);

}
