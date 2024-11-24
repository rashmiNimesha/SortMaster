package org.example.sortingapplication.controller;

import javafx.application.Platform;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

// Handles measuring and displaying how fast different sorting methods work
public class PerformanceController {
    // UI elements
    private BarChart<String, Number> barChart;
    private VBox resultsBox;
    private final SortingController sortingController = new SortingController();

    // Store how long each sorting method takes
    Long quickSortTime;
    Long mergeSortTime;
    Long heapSortTime;
    Long shellSortTime;
    Long insertionSortTime;

    // Connect the bar chart
    public void setBarChart(BarChart<String, Number> barChart) {
        this.barChart = barChart;
    }

    // Connect the text results box
    public void setResultsBox(VBox resultsBox) {
        this.resultsBox = resultsBox;
    }

    // Main method that handles the performance analysis
    // Takes data from a table and a specific column
    public void handleAnalyze(List<ObservableList<String>> tableData, int columnIndex) {
        // Verify display components
        if (barChart == null || resultsBox == null) {
            showError("Initialization Error", "BarChart or ResultsBox is not initialized.");
            return;
        }

        // Clear out any old results
        barChart.getData().clear();
        resultsBox.getChildren().clear();

        // Get the list of numbers to sort from selected column
        List<Double> numericData = new ArrayList<>();

        for (ObservableList<String> row : tableData) {
            String cellValue = row.get(columnIndex);
            if (isValidNumericValue(cellValue)) {
                // Convert text to number, removing any extra characters
                numericData.add(Double.parseDouble(cellValue.replaceAll("[^\\d.-]", "")));
            } else if (cellValue != null && !cellValue.isEmpty()) {
                // Show error for data that isn't a number
                showError("Invalid Data", "Column contains non-numeric or invalid data. Please correct the values.");
                return;
            }
        }

        // Validate selected numeric column isn't empty
        if (numericData.isEmpty()) {
            showError("No Numeric Data", "The selected column does not contain numeric data for analysis.");
            return;
        }

        // Run each sorting method and measure the time taken
        quickSortTime = sortingController.runQuickSort(new ArrayList<>(numericData));
        mergeSortTime = sortingController.runMergeSort(new ArrayList<>(numericData));
        heapSortTime = sortingController.runHeapSort(new ArrayList<>(numericData));
        shellSortTime = sortingController.runShellSort(new ArrayList<>(numericData));
        insertionSortTime = sortingController.runInsertionSort(new ArrayList<>(numericData));

        // Show the results in the chart
        Platform.runLater(() -> {
            addDataToChart("QuickSort", quickSortTime);
            addDataToChart("MergeSort", mergeSortTime);
            addDataToChart("HeapSort", heapSortTime);
            addDataToChart("ShellSort", shellSortTime);
            addDataToChart("InsertionSort", insertionSortTime);
        });
    }

    // Add one sorting method's results to the chart and results box
    private void addDataToChart(String algorithm, long time) {
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName(algorithm);
        series.getData().add(new XYChart.Data<>(algorithm, time));
        barChart.getData().add(series);
        resultsBox.getChildren().add(new Label(algorithm + " - " + time + " ns"));
    }

    // Check if a text value is actually a number
    private boolean isValidNumericValue(String value) {
        if (value == null || value.trim().isEmpty()) {
            return false;
        }

        // Check if the text matches the pattern of a number
        // (can be negative and can have decimal points)
        String numericPattern = "^-?\\d*(\\.\\d+)?$";
        return value.matches(numericPattern);
    }

    // Show an error message to the user
    private void showError(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}