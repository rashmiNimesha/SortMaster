package org.example.sortingapplication.controller;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

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
}
