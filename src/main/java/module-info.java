module org.example.sortingapplication {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.opencsv;


    opens org.example.sortingapplication to javafx.fxml;
    exports org.example.sortingapplication;
}