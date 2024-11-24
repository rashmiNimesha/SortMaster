package org.example.sortingapplication.util;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.beans.property.SimpleStringProperty;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CSVHandler {

    // Reads and returns the headers (first row) from a CSV file
    public List<String> getHeaders(File file) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String headerLine = reader.readLine(); // Retrieve the first line as headers
        reader.close();
        return headerLine != null ? Arrays.asList(headerLine.split(",")) : new ArrayList<>();
    }

    // Reads the data (excluding headers) from a CSV file and converts it into an observable list
    public ObservableList<ObservableList<String>> getData(File file) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        reader.readLine(); // Skip header line
        ObservableList<ObservableList<String>> data = FXCollections.observableArrayList();
        String line;
        while ((line = reader.readLine()) != null) {
            // Split each line into columns and add it to the data list
            ObservableList<String> row = FXCollections.observableArrayList(line.split(","));
            data.add(row);
        }
        reader.close();
        return data;
    }

    // Identifies the indices of numeric columns by checking the first row of data
    public List<Integer> identifyNumericColumns(List<String> headers, BufferedReader reader) throws IOException {
        List<Integer> numericColumnIndices = new ArrayList<>();
        String line = reader.readLine();
        if (line != null) {
            String[] values = line.split(",");
            for (int i = 0; i < values.length; i++) {
                // Check if the column contains numeric values
                if (isNumeric(values[i])) {
                    numericColumnIndices.add(i);
                }
            }
        }
        return numericColumnIndices;
    }

    // Dynamically creates table columns based on headers and adds them to the TableView
    public void createTableColumns(List<String> headers, TableView<ObservableList<String>> tableView) {
        tableView.getColumns().clear();
        for (int i = 0; i < headers.size(); i++) {
            final int colIndex = i;
            TableColumn<ObservableList<String>, String> column = new TableColumn<>(headers.get(i));
            // Bind each column to the corresponding data in the rows
            column.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().get(colIndex)));
            tableView.getColumns().add(column);
        }
    }

    // Helper method to check if a string can be parsed as a numeric value
    private boolean isNumeric(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        try {
            Double.parseDouble(str); // Attempt to parse as a double
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}