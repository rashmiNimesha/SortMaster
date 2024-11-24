module org.example.sortingapplication {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;

    opens org.example.sortingapplication.controller to javafx.fxml;
    exports org.example.sortingapplication;

}