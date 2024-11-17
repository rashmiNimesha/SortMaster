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

    public List<String> getHeaders(File file) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String headerLine = reader.readLine();
        reader.close();
        return headerLine != null ? Arrays.asList(headerLine.split(",")) : new ArrayList<>();
    }

    public ObservableList<ObservableList<String>> getData(File file) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        reader.readLine(); // Skip header line
        ObservableList<ObservableList<String>> data = FXCollections.observableArrayList();
        String line;
        while ((line = reader.readLine()) != null) {
            ObservableList<String> row = FXCollections.observableArrayList(line.split(","));
            data.add(row);
        }
        reader.close();
        return data;
    }

    public List<Integer> identifyNumericColumns(List<String> headers, BufferedReader reader) throws IOException {
        List<Integer> numericColumnIndices = new ArrayList<>();
        String line = reader.readLine();
        if (line != null) {
            String[] values = line.split(",");
            for (int i = 0; i < values.length; i++) {
                if (isNumeric(values[i])) {
                    numericColumnIndices.add(i);
                }
            }
        }
        return numericColumnIndices;
    }

    public void createTableColumns(List<String> headers, TableView<ObservableList<String>> tableView) {
        tableView.getColumns().clear();
        for (int i = 0; i < headers.size(); i++) {
            final int colIndex = i;
            TableColumn<ObservableList<String>, String> column = new TableColumn<>(headers.get(i));
            column.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().get(colIndex)));
            tableView.getColumns().add(column);
        }
    }

    private boolean isNumeric(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}