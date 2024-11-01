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
    private Button csvUploadButton;

    @FXML
    private ChoiceBox<String> columnChoiceBox;

    @FXML
    private Button sortButton;

    @FXML
    private TableView<List<String>> csvTableView;


    @FXML
    private Label message;


    private String[] headers;
    private ObservableList<List<String>> data = FXCollections.observableArrayList();

    @FXML
    private void handleFileUpload() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV Files", "*.csv"));
        File file = fileChooser.showOpenDialog(csvUploadButton.getScene().getWindow());

        if (file != null) {
            try (CSVReader reader = new CSVReader(new FileReader(file))) {
                headers = reader.readNext();
                if (headers != null) {
                    setupTable(headers);
                    loadData(reader);
                    message.setText("Status: CSV File uploaded successfully.");
                } else {
                    message.setText("Error: No header found in the CSV file.");
                }
            } catch (Exception ex) {
                message.setText("Error: " + ex.getMessage());
                ex.printStackTrace();
            }
        }


    }


    private void setupTable(String[] headers) {
        csvTableView.getColumns().clear();
        for (String header : headers) {
            TableColumn<List<String>, String> column = new TableColumn<>(header);
            int colIndex = List.of(headers).indexOf(header);
            column.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().get(colIndex)));
            csvTableView.getColumns().add(column);
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
            csvTableView.setItems(data);
        } catch (Exception ex) {
            message.setText("Error loading data: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

}