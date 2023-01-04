package resultsViewLayer;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import utilitiesLayer.DataResultObject;

public class Subject {
    //Here the Observer Design Pattern is being used
    //This class is the Subject/Publisher
    
    private DataResultObject data;
    private List<Viewer> observers = new ArrayList<>();
    
    public void attach(Viewer observer) {
        observers.add(observer);
    }
    
    public void detach(Viewer observer) {
        observers.remove(observer);
    }
    
    public void notifyObservers(JPanel panel) {
        for (Viewer observer : observers) {
            observer.draw(panel);
        }
    }
    
    public void setData(DataResultObject data) {
        this.data = data;
    }
    
    public DataResultObject getData() {
        return this.data;
    }
}
