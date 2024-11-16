module org.example.sortingapplication {
    requires javafx.controls;
    requires javafx.fxml;

    opens org.example.sortingapplication.controller to javafx.fxml;
    exports org.example.sortingapplication;

}