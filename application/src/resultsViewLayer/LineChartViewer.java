package resultsViewLayer;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class LineChartViewer extends Viewer {
    private Subject subject;

    public LineChartViewer(Subject subject) {
        super(subject);
        this.subject = subject;
    }

    public void draw(JPanel panel) {
        XYSeries series1 = new XYSeries(subject.getData().getChartName());
        
        for (Map.Entry<String, Double> entry : subject.getData().getDataResult().get(0).entrySet()) {
            series1.add(Double.parseDouble(entry.getKey()), entry.getValue().doubleValue());
        }
        
        XYSeries series2 = new XYSeries("Second Key");
        if(subject.getData().getDataResult().size() > 1) {
            for (Map.Entry<String, Double> entry : subject.getData().getDataResult().get(1).entrySet()) {
                series2.add(Double.parseDouble(entry.getKey()), entry.getValue().doubleValue());
            }
        }
        
        if(subject.getData().getDataResult().size() > 2) {
            
        }
        
        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series1);
        
        if(subject.getData().getDataResult().size() > 1) {
        dataset.addSeries(series2);
        }
        
        JFreeChart chart = ChartFactory.createXYLineChart(subject.getData().getChartName(), "Year", "", dataset, PlotOrientation.VERTICAL, true, true, false);

        XYPlot plot = chart.getXYPlot();

        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesPaint(0, Color.RED);
        renderer.setSeriesStroke(0, new BasicStroke(2.0f));

        plot.setRenderer(renderer);
        plot.setBackgroundPaint(Color.white);

        plot.setRangeGridlinesVisible(true);
        plot.setRangeGridlinePaint(Color.BLACK);

        plot.setDomainGridlinesVisible(true);
        plot.setDomainGridlinePaint(Color.BLACK);

        chart.getLegend().setFrame(BlockBorder.NONE);

        chart.setTitle(new TextTitle(subject.getData().getChartName(), new Font("Serif", java.awt.Font.BOLD, 18)));

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(400, 300));
        chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        chartPanel.setBackground(Color.white);
        panel.add(chartPanel);
        
    }

}
