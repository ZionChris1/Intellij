module edu.miracosta.cs112.ncho.capstone {
    requires javafx.controls;
    requires javafx.fxml;


    exports edu.miracosta.cs112.ncho.capstone.view;
    opens edu.miracosta.cs112.ncho.capstone.view to javafx.fxml;
}