module org.example.cs210week8 {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.cs210week8 to javafx.fxml;
    exports org.example.cs210week8;
}