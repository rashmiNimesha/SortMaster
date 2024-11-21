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

public class PerformanceController {
    private BarChart<String, Number> barChart;
    private VBox resultsBox;
    private final SortingController sortingController = new SortingController();

    public void setBarChart(BarChart<String, Number> barChart) {
        this.barChart = barChart;
    }

    Long quickSortTime;
    Long mergeSortTime;
    Long heapSortTime;
    Long shellSortTime;

    public void setResultsBox(VBox resultsBox) {
        this.resultsBox = resultsBox;
    }

    public void handleAnalyze(List<ObservableList<String>> tableData, int columnIndex) {

        if (barChart == null || resultsBox == null) {
            showError("Initialization Error", "BarChart or ResultsBox is not initialized.");
            return;
        }

        barChart.getData().clear();
        resultsBox.getChildren().clear();

        List<Double> numericData = new ArrayList<>();

        for (ObservableList<String> row : tableData) {
            String cellValue = row.get(columnIndex);
            if (isValidNumericValue(cellValue)) {

                numericData.add(Double.parseDouble(cellValue.replaceAll("[^\\d.-]", "")));
            } else if (cellValue != null && !cellValue.isEmpty()) {

                showError("Invalid Data", "Column contains non-numeric or invalid data. Please correct the values.");
                return;
            }
        }

        if (numericData.isEmpty()) {
            showError("No Numeric Data", "The selected column does not contain numeric data for analysis.");
            return;
        }

        quickSortTime = sortingController.runQuickSort(new ArrayList<>(numericData));
        mergeSortTime = sortingController.runMergeSort(new ArrayList<>(numericData));
        heapSortTime = sortingController.runHeapSort(new ArrayList<>(numericData));
        shellSortTime = sortingController.runShellSort(new ArrayList<>(numericData));
        System.out.println(numericData);

        Platform.runLater(() -> {
            addDataToChart("QuickSort", quickSortTime);
            addDataToChart("MergeSort", mergeSortTime);
            addDataToChart("HeapSort", heapSortTime);
            addDataToChart("ShellSort", shellSortTime);
        });
    }

    private void addDataToChart(String algorithm, long time) {
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName(algorithm);
        series.getData().add(new XYChart.Data<>(algorithm, time));
        barChart.getData().add(series);
        resultsBox.getChildren().add(new Label(algorithm + " - " + time + " ns"));
    }

    private boolean isValidNumericValue(String value) {
        if (value == null || value.trim().isEmpty()) {
            return false;
        }

        String numericPattern = "^-?\\d*(\\.\\d+)?$";
        return value.matches(numericPattern);
    }

    private void showError(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
