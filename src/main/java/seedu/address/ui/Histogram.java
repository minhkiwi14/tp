package seedu.address.ui;

import java.util.Arrays;
import java.util.List;

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
    private static final String TO_SEPARATOR = "-";

    private static final int LOWER_BOUND_FREQUENCY = 0;
    private static final int DEFAULT_MAX_FREQUENCY = 5;
    private static final int TICK_UNIT = 1;
    private static final int MAX_GRADE = 100;

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
        xAxis.setCategories(FXCollections.observableArrayList("0-9", "10-19", "20-29", "30-39"
                , "40-49", "50-59", "60-69", "70-79", "80-89", "90-100"));

        yAxis.setLowerBound(LOWER_BOUND_FREQUENCY);
        yAxis.setUpperBound(DEFAULT_MAX_FREQUENCY);
        yAxis.setTickUnit(TICK_UNIT);
        yAxis.setMinorTickCount(0);

        barChart.setTitle("Grades of Students in Current List");
        barChart.setLegendVisible(false);
        barChart.setCategoryGap(0);
        barChart.setBarGap(1);
    }
    
    public void updateHistogram(List<Grade> data, int numBins) {
        if (data == null || data.size() == 0) {
            barChart.getData().clear();
            return;
        }

        int binWidth = MAX_GRADE / numBins;
        int[] bins = new int[numBins];

        bins = populateBins(data, bins, numBins, binWidth);
        setMaxFrequency(bins);
        XYChart.Series<String, Number> series = addDataToSeries(bins, numBins, binWidth);
        refreshHistogram(series);
    }

    /**
     * Populates the bins with the Grade data.
     *
     * @param numBins number of bins to populate.
     * @param binWidth width of each bin.
     * @return bins populated with the Grade data.
     */
    private int[] populateBins(List<Grade> data, int[] bins, int numBins, int binWidth) {
        for (Grade grade : data) {
            int binIndex = (grade.grade) / binWidth;
            if (binIndex >= numBins) {
                binIndex = numBins - 1;
            }
            bins[binIndex]++;
        }

        return bins;
    }

    /**
     * Sets the maximum frequency of the histogram.
     *
     * @param bins bins of the histogram.
     */
    private void setMaxFrequency(int[] bins) {
        int maxFrequency = Arrays.stream(bins).max().orElse(DEFAULT_MAX_FREQUENCY);
        int upperBound = Math.max(DEFAULT_MAX_FREQUENCY, maxFrequency);

        yAxis.setLowerBound(LOWER_BOUND_FREQUENCY);
        yAxis.setUpperBound(upperBound);
        yAxis.setTickUnit(TICK_UNIT);
        yAxis.setMinorTickCount(0);
        yAxis.setAutoRanging(false);

        yAxis.layout();
        barChart.layout();
    }

    /**
     * Adds data to the series.
     *
     * @param bins bins of the histogram.
     * @param numBins number of bins.
     * @param binWidth width of each bin.
     */
    private XYChart.Series<String, Number> addDataToSeries(int[] bins, int numBins, int binWidth) {
        XYChart.Series<String, Number> series = new XYChart.Series<>();

        for (int i = 0; i < numBins; i++) {
            int lowerBound = i * binWidth;
            int upperBound = (i + 1) * binWidth - 1;
            // Handle different upperbound for the last bin
            if (upperBound == 99) {
                upperBound = 100;
            }

            String binLabel = lowerBound + TO_SEPARATOR + upperBound;
            series.getData().add(new XYChart.Data<>(binLabel, bins[i]));
        }

        return series;
    }

    /**
     * Refreshes the histogram with the new series.
     */
    private void refreshHistogram(XYChart.Series<String, Number> series) {
        barChart.getData().clear();
        barChart.getData().add(series);
    }
}
