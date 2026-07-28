package com.example.demo;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    private Canvas canv1;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    @FXML
    protected void onCClick(MouseEvent e) {
        int mouseX = (int) e.getX();
        int mouseY = (int) e.getY();
        canv1.getGraphicsContext2D().beginPath();
        canv1.getGraphicsContext2D().setLineWidth(10);
        canv1.getGraphicsContext2D().lineTo(mouseX, mouseY);
        canv1.getGraphicsContext2D().stroke();
    }

    @FXML
    protected void onCDrag(MouseEvent e) {
        int mouseX = (int) e.getX();
        int mouseY = (int) e.getY();
        canv1.getGraphicsContext2D().setLineWidth(10);
        canv1.getGraphicsContext2D().lineTo(mouseX, mouseY);
        canv1.getGraphicsContext2D().stroke();
    }
}