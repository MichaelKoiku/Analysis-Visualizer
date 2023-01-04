package utilitiesLayer;

import java.util.ArrayList;
import java.util.List;

/**
 * User selection object class to store the user selection.
 */
public class UserSelectionObject {
    private String selectedCountry;
    private String selectedYearStart;
    private String selectedYearEnd;
    private List<String> selectedView = new ArrayList<String>();
    private String selectedAnalysis; // Selected Items from UI are strings, We will use hash table to select the Analysis based on strategy.
    private String selectedSource;

    /**
     * Default constructor for the user selection object.
     */
    public UserSelectionObject() {}

    /**
     * Constructor for the user selection object.
     * 
     * @param selectedCountry   The selected country.
     * @param selectedYearStart The selected start year.
     * @param selectedYearEnd   The selected end year.
     * @param selectedView      The selected view.
     * @param selectedAnalysis  The selected analysis.
     */
    public UserSelectionObject(String country, String yearStart, String yearEnd, List<String> view, String analysis, String source) { // Note we will add String view argument to constructor later on
        this.selectedCountry = country;
        this.selectedYearStart = yearStart;
        this.selectedYearEnd = yearEnd;
        this.selectedView = view;
        this.selectedAnalysis = analysis;
        this.selectedSource = source;
    }

    public String getSelectedCountry() {
        return selectedCountry;
    }

    public void setSelectedCountry(String selectedCountry) {
        this.selectedCountry = selectedCountry;
    }

    public String getStartYear() {
        return selectedYearStart;
    }

    public void setStartYear(String selectedYearStart) {
        this.selectedYearStart = selectedYearStart;
    }

    public String getEndYear() {
        return selectedYearEnd;
    }

    public void setEndYear(String selectedYearEnd) {
        this.selectedYearEnd = selectedYearEnd;
    }

    public List<String> getSelectedView() {
        return selectedView;
    }
    
    public void removeView(String selectedView) {
        this.selectedView.remove(selectedView);
    }
    
    public void clearViews() {
        this.selectedView.clear();
    }

    public void addView(String selectedView) {
        if(!this.selectedView.contains(selectedView)) {
            this.selectedView.add(selectedView); 
        }
    }

    public String getSelectedAnalysis() {
        return selectedAnalysis;
    }

    public void setSelectedAnalysis(String selectedAnalysis) {
        this.selectedAnalysis = selectedAnalysis;
    }

    public void setSelectedSource(String source) {
        this.selectedSource = source;
    }

    public String getSelectedSource() {
        return selectedSource;
    }

}