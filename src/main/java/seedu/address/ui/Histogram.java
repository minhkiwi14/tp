package seedu.address.ui;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.Region;
import seedu.address.model.person.Grade;

public class Histogram extends UiPart<Region> {

    private static final String FXML = "Histogram.fxml";

    @FXML
    private BarChart<String, Number> barChart;

    @FXML
    private CategoryAxis xAxis;

    @FXML
    private NumberAxis yAxis;

    public Histogram() {
        super(FXML);

        // Initialise the chart
        xAxis = new CategoryAxis();
        yAxis = new NumberAxis();

        xAxis.setLabel("Grade");
        xAxis.setLabel("Frequency");

        // Configure x-axis
        xAxis.setCategories(FXCollections.observableArrayList(
                "0-9", "10-19", "20-29", "30-39", "40-49", "50-59", "60-69", "70-79", "80-89", "90-100"));

        barChart.setTitle("Grades of Students in Current List");
        barChart.setLegendVisible(false);
        barChart.setCategoryGap(0);
        barChart.setBarGap(1);
    }
    
    public void updateHistogram(Grade[] data, int numBins) {
        if (data == null || data.length == 0) {
            barChart.getData().clear();
            return;
        }

        // Calculate number of bins from 0 to 100
        int binWidth = 100 / numBins;
        int[] bins = new int[numBins];

        // Populate bins
        for (Grade grade : data) {
            int binIndex = (grade.grade) / binWidth;
            if (binIndex >= numBins) {
                binIndex = numBins - 1;
            }
            bins[binIndex]++;
        }

        XYChart.Series<String, Number> series = new XYChart.Series<>();

        // Add data to series
        for (int i = 0; i < numBins; i++) {
            int lowerBound = i * binWidth;
            int upperBound = (i + 1) * binWidth - 1;
            String binLabel = lowerBound + "-" + upperBound;
            series.getData().add(new XYChart.Data<>(binLabel, bins[i]));
        }

        barChart.getData().clear();
        barChart.getData().add(series);
    }
}
