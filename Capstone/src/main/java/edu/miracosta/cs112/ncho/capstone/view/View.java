package edu.miracosta.cs112.ncho.capstone.view;

import edu.miracosta.cs112.ncho.capstone.controller.Controller;
import javafx.application.Application;
import javafx.stage.Stage;

public class View extends Application {
    Controller controller;

    @Override
    public void start(Stage stage) {
        controller = Controller.getInstance();
        controller.load("autosave.level");

        ViewNavigator.setStage(stage);
        ViewNavigator.loadScene("Real Engine", new MainScene());
        stage.setMaximized(true);
    }

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        controller.save("autosave.level");
    }
}