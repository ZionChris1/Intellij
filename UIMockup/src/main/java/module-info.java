module org.example.uimockup {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.almasb.fxgl.all;

    opens org.example.uimockup to javafx.fxml;
    exports org.example.uimockup;
}