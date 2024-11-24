package org.example.sortingapplication.controller;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.collections.ObservableList;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.util.*;

// Handles measuring and displaying how fast different sorting methods work
public class PerformanceController {
    // UI elements
    private BarChart<String, Number> barChart;
    private VBox resultsBox;
    private final SortingController sortingController = new SortingController();

    // Store results with algorithm names and their times
    private record SortResult(String algorithm, long time) {}
    private List<SortResult> sortResults;

    Long quickSortTime;
    Long mergeSortTime;
    Long heapSortTime;
    Long shellSortTime;
    Long insertionSortTime;

    // Connect the bar chart
    public void setBarChart(BarChart<String, Number> barChart) {
        this.barChart = barChart;
    }

    // Connect the results display box
    public void setResultsBox(VBox resultsBox) {
        this.resultsBox = resultsBox;
    }

    // Main method that handles the performance analysis
    // Takes data from a table and a specific column
    public void handleAnalyze(List<ObservableList<String>> tableData, int columnIndex) {
        // Check if UI components are set
        if (barChart == null || resultsBox == null) {
            showError("Initialization Error", "BarChart or ResultsBox is not initialized.");
            return;
        }

        // Clear previous chart and results
        barChart.getData().clear();
        resultsBox.getChildren().clear();
        sortResults = new ArrayList<>();

        List<Double> numericData = new ArrayList<>();

        // Collect numeric data from the specified column
        for (ObservableList<String> row : tableData) {
            String cellValue = row.get(columnIndex);
            if (isValidNumericValue(cellValue)) {
                // Remove non-numeric characters and parse to double
                numericData.add(Double.parseDouble(cellValue.replaceAll("[^\\d.-]", "")));
            } else if (cellValue != null && !cellValue.isEmpty()) {
                // Show error if data is invalid
                showError("Invalid Data", "Column contains non-numeric or invalid data. Please correct the values.");
                return;
            }
        }

        // Check if there's any numeric data to sort
        if (numericData.isEmpty()) {
            showError("No Numeric Data", "The selected column does not contain numeric data for analysis.");
            return;
        }

        // Run different sorting algorithms and record their times
        quickSortTime = sortingController.runQuickSort(new ArrayList<>(numericData));
        mergeSortTime = sortingController.runMergeSort(new ArrayList<>(numericData));
        heapSortTime = sortingController.runHeapSort(new ArrayList<>(numericData));
        shellSortTime = sortingController.runShellSort(new ArrayList<>(numericData));
        insertionSortTime = sortingController.runInsertionSort(new ArrayList<>(numericData));

        // Add each sort result to the list
        sortResults.add(new SortResult("QuickSort", quickSortTime));
        sortResults.add(new SortResult("MergeSort", mergeSortTime));
        sortResults.add(new SortResult("HeapSort", heapSortTime));
        sortResults.add(new SortResult("ShellSort", shellSortTime));
        sortResults.add(new SortResult("InsertionSort", insertionSortTime));

        // Sort the results by time
        sortResults.sort(Comparator.comparingLong(SortResult::time));

       // Update UI
        Platform.runLater(() -> {
            // Add each result to the bar chart
            for (SortResult result : sortResults) {
                addDataToChart(result.algorithm(), result.time());
            }

            // Display the sorted results as cards
            displayResultCards();
        });
    }

    // Display each sort result as a card in the results box
    private void displayResultCards() {
        for (int i = 0; i < sortResults.size(); i++) {
            SortResult result = sortResults.get(i);
            addResultCard(result.algorithm(), result.time(), i + 1); // Add card with rank
        }
    }

    // Create and add a result card to the resultsBox
    private void addResultCard(String algorithm, long time, int rank) {
        // Create a container for the card
        VBox card = new VBox(5); // 5px spacing
        card.setPadding(new Insets(10));
        card.setStyle("-fx-background-color: white; " +
                "-fx-border-color: #cccccc; " +
                "-fx-border-radius: 5; " +
                "-fx-background-radius: 5;");

        // Label for rank
        Label rankLabel = new Label("#" + rank);
        rankLabel.setFont(Font.font("System", FontWeight.BOLD, 16));
        rankLabel.setTextFill(Color.web("#666666"));

        // Label for algorithm name
        Label algorithmLabel = new Label(algorithm);
        algorithmLabel.setFont(Font.font("System", FontWeight.BOLD, 14));

        // Label for formatted time
        Label timeLabel = new Label(formatTime(time));
        timeLabel.setTextFill(Color.web("#666666"));

        // Container for rank and algorithm info
        HBox container = new HBox();
        container.setAlignment(Pos.CENTER_LEFT);
        container.setSpacing(15);

        // Container for algorithm name and time
        VBox infoContainer = new VBox(5);
        infoContainer.getChildren().addAll(algorithmLabel, timeLabel);

        // Add rank and info to the horizontal container
        container.getChildren().addAll(rankLabel, infoContainer);

        // Add the horizontal container to the card
        card.getChildren().add(container);

        // Set margin below the card
        VBox.setMargin(card, new Insets(0, 0, 8, 0));

        // Add the card to the resultsBox
        resultsBox.getChildren().add(card);
    }

    // Format time from nanoseconds to a readable string
    private String formatTime(long timeNanos) {
        if (timeNanos < 1000) {
            return timeNanos + " ns"; // Nanoseconds
        } else if (timeNanos < 1_000_000) {
            return String.format("%.2f µs", timeNanos / 1000.0); // Microseconds
        } else if (timeNanos < 1_000_000_000) {
            return String.format("%.2f ms", timeNanos / 1_000_000.0); // Milliseconds
        } else {
            return String.format("%.2f s", timeNanos / 1_000_000_000.0); // Seconds
        }
    }

    // Add a data point to the bar chart
    private void addDataToChart(String algorithm, long time) {
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName(algorithm);
        series.getData().add(new XYChart.Data<>(algorithm, time));
        barChart.getData().add(series);
    }

    // Check if a text value is actually a number
    private boolean isValidNumericValue(String value) {
        if (value == null || value.trim().isEmpty()) {
            return false; // Empty or null is not valid
        }

        // Regex to check if the string is a valid number
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
