package analysisLayer;


public class AnalysisFactory {
    //Here the Factory Design Pattern is used
    
    private String analysisString;
    private Analysis analysis;
    private String countryCode;
    private String startYear;
    private String endYear;
    
    
    public AnalysisFactory(String analysis, String countryCode, String startYear, String endYear) {
        this.analysisString = analysis;
        this.countryCode = countryCode;
        this.startYear = startYear;
        this.endYear = endYear;
    }
    
    public Analysis createAnalysis() {
        if(this.analysisString.equals("Average Forest Area")) {
            this.analysis =  new AvgForestArea(this.countryCode, this.startYear, this.endYear);
        }
        
        if(this.analysisString.equals("Average Government Expenditure on Education")) {
            this.analysis =  new AvgGovExpOnEducation(this.countryCode, this.startYear, this.endYear);
        }
        
        if(this.analysisString.equals("CO2 vs GDP")) {
            this.analysis =  new CO2vsGDP(this.countryCode, this.startYear, this.endYear);
        }
        
        if(this.analysisString.equals("Health care vs Mortality rate")) {
            this.analysis =  new HealthCareMortalityRate(this.countryCode, this.startYear, this.endYear);
        }
        
        if(this.analysisString.equals("Health expenditure vs Hospital beds")) {
            this.analysis =  new HealthExpvsHospitalBeds(this.countryCode, this.startYear, this.endYear);
        }
       
        if(this.analysisString.equals("Total popuplation")) {
            this.analysis =  new TotalPopulation(this.countryCode, this.startYear, this.endYear);
        }
       
        if(this.analysisString.equals("GDP per capita")) {
            this.analysis =  new GDPperCapita(this.countryCode, this.startYear, this.endYear);
        }
       
        if(this.analysisString.equals("Mortality")) {
            this.analysis =  new Mortality(this.countryCode, this.startYear, this.endYear);
        }
       
        if (this.analysisString.equals("Display Covid Cases")) {
            this.analysis = new Covid(this.countryCode, this.startYear, this.endYear);
        }
        
        return this.analysis;
    }
}
