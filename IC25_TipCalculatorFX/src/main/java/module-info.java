module edu.miracosta.cs112.ncho.ic25_tipcalculatorfx {
    requires javafx.controls;
    requires javafx.fxml;


    opens edu.miracosta.cs112.ncho.ic25_tipcalculatorfx to javafx.fxml;
    exports edu.miracosta.cs112.ncho.ic25_tipcalculatorfx;
}