package org.example.sortingapplication;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import com.opencsv.CSVReader;

import java.io.File;
import java.io.FileReader;
import java.util.List;

public class SortController {
    @FXML
    private Button uploadButton;

    @FXML
    private ChoiceBox<String> columnChoiceBox;

    @FXML
    private Button sortButton;

    @FXML
    private TableView<List<String>> tableView;


    @FXML
    private Label statusLabel;


    private String[] headers;
    private ObservableList<List<String>> data = FXCollections.observableArrayList();

    @FXML
    private void handleFileUpload() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV Files", "*.csv"));
        File file = fileChooser.showOpenDialog(uploadButton.getScene().getWindow());

        if (file != null) {
            try (CSVReader reader = new CSVReader(new FileReader(file))) {
                headers = reader.readNext();
                if (headers != null) {
                    setupTable(headers);
                    loadData(reader);
                    statusLabel.setText("Status: File uploaded successfully.");
                } else {
                    statusLabel.setText("Error: No header found in the CSV file.");
                }
            } catch (Exception ex) {
                statusLabel.setText("Error: " + ex.getMessage());
                ex.printStackTrace();
            }
        }


    }


    private void setupTable(String[] headers) {
        tableView.getColumns().clear();
        for (String header : headers) {
            TableColumn<List<String>, String> column = new TableColumn<>(header);
            int colIndex = List.of(headers).indexOf(header);
            column.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().get(colIndex)));
            tableView.getColumns().add(column);
        }
    }

    private void loadData(CSVReader reader) {
        data.clear();
        String[] line;
        try {
            while ((line = reader.readNext()) != null) {
                if (line.length == headers.length) {
                    data.add(FXCollections.observableArrayList(line));
                }
            }
            tableView.setItems(data);
        } catch (Exception ex) {
            statusLabel.setText("Error loading data: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

}