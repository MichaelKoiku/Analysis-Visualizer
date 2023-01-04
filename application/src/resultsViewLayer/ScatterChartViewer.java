package resultsViewLayer;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.time.Year;

public class ScatterChartViewer extends Viewer {
    private Subject subject;
    

    public ScatterChartViewer(Subject subject) {
        super(subject);
        this.subject = subject;
    }


    public void draw(JPanel panel) {
        TimeSeries series1 = new TimeSeries(subject.getData().getChartName());
        for (Map.Entry<String, Double> entry : subject.getData().getDataResult().get(0).entrySet()) {
            series1.add(new Year(Integer.parseInt(entry.getKey())), entry.getValue().doubleValue());
        }

        TimeSeriesCollection dataset = new TimeSeriesCollection();
        dataset.addSeries(series1);
        
        TimeSeriesCollection dataset2 = new TimeSeriesCollection();
        TimeSeries series2 = new TimeSeries("Second");
        
        if(subject.getData().getDataResult().size() > 1) {
            for (Map.Entry<String, Double> entry : subject.getData().getDataResult().get(1).entrySet()) {
                series2.add(new Year(Integer.parseInt(entry.getKey())), entry.getValue().doubleValue());
            }
            dataset2.addSeries(series2);
        }
        
        if(subject.getData().getDataResult().size() > 2) {
            
        }
        

        XYPlot plot = new XYPlot();
        XYItemRenderer itemrenderer1 = new XYLineAndShapeRenderer(false, true);
        XYItemRenderer itemrenderer2 = new XYLineAndShapeRenderer(false, true);

        plot.setDataset(0, dataset);
        plot.setRenderer(0, itemrenderer1);
        DateAxis domainAxis = new DateAxis("Year");
        plot.setDomainAxis(domainAxis);
        plot.setRangeAxis(new NumberAxis(""));
        
        //
        if(subject.getData().getDataResult().size() > 1) {
        plot.setDataset(1, dataset2);
        plot.setRenderer(1, itemrenderer2);
        plot.setRangeAxis(1, new NumberAxis("US$"));
        }
        //

        plot.mapDatasetToRangeAxis(0, 0);// 1st dataset to 1st y-axis
        //
        if(subject.getData().getDataResult().size() > 1) {
            plot.mapDatasetToRangeAxis(1, 1); // 2nd dataset to 2nd y-axis
        }
        //

        JFreeChart scatterChart = new JFreeChart(subject.getData().getChartName(),
                new Font("Serif", java.awt.Font.BOLD, 18), plot, true);

        ChartPanel chartPanel = new ChartPanel(scatterChart);
        chartPanel.setPreferredSize(new Dimension(400, 300));
        chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        chartPanel.setBackground(Color.white);
        panel.add(chartPanel);
    }

}
