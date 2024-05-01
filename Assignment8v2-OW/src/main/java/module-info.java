module org.example.assignment8v2ow {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.assignment8v2ow to javafx.fxml;
    exports org.example.assignment8v2ow;
}