package edu.miracosta.cs112.ncho.capstone_3dengine.View;

import edu.miracosta.cs112.ncho.capstone_3dengine.Model.Actor;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.ObservableFaceArray;
import javafx.scene.shape.Shape3D;
import javafx.scene.shape.Sphere;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class HelloApplication extends Application {
    Shape3D current = null;
    private ObservableList<Actor> actors;
    boolean light = false;
    String mShapeType, mLightType;
    Camera mCamera;
    boolean mDelete, cube;
    double mStartX, mStartY;
    @Override
    public void start(Stage stage) throws IOException {
        actors = FXCollections.observableArrayList();
        ArrayList<Shape3D> shapes = new ArrayList<>();
        ListView<Actor> outline = new ListView<>();
        ComboBox<String> addDropdown = new ComboBox<String>();
        BorderPane root = new BorderPane();
        GridPane optionPane = new GridPane();
        GridPane topPane = new GridPane();
        ScrollPane optionScrollPane = new ScrollPane();
        root.setRight(optionScrollPane);
        root.setTop(topPane);
        optionScrollPane.setContent(optionPane);
        optionScrollPane.setMinWidth(192);
        optionPane.getChildren().add(outline);
        VBox header = new VBox();

        Button sphereButton = new Button("Sphere");
        Button cubeButton = new Button("Cube");
        Button pointLight = new Button("Cube");
        Button directinalLightButton = new Button("Cube");
        Button spotLightButton = new Button("Cube");
        Button ambientLightButton = new Button("Cube");

        topPane.add(sphereButton, 0, 0);
        topPane.add(cubeButton, 1, 0);

        ColorPicker picker = new ColorPicker();

        optionPane.add(picker, 0, 0);

        root.setBackground(new Background(new BackgroundFill(Color.DARKGRAY, null, null)));

        Scene s = new Scene(root, 500, 500, true);
        stage.setMaximized(true);
        mCamera = new PerspectiveCamera(true);
        s.setCamera(mCamera);
        PhongMaterial phm = new PhongMaterial();
        phm.setSpecularColor(Color.WHITE);
        phm.setDiffuseColor(Color.RED);
        AmbientLight al = new AmbientLight();
        al.setColor(Color.GRAY);
        root.getChildren().add(al);
        s.setFill(Color.GRAY);
        stage.setTitle("Real engine");
        stage.setScene(s);

        stage.show();
        s.setCamera(new PerspectiveCamera());
        s.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(mDelete) {
                    for(Shape3D shape : shapes) {
                        if(Math.abs(shape.getTranslateX() - mouseEvent.getX()) < shape.getScaleX() && Math.abs(shape.getTranslateY() - mouseEvent.getY()) < shape.getScaleY()) {
                            root.getChildren().remove(shape);
                            shapes.remove(shape);
                            break;
                        }
                    }
                } else {
                    if(light) {
                        PointLight p = new PointLight();
                        p.setTranslateX(mouseEvent.getX());
                        p.setTranslateY(mouseEvent.getY());
                        root.getChildren().add(p);
                        System.out.println("Light added");
                    } else {
                        Shape3D shape = new Sphere();
                        shape.setMaterial(phm);
                        shapes.add(shape);
                        current = shape;
                        shape.setTranslateX(mouseEvent.getX());
                        shape.setTranslateY(mouseEvent.getY());
                        mStartX = mouseEvent.getX();
                        mStartY = mouseEvent.getY();
                        shape.setScaleX(0);
                        shape.setScaleY(0);
                        root.getChildren().add(current);
                    }
                }
            }
        });
        s.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(current != null) {
                    current.setScaleX(Math.abs((mouseEvent.getX() - mStartX) + (mouseEvent.getY() - mStartY)));
                    current.setScaleY(Math.abs((mouseEvent.getX() - mStartX) + (mouseEvent.getY() - mStartY)));
                    current.setScaleZ(Math.abs((mouseEvent.getX() - mStartX) + (mouseEvent.getY() - mStartY)));
                }
            }
        });
        s.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                current = null;
            }
        });
        s.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                switch(keyEvent.getCode()) {
                    case W -> {
                        mCamera.setTranslateX(mCamera.getTranslateX() + 10);
                    }
                    case S -> {
                        mCamera.setTranslateX(mCamera.getTranslateX() - 10);
                    }
                    case DELETE -> {
                        mDelete = true;
                    }
                    case L -> {
                        light = true;
                    }
                }
            }
        });
        s.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                switch(keyEvent.getCode()) {
                    case DELETE -> {mDelete = false;}
                }
            }
        });
    }

    public void spawnShape() {
        switch(mShapeType) {

        }
    }

    public void spawnLight() {
        switch(mLightType) {

        }
    }

    public static void main(String[] args) {
        launch();
    }
}