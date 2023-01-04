package resultsViewLayer;

import java.awt.Color;
import java.awt.Dimension;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.util.TableOrder;
import org.jfree.data.category.DefaultCategoryDataset;

public class PieChartViewer extends Viewer {
    private Subject subject;
    
    
    
    public PieChartViewer(Subject subject) {
        super(subject);
        this.subject = subject;
    }

    public void draw(JPanel panel) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        
        for (Map.Entry<String, Double> entry : subject.getData().getDataResult().get(0).entrySet()) {
            dataset.addValue(entry.getValue(), entry.getKey(), subject.getData().getChartName());
        }
        
        JFreeChart pieChart = ChartFactory.createMultiplePieChart(subject.getData().getChartName(), dataset, TableOrder.BY_COLUMN, true, true, false);

        ChartPanel chartPanel = new ChartPanel(pieChart);
        chartPanel.setPreferredSize(new Dimension(400, 300));
        chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        chartPanel.setBackground(Color.white);
        panel.add(chartPanel);
    }

}
