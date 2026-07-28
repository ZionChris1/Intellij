package edu.miracosta.cs112.ncho.capstone.view;

import edu.miracosta.cs112.ncho.capstone.controller.Controller;
import edu.miracosta.cs112.ncho.capstone.model.Actor;
import edu.miracosta.cs112.ncho.capstone.model.Mesh;
import javafx.geometry.Point2D;
import javafx.geometry.Point3D;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.robot.Robot;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Scale;
import javafx.scene.transform.Translate;
import javafx.stage.FileChooser;
import javafx.util.converter.NumberStringConverter;

import java.io.File;

public class MainScene extends Scene {
    public static final int TransformLabelWidth = 12;
    private ListView<Actor> outline;
    private Controller controller;
    private TextField[] transformTFs;
    private TextField specularPowerField;
    private SubScene viewport;
    double mStartX, mStartY;
    private boolean mRotating;
    private double mCameraRotationRate = 0.1;
    Robot robot = new Robot();
    private Group viewportRoot;
    ColorPicker actorColorPicker, specularColorPicker, backgroundColorPicker;

    public MainScene() {
        super(new GridPane(), 500, 400);

        //Initialize node fields
        viewportRoot = new Group();
        viewport = new SubScene(viewportRoot, 1200, 900, true, SceneAntialiasing.BALANCED);
        controller = Controller.getInstance();
        outline = new ListView<>(controller.getActors());
        actorColorPicker = new ColorPicker();
        specularColorPicker = new ColorPicker();
        backgroundColorPicker = new ColorPicker();
        specularPowerField = new TextField();

        //Create components
        BorderPane root = new BorderPane();
        GridPane optionPane = new GridPane();
        GridPane topPane = new GridPane();
        GridPane transformBox = new GridPane();
        GridPane positionCoordsPane = new GridPane();
        GridPane rotationCoordsPane = new GridPane();
        GridPane scaleCoordsPane = new GridPane();
        MenuBar menuBar = new MenuBar();
        Pane viewportContainer = new Pane();
        ComboBox<String> addDropdown = new ComboBox<>(controller.getActorTypes());
        ScrollPane optionScrollPane = new ScrollPane();
        Button addButton = new Button("+");
        Menu fileMenu = new Menu("File");
        Menu editMenu = new Menu("Edit");

        //Create labels
        Label colorLabel = new Label("Color");
        Label specularLabel = new Label("Specular");
        Label backgroundColorLabel = new Label("Background color");
        Label specularPowerLabel = new Label("Specular Power");
        Label positionBoxLabel = new Label("Position");
        Label rotationBoxLabel = new Label("Rotation");
        Label scaleBoxLabel = new Label("Scale");
        Label cameraTypeLabel = new Label("Camera Type");
        Label xPositionLabel = new Label("X:");
        Label yPositionLabel = new Label("Y:");
        Label zPositionLabel = new Label("Z:");
        Label xRotationLabel = new Label("X:");
        Label yRotationLabel = new Label("Y:");
        Label zRotationLabel = new Label("Z:");
        Label xScaleLabel = new Label("X:");
        Label yScaleLabel = new Label("Y:");
        Label zScaleLabel = new Label("Z:");

        //Create perspective/orthographic camera selector
        RadioButton perspectiveButton = new RadioButton("Perspective");
        RadioButton orthographicButton = new RadioButton("Orthographic");
        ToggleGroup cameraTypeGroup = new ToggleGroup();

        //Create menu options
        MenuItem newMenuItem = new MenuItem("New");
        MenuItem saveMenuItem = new MenuItem("Save");
        MenuItem loadMenuItem = new MenuItem("Load");
        MenuItem optionsMenuItem = new MenuItem("Options");


        //Setup color pickers
        backgroundColorPicker.valueProperty().set(controller.getBackgroundColor());

        actorColorPicker.valueProperty().addListener((obsVal, oldColor, newColor) -> controller.setColor(newColor));
        specularColorPicker.valueProperty().addListener((ObsVal, oldColor, newColor) -> controller.setSpecular(newColor));
        backgroundColorPicker.valueProperty().addListener((obsVal, oldColor, newColor) -> setBackroundColor(newColor));

        //Initialize position, rotation, and scale text fields
        transformTFs = new TextField[9];
        for(int i = 0; i < transformTFs.length; i++)
            transformTFs[i] = new TextField();


        perspectiveButton.setToggleGroup(cameraTypeGroup);
        orthographicButton.setToggleGroup(cameraTypeGroup);

        addDropdown.getSelectionModel().select(0);

        //Setup option panel
        optionScrollPane.setContent(optionPane);
        optionScrollPane.setMinWidth(192);
        optionPane.getChildren().add(outline);

        //Setup button and menu actions
        addButton.setOnAction(e -> spawnActor());
        newMenuItem.setOnAction(e -> newLevel());
        saveMenuItem.setOnAction(e -> saveLevel());
        loadMenuItem.setOnAction(e -> loadLevel());
        perspectiveButton.setOnAction(e -> setCameraType("Perspective"));
        orthographicButton.setOnAction(e -> setCameraType("Orthographic"));
        perspectiveButton.setSelected(true);
        optionsMenuItem.setOnAction(e -> ViewNavigator.loadScene("Options", new OptionsScene(this)));

        //Add items to menus and menus to menu bar
        fileMenu.getItems().addAll(newMenuItem, saveMenuItem, loadMenuItem);
        editMenu.getItems().addAll(optionsMenuItem);
        menuBar.getMenus().addAll(fileMenu, editMenu);

        //Setup viewport container
        viewportContainer.getChildren().add(viewport);
        viewportContainer.setManaged(true);

        //Set min width of x, y, and z labels
        xPositionLabel.setMinWidth(TransformLabelWidth);
        yPositionLabel.setMinWidth(TransformLabelWidth);
        zPositionLabel.setMinWidth(TransformLabelWidth);
        xRotationLabel.setMinWidth(TransformLabelWidth);
        yRotationLabel.setMinWidth(TransformLabelWidth);
        zRotationLabel.setMinWidth(TransformLabelWidth);
        xScaleLabel.setMinWidth(TransformLabelWidth);
        yScaleLabel.setMinWidth(TransformLabelWidth);
        zScaleLabel.setMinWidth(TransformLabelWidth);

        //Add position, rotation, and scale labels and boxes to layout panes
        positionCoordsPane.add(xPositionLabel, 0, 0);
        positionCoordsPane.add(transformTFs[0], 1, 0);
        positionCoordsPane.add(yPositionLabel, 2, 0);
        positionCoordsPane.add(transformTFs[1], 3, 0);
        positionCoordsPane.add(zPositionLabel, 4, 0);
        positionCoordsPane.add(transformTFs[2], 5, 0);

        rotationCoordsPane.add(xRotationLabel, 0, 0);
        rotationCoordsPane.add(transformTFs[3], 1, 0);
        rotationCoordsPane.add(yRotationLabel, 2, 0);
        rotationCoordsPane.add(transformTFs[4], 3, 0);
        rotationCoordsPane.add(zRotationLabel, 4, 0);
        rotationCoordsPane.add(transformTFs[5], 5, 0);

        scaleCoordsPane.add(xScaleLabel, 0, 0);
        scaleCoordsPane.add(transformTFs[6], 1, 0);
        scaleCoordsPane.add(yScaleLabel, 2, 0);
        scaleCoordsPane.add(transformTFs[7], 3, 0);
        scaleCoordsPane.add(zScaleLabel, 4, 0);
        scaleCoordsPane.add(transformTFs[8], 5, 0);

        //Setup mouse drag on position, rotation, scale, and specular power text fields
        for(TextField tf : transformTFs) {
            tf.setOnMousePressed(e -> controller.handleMouseDragStart(e, tf));
            tf.setOnMouseDragged(e -> controller.handleMouseDrag(e));
            tf.textProperty().addListener((obsVal, oldVal, newVal) -> ensureCorrectInput(oldVal, newVal, tf));
        }
        specularPowerField.setOnMousePressed(e -> controller.handleMouseDragStart(e, specularPowerField));
        specularPowerField.setOnMouseDragged(e -> controller.handleMouseDrag(e));
        specularPowerField.textProperty().addListener((obsVal, oldVal, newVal) -> ensureCorrectInput(oldVal, newVal, specularPowerField));

        //Setup groups for option panel
        VBox positionBox = new VBox(positionBoxLabel, positionCoordsPane);
        VBox rotationBox = new VBox(rotationBoxLabel, rotationCoordsPane);
        VBox scaleBox = new VBox(scaleBoxLabel, scaleCoordsPane);
        VBox topBar = new VBox(menuBar, topPane);
        HBox colorBox = new HBox(colorLabel, actorColorPicker);
        HBox specularBox = new HBox(specularLabel, specularColorPicker);
        HBox backgroundColorBox = new HBox(backgroundColorLabel, backgroundColorPicker);
        HBox specularPowerBox = new HBox(specularPowerLabel, specularPowerField);

        HBox cameraTypeBox = new HBox(perspectiveButton, orthographicButton);

        //Setup top level box for position, rotation, and scale fields and labels
        transformBox.setMaxWidth(192);
        transformBox.add(positionBox, 0, 0);
        transformBox.add(rotationBox, 0, 1);
        transformBox.add(scaleBox, 0, 2);

        //Add panes to option panel
        optionPane.add(transformBox, 0, 1);
        optionPane.add(colorBox, 0, 2);
        optionPane.add(specularBox, 0, 3);
        optionPane.add(backgroundColorBox, 0, 4);
        optionPane.add(specularPowerBox, 0, 5);

        //Add items to top bar
        topPane.add(addDropdown, 0, 0);
        topPane.add(addButton, 1, 0);
        topPane.add(cameraTypeLabel, 2, 0);
        topPane.add(cameraTypeBox, 3, 0);

        outline.setMaxHeight(256);

        //Setup listeners for specular text and outline selectiom
        //specularPowerField.textProperty().addListener((ObsVal, oldPower, newPower) -> controller.(Double.parseDouble(newPower)));
        outline.getSelectionModel().selectedItemProperty().addListener((obsVal, oldVal, newVal) -> updateSelection(newVal));

        //Setup selected actor type
        controller.setSpawnTypeProperty(addDropdown.valueProperty());

        //Add components to root
        root.setTop(topBar);
        root.setCenter(viewportContainer);
        root.setRight(optionScrollPane);

        //Add all actor's nodes to viewport
        viewportRoot.getChildren().setAll(controller.getNodes());

        //Setup viewport
        viewport.setFill(controller.getBackgroundColor());
        viewport.setCamera(controller.getCamera());
        viewport.heightProperty().bind(viewportContainer.heightProperty());
        viewport.widthProperty().bind(viewportContainer.widthProperty());

        //Setup mouse handlers
        viewport.setOnMousePressed(e -> startRotateCamera(e));
        viewport.setOnMouseDragged(e -> rotateCamera(e));
        viewport.setOnMouseReleased(e -> endRotateCamera(e));
        viewport.setOnMouseClicked(e -> selectClickedActor(e));

        //Setup key handler
        root.setOnKeyPressed(e -> handleKeyInput(e));

        //Set root
        setRoot(root);
    }

    public void spawnActor() {
        //Get the point 500 units ahead of the camera
        Point3D spawnPos = controller.getCamera().localToScene(0, 0, 500);

        //Create a new actor and update viewport
        controller.spawnActor(spawnPos.getX(), spawnPos.getY(), spawnPos.getZ());
        viewportRoot.getChildren().setAll(controller.getNodes());
    }

    private void deleteSelectedActor() {
        //Delete selected actor and update viewport
        controller.deleteSelectedActor();
        viewportRoot.getChildren().setAll(controller.getNodes());
    }

    private void setBackroundColor(Color newColor) {
        viewport.setFill(newColor);
        controller.setBackgroundColor(newColor);
    }

    private void handleKeyInput(KeyEvent e) {
        switch(e.getCode()) {
            case W -> {
                controller.moveCamera(0, 0, 1 * controller.getCameraSpeed());
            }
            case S -> {
                controller.moveCamera(0, 0, -1 * controller.getCameraSpeed());
            }
            case A -> {
                controller.moveCamera(-1 * controller.getCameraSpeed(), 0, 0);
            }
            case D -> {
                controller.moveCamera(1 * controller.getCameraSpeed(), 0, 0);
            }
            case Q -> {
                controller.moveCamera(0, 1 * controller.getCameraSpeed(), 0);
            }
            case E -> {
                controller.moveCamera(0, -1 * controller.getCameraSpeed(), 0);
            }
            case DELETE -> {
                deleteSelectedActor();
            }
        }
    }


    public void startRotateCamera(MouseEvent e) {
        mStartX = e.getX();
        mStartY = e.getY();

        //If starting to rotate the camera
        if(e.getButton() == MouseButton.SECONDARY) {
            mRotating = true;
        }
    }

    public void rotateCamera(MouseEvent e) {
        if(mRotating) {
            //Get position of viewport in window
            Point2D viewportOrigin = viewport.localToScreen(0, 0);
            //Update camera angle
            controller.rotateCamera((e.getX() - mStartX) * mCameraRotationRate, -(e.getY() - mStartY) * mCameraRotationRate, 0);
            mStartX = e.getX();
            mStartY = e.getY();

            //If mouse hit edge of viewport move it to other side
            //Call endRotate to skip next mouseEvent
            //Drag will be started again by else statement below
            if(e.getX() <= 0) {
                robot.mouseMove(viewportOrigin.getX() + viewport.getWidth() - 1, e.getScreenY());
                endRotateCamera(e);
            } else if(e.getX() >= viewport.getWidth()) {
                robot.mouseMove(viewportOrigin.getX() + 1, e.getScreenY());
                endRotateCamera(e);
            }

            if(e.getY() <= 0) {
                robot.mouseMove(e.getScreenX(), viewportOrigin.getY() + viewport.getHeight() + 1);
                endRotateCamera(e);
            } else if(e.getY() >= viewport.getHeight()) {
                robot.mouseMove(e.getScreenX(), viewportOrigin.getY() - 1);
                endRotateCamera(e);
            }
        } else {
            startRotateCamera(e);
        }
    }

    private void endRotateCamera(MouseEvent e) {
        if(e.getButton() == MouseButton.SECONDARY) {
            mRotating = false;
        }
    }

    private void setCameraType(String cameraType) {
        controller.setCameraType(cameraType);
        viewport.setCamera(controller.getCamera());
    }

    private void selectClickedActor(MouseEvent e) {
        if(e.getButton() == MouseButton.PRIMARY) {
            //Get clicked node then get actor that contains the node
            Actor clickedActor = controller.getActorByNode(e.getPickResult().getIntersectedNode());

            //Update selected actor
            updateSelection(clickedActor);
            outline.getSelectionModel().select(clickedActor);
        }
    }

    public void updateSelection(Actor newVal) {
        Actor oldVal = controller.getSelectedActor();

        //If an actor was previously selected
        if(oldVal != null) {
            //get attributes to unlink
            Translate oldLocation = oldVal.getLocation();
            Rotate oldXRotation = oldVal.getRotateX();
            Rotate oldYRotation = oldVal.getRotateY();
            Rotate oldZRotation = oldVal.getRotateZ();
            Scale oldScale = oldVal.getScale();

            //Unlink attributes from text fields
            transformTFs[0].textProperty().unbindBidirectional(oldLocation.xProperty());
            transformTFs[1].textProperty().unbindBidirectional(oldLocation.yProperty());
            transformTFs[2].textProperty().unbindBidirectional(oldLocation.zProperty());
            transformTFs[3].textProperty().unbindBidirectional(oldXRotation.angleProperty());
            transformTFs[4].textProperty().unbindBidirectional(oldYRotation.angleProperty());
            transformTFs[5].textProperty().unbindBidirectional(oldZRotation.angleProperty());
            transformTFs[6].textProperty().unbindBidirectional(oldScale.xProperty());
            transformTFs[7].textProperty().unbindBidirectional(oldScale.yProperty());
            transformTFs[8].textProperty().unbindBidirectional(oldScale.zProperty());

            //Unlink mesh specific attributes
            if(oldVal instanceof Mesh mesh) {
                //Link mesh specific attributes
                specularColorPicker.setDisable(true);
                specularPowerField.textProperty().unbindBidirectional(mesh.getSpecularPower());
                specularPowerField.setDisable(true);
                specularPowerField.clear();
            }
        }
        //Update selected actor
        controller.setSelectedActor(newVal);

        //If an actor was just selected
        if(newVal != null) {
            //Enable text fields
            for(TextField tf : transformTFs)
                tf.setDisable(false);

            actorColorPicker.setDisable(false);

            //Update color picker
            actorColorPicker.valueProperty().setValue(newVal.getColor());

            //Get attributes to link
            NumberStringConverter converter = new NumberStringConverter();
            Translate location = newVal.getLocation();
            Rotate xRotation = newVal.getRotateX();
            Rotate yRotation = newVal.getRotateY();
            Rotate zRotation = newVal.getRotateZ();
            Scale scale = newVal.getScale();

            //Link attributes to text fields
            transformTFs[0].textProperty().bindBidirectional(location.xProperty(), converter);
            transformTFs[1].textProperty().bindBidirectional(location.yProperty(), converter);
            transformTFs[2].textProperty().bindBidirectional(location.zProperty(), converter);
            transformTFs[3].textProperty().bindBidirectional(xRotation.angleProperty(), converter);
            transformTFs[4].textProperty().bindBidirectional(yRotation.angleProperty(), converter);
            transformTFs[5].textProperty().bindBidirectional(zRotation.angleProperty(), converter);
            transformTFs[6].textProperty().bindBidirectional(scale.xProperty(), converter);
            transformTFs[7].textProperty().bindBidirectional(scale.yProperty(), converter);
            transformTFs[8].textProperty().bindBidirectional(scale.zProperty(), converter);

            if(newVal instanceof Mesh mesh) {
                //Link mesh specific attributes
                specularColorPicker.valueProperty().set(mesh.getSpecular());
                specularColorPicker.setDisable(false);
                specularPowerField.setDisable(false);
                specularPowerField.textProperty().bindBidirectional(mesh.getSpecularPower(), converter);
            } else {
                //If light selected disable mesh specific text fields
                specularColorPicker.setDisable(true);
            }
        } else {
            //Disable text fields if nothing selected
            for(TextField tf : transformTFs) {
                tf.setDisable(true);
                tf.clear();
            }
            actorColorPicker.setDisable(true);
            specularColorPicker.setDisable(true);
            specularPowerField.setDisable(true);
            specularPowerField.clear();
        }
    }

    private void ensureCorrectInput(String oldVal, String newVal, TextField tf) {
        //Remove invalid characters
        if (!newVal.matches("\\-?\\d*\\.\\d*")) {
            tf.setText(newVal.replaceAll("[^(\\-?\\d*\\.\\d*)]|\\-[^\\d\\.]|\\.\\D", ""));
        }
    }

    public void newLevel() {
        controller.newLevel();
        viewport.setCamera(controller.getCamera());
        viewportRoot.getChildren().setAll(controller.getNodes());
    }

    public void loadLevel() {
        //Filter by .level files
        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("Real Engine Level(*.level)", "*.level");

        //Setup new fileChooser
        FileChooser loadLocation = new FileChooser();
        loadLocation.getExtensionFilters().add(filter);
        loadLocation.setInitialFileName("Unnamed.level");

        //Open file picker and load
        File path = loadLocation.showOpenDialog(null);
        if(path != null)
            controller.load(path.getPath());
        viewport.setCamera(controller.getCamera());
        viewportRoot.getChildren().setAll(controller.getNodes());
    }

    public void saveLevel() {
        //Filter by .level files
        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("Real Engine Level(*.level)", "*.level");

        //Setup new fileChooser
        FileChooser saveLocation = new FileChooser();
        saveLocation.getExtensionFilters().add(filter);
        saveLocation.setInitialFileName("Unnamed.level");

        //Open file picker and save
        File path = saveLocation.showSaveDialog(null);
        if(path != null)
            controller.save(saveLocation.showSaveDialog(null).getPath());
    }



}
