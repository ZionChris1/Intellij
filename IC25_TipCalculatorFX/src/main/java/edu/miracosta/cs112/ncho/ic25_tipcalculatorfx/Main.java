package edu.miracosta.cs112.ncho.ic25_tipcalculatorfx;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.NumberFormat;

public class Main extends Application {

    private TextField billAmountTF = new TextField();
    private Label tipPercentLabel = new Label("15%");
    private Slider tipPercentSlider = new Slider(0, 30, 15);
    private TextField tipAmountTF = new TextField();
    private TextField totalAmountTF = new TextField();
    private Button clearButton = new Button("Clear");
    private Button calculateButton = new Button("Calculate");

    @Override
    public void start(Stage stage) throws IOException {
        //Setup new grid pane
        GridPane pane = new GridPane();
        pane.setVgap(5.0);
        pane.setHgap(5.0);
        pane.setAlignment(Pos.CENTER);

        //Add input field and label
        pane.add(new Label("Bill Amount:"), 0, 0);
        pane.add(billAmountTF, 1, 0);
        billAmountTF.setAlignment(Pos.CENTER_RIGHT);

        //Setup real time tip calculation for text input
        billAmountTF.textProperty().addListener((obsVal, oldVal, newVal) -> calculate());

        //Add tip percent slider and label to window
        pane.add(tipPercentLabel, 0, 1);
        pane.add(tipPercentSlider, 1, 1);

        //Setup tick marks for slider
        tipPercentSlider.setShowTickMarks(true);
        tipPercentSlider.setShowTickLabels(true);
        tipPercentSlider.setMajorTickUnit(5);
        tipPercentSlider.setMinorTickCount(4);
        tipPercentSlider.setSnapToTicks(true);

        //Setup real time calculation for slider input
        tipPercentSlider.valueProperty().addListener((obsVal, oldVal, newVal) -> {
            tipPercentLabel.setText(newVal.intValue() + "%");
            calculate();
        });

        //Setup tip amount field and add to window
        pane.add(new Label("Tip amount:"), 0, 2);
        pane.add(tipAmountTF, 1, 2);
        tipAmountTF.setFocusTraversable(false);
        tipAmountTF.setMouseTransparent(true);
        tipAmountTF.setAlignment(Pos.CENTER_RIGHT);

        //Setup total amount field and add to window
        pane.add(new Label("Total amount:"), 0, 3);
        pane.add(totalAmountTF, 1, 3);
        totalAmountTF.setFocusTraversable(false);
        totalAmountTF.setMouseTransparent(true);
        totalAmountTF.setAlignment(Pos.CENTER_RIGHT);

        //Setup Button locations in the window
        HBox hbox = new HBox();
        hbox.setSpacing(10.0);
        hbox.setAlignment(Pos.CENTER_RIGHT);
        hbox.getChildren().add(clearButton);
        hbox.getChildren().add(calculateButton);
        pane.add(hbox, 1, 4);

        //Setup Button action handlers
        clearButton.setOnAction(e -> clear());
        calculateButton.setOnAction(e -> calculate());


        //Setup scene and show window
        Scene scene = new Scene(pane, 320, 240);
        stage.setTitle("Tip Calculator");
        stage.setScene(scene);
        stage.show();
    }

    private void clear() {
        //Reset text fields, slider, and focus
        billAmountTF.clear();
        tipAmountTF.clear();
        totalAmountTF.clear();
        tipPercentSlider.setValue(15);
        billAmountTF.requestFocus();
    }

    private void calculate() {
        if(billAmountTF.getText().isBlank()) {
            clear();
            return;
        }

        try {
            //Get bill amount and calculate tip
            int billAmount = (int)Double.parseDouble(billAmountTF.getText());
            int tipAmount = billAmount * (int)tipPercentSlider.getValue() / 100;

            //Display output
            NumberFormat currency = NumberFormat.getCurrencyInstance();
            tipAmountTF.setText(currency.format(tipAmount));
            totalAmountTF.setText(currency.format(billAmount + tipAmount));
        } catch(NumberFormatException e) {
            //Clear invalid input
            billAmountTF.clear();
            billAmountTF.requestFocus();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}