package resultsViewLayer;

import java.awt.Color;
import java.awt.Dimension;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class ReportViewer extends Viewer {
    private Subject subject;

    public ReportViewer(Subject subject) {
        super(subject);
        this.subject = subject;
    }

    public void draw(JPanel panel) {
        JTextArea report = new JTextArea();
        report.setEditable(false);
        report.setPreferredSize(new Dimension(400, 300));
        report.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        report.setBackground(Color.white);
        StringBuilder reportMessage  = new StringBuilder();
        reportMessage.append(subject.getData().getChartName() + "\n" + "==============================\n");
        
        for (Map.Entry<String, Double> entry : subject.getData().getDataResult().get(0).entrySet()) {
            reportMessage.append("Year " + entry.getKey() + ":\n \t" + subject.getData().getChartName() + " => " + entry.getValue() + "\n\n");
        }

        report.setText(reportMessage.toString());
        JScrollPane outputScrollPane = new JScrollPane(report);
        panel.add(outputScrollPane);
        System.out.println("Report View done");
    }

}
