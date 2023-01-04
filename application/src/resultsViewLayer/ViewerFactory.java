package resultsViewLayer;

public class ViewerFactory {
    // Here the Factory Design Pattern is being used

    private Viewer viewer;

    public Viewer createView(String viewer, Subject subject) {

        if (viewer.equals("Pie Chart")) {
            this.viewer = new PieChartViewer(subject);
        }

        if (viewer.equals("Line Chart")) {
            this.viewer = new LineChartViewer(subject);
        }

        if (viewer.equals("Bar Chart")) {
            this.viewer = new BarChartViewer(subject);
        }

        if (viewer.equals("Scatter Chart")) {
            this.viewer = new ScatterChartViewer(subject);
        }

        if (viewer.equals("Report")) {
            this.viewer = new ReportViewer(subject);
        }

        return this.viewer;
    }

}
