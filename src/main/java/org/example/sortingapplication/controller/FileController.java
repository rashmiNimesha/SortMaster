package org.example.sortingapplication.controller;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
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

    private final CSVHandler csvHandler = new CSVHandler(); // Utility to handle reading CSV files
    private final PerformanceController performanceController = new PerformanceController();

    @FXML
    public void initialize() {
        performanceController.setBarChart(barChart);
        performanceController.setResultsBox(resultsBox);
    }

    @FXML
    public void handleOpenFile() {
        // Open a dialog for the user to select a CSV file
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open CSV File");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV Files", "*.csv"));

        File selectedFile = fileChooser.showOpenDialog(null); // Get the file chosen by the user
        if (selectedFile != null) {
            fileNameField.setText(selectedFile.getName()); // Show the file name in the text field
            try {
                // Read the CSV file's headers and data
                List<String> headers = csvHandler.getHeaders(selectedFile);
                csvHandler.createTableColumns(headers, tableView); // Set up table columns
                ObservableList<ObservableList<String>> data = csvHandler.getData(selectedFile);
                tableView.setItems(data); // Display the data in the table

                // Populate the dropdown with the file's column headers
                columnComboBox.getItems().clear();
                columnComboBox.getItems().addAll(headers);
            } catch (IOException e) {
                // Handle errors if the file cannot be read
                showError("Error reading CSV file", "An error occurred while reading the CSV file.");
                e.printStackTrace();
            }
        } else {
            // Show a message if no file is selected
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
        // Shows an error dialog with a specified title and message to inform the user about an issue
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    // Displaying box in Help menu
    public void showAboutDialog() {
        Stage aboutStage = new Stage();
        aboutStage.initModality(Modality.APPLICATION_MODAL);
        aboutStage.setTitle("About SortMaster");
        Label appDetails = new Label("Developed by \n\tWeerasekara W.M.S.R. \n\tNimesha G.R. \n\tRajapaksha R.P.N.D.\nA tool for sorting algorithm performance analysis.\nSortMaster v1.0");
        appDetails.setWrapText(true);

        //close button
        Button closeButton = new Button("Close");
        closeButton.setOnAction(e -> aboutStage.close());

        VBox layout = new VBox(10, appDetails, closeButton);
        layout.setPadding(new Insets(20, 20, 20, 20));
        layout.setStyle("-fx-alignment: center;");

        Scene scene = new Scene(layout, 300, 200);
        aboutStage.setScene(scene);
        aboutStage.show();
    }
}
