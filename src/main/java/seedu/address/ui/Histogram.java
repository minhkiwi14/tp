package seedu.address.ui;

import java.util.Arrays;

import javafx.collections.FXCollections;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.layout.Region;

public class Histogram extends UiPart<Region> {

    private static final String FXML = "Histogram.fxml";

    private final BarChart<Number, Number> barChart;

    public Histogram() {
        super(FXML);

        // Initialise the chart
        NumberAxis xAxis = new NumberAxis();
        NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Grade");
        xAxis.setLabel("Frequency");

        barChart = new BarChart<>(xAxis, yAxis);
        barChart.setTitle("Grades of Students in Current List");
        barChart.setLegendVisible(false);
        barChart.setCategoryGap(0);
        barChart.setBarGap(1);
        
        // Add the chart to the root
        getRoot().getChildren().add(barChart);
    }
    
}
