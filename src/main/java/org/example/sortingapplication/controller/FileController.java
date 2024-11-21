package org.example.sortingapplication.controller;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.scene.control.Alert;
import org.example.sortingapplication.util.CSVHandler;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class FileController {
    @FXML
    private TextField fileNameField;
    @FXML
    private TableView<ObservableList<String>> tableView;
    @FXML
    private ComboBox<String> columnComboBox;
    @FXML
    private BarChart<String, Number> barChart;
    @FXML
    private VBox resultsBox;

    private final CSVHandler csvHandler = new CSVHandler();
    private final PerformanceController performanceController = new PerformanceController();

    @FXML
    public void initialize() {
        performanceController.setBarChart(barChart);
        performanceController.setResultsBox(resultsBox);
    }

    @FXML
    public void handleOpenFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open CSV File");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV Files", "*.csv"));

        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            fileNameField.setText(selectedFile.getName());
            try {
                List<String> headers = csvHandler.getHeaders(selectedFile);
                csvHandler.createTableColumns(headers, tableView);
                ObservableList<ObservableList<String>> data = csvHandler.getData(selectedFile);
                tableView.setItems(data);

                columnComboBox.getItems().clear();
                columnComboBox.getItems().addAll(headers);
            } catch (IOException e) {
                showError("Error reading CSV file", "An error occurred while reading the CSV file.");
                e.printStackTrace();
            }
        } else {
            showError("No File Selected", "Please select a CSV file to proceed.");
        }
    }

    @FXML
    public void handleAnalyze() {
        String selectedColumn = columnComboBox.getValue();
        if (selectedColumn != null) {
            int columnIndex = columnComboBox.getItems().indexOf(selectedColumn);
            performanceController.handleAnalyze(tableView.getItems(), columnIndex);
        } else {
            showError("No Column Selected", "Please select a column from the dropdown to analyze.");
        }
    }

    private void showError(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
