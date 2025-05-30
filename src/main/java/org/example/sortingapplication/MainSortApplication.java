package org.example.sortingapplication;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainSortApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainSortApplication.class.getResource("main-page-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1024, 768);

        // Set minimum width and height
        stage.setMinWidth(1024);
        stage.setMinHeight(768);
        stage.setTitle("SortMaster");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}