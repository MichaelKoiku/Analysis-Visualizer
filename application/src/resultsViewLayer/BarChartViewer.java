package resultsViewLayer;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

public class BarChartViewer extends Viewer {
    private Subject subject;

    public BarChartViewer(Subject subject) {
        super(subject);
        this.subject = subject;
    }

    public void draw(JPanel panel) {

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for (Map.Entry<String, Double> entry : subject.getData().getDataResult().get(0).entrySet()) {
            System.out.println(entry.getKey());
            dataset.addValue(entry.getValue(), subject.getData().getChartName(), entry.getKey());
        }

        DefaultCategoryDataset dataset2 = new DefaultCategoryDataset();
        if (subject.getData().getDataResult().size() > 1) {
            for (Map.Entry<String, Double> entry : subject.getData().getDataResult().get(1).entrySet()) {
                dataset2.addValue(entry.getValue(), subject.getData().getChartName(), entry.getKey());
            }
        }

        if (subject.getData().getDataResult().size() > 2) {

        }

        CategoryPlot plot = new CategoryPlot();
        BarRenderer barrenderer = new BarRenderer();
        BarRenderer barrenderer2 = new BarRenderer();

        plot.setDataset(0, dataset);
        plot.setRenderer(0, barrenderer);
        CategoryAxis domainAxis = new CategoryAxis("Year");
        plot.setDomainAxis(domainAxis);
        plot.setRangeAxis(new NumberAxis(""));

        ///
        if (subject.getData().getDataResult().size() > 1) {
            plot.setDataset(1, dataset2);
            plot.setRenderer(1, barrenderer2);
            plot.setRangeAxis(1, new NumberAxis("US$"));
        }
        ///

        plot.mapDatasetToRangeAxis(0, 0);

        ///
        if (subject.getData().getDataResult().size() > 1) {
            plot.mapDatasetToRangeAxis(1, 1); // 2nd dataset to 2nd y-axis
        }
        ///

        JFreeChart barChart = new JFreeChart(subject.getData().getChartName(),
                new Font("Serif", java.awt.Font.BOLD, 18), plot, true);

        ChartPanel chartPanel = new ChartPanel(barChart);
        chartPanel.setPreferredSize(new Dimension(400, 300));
        chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        chartPanel.setBackground(Color.white);
        panel.add(chartPanel);

    }

}
