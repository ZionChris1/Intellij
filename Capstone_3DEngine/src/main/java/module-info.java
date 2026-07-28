module edu.miracosta.cs112.ncho.capstone_3dengine {
    requires javafx.controls;
    requires javafx.fxml;


    exports edu.miracosta.cs112.ncho.capstone_3dengine.Model;
    opens edu.miracosta.cs112.ncho.capstone_3dengine.Model to javafx.fxml;
    exports edu.miracosta.cs112.ncho.capstone_3dengine.View;
    opens edu.miracosta.cs112.ncho.capstone_3dengine.View to javafx.fxml;
}