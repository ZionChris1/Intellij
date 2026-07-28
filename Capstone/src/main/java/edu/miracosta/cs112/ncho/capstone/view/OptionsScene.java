package edu.miracosta.cs112.ncho.capstone.view;

import edu.miracosta.cs112.ncho.capstone.controller.Controller;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class OptionsScene extends Scene {
    private Scene mPreviousScene;
    private Controller mController;

    private TextField mCameraFOVTF, mCameraSpeedTF, mCameraFarClipTF;

    public OptionsScene(Scene previousScene) {
        super(new GridPane(), 300, 200);

        mPreviousScene = previousScene;

        mController = Controller.getInstance();

        GridPane root = new GridPane();

        //Initialize nodes
        Label cameraFOVLabel = new Label("Camera Field of View");
        Label cameraSpeedLabel = new Label("Camera Movement Speed");
        Label cameraFarClipLabel = new Label("Far clip distance");
        Button saveButton = new Button("Save");
        Button cancelButton = new Button("Cancel");
        mCameraFOVTF = new TextField();
        mCameraSpeedTF = new TextField();
        mCameraFarClipTF = new TextField();

        //Populate field values
        mCameraSpeedTF.setText(String.valueOf((int)mController.getCameraSpeed()));
        mCameraFOVTF.setText(String.valueOf((int)mController.getCameraFOV()));
        mCameraFarClipTF.setText(String.valueOf((int)mController.getCamera().getFarClip()));

        //Add nodes to root
        root.add(cameraSpeedLabel, 0, 0);
        root.add(mCameraSpeedTF, 1, 0);
        root.add(cameraFOVLabel, 0, 1);
        root.add(mCameraFOVTF, 1, 1);
        root.add(cameraFarClipLabel, 0, 2);
        root.add(mCameraFarClipTF, 1, 2);
        root.add(cancelButton, 0, 3);
        root.add(saveButton, 1, 3);

        //Connect input validator
        mCameraFOVTF.textProperty().addListener((obsVal, oldVal, newVal) -> ensureCorrectInput(newVal, mCameraFOVTF));
        mCameraSpeedTF.textProperty().addListener((obsVal, oldVal, newVal) -> ensureCorrectInput(newVal, mCameraSpeedTF));
        mCameraFarClipTF.textProperty().addListener((obsVal, oldVal, newVal) -> ensureCorrectInput(newVal, mCameraFarClipTF));

        //Setup button actions
        cancelButton.setOnAction(e -> returnToPreviousScene());
        saveButton.setOnAction(e -> saveAndReturnToPreviousScene());

        super.setRoot(root);
    }

    private void returnToPreviousScene() {
        ViewNavigator.loadScene("Real Engine", mPreviousScene);
    }

    private void saveAndReturnToPreviousScene() {
        mController.getCamera().setFarClip(Double.parseDouble(mCameraFarClipTF.getText()));
        mController.setFieldOfView(Double.parseDouble(mCameraFOVTF.getText()));
        mController.setCameraMoveSpeed(Double.parseDouble(mCameraSpeedTF.getText()));
        returnToPreviousScene();
    }

    private void ensureCorrectInput(String newVal, TextField tf) {
        //Remove invalid characters
        if (!newVal.matches("\\d*")) {
            tf.setText(newVal.replaceAll("[^\\d]", ""));
        }
    }
}
