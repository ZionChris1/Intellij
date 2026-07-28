package edu.miracosta.cs112.ncho.capstone.controller;

import edu.miracosta.cs112.ncho.capstone.model.Actor;
import edu.miracosta.cs112.ncho.capstone.model.Mesh;
import edu.miracosta.cs112.ncho.capstone.model.Model;
import javafx.beans.property.ObjectProperty;
import javafx.collections.ObservableList;
import javafx.geometry.Point3D;
import javafx.scene.Camera;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class Controller {

    private static Controller theInstance;
    private ObjectProperty<String> spawnType;
    private Model mModel;
    private double mDragStartX;
    private TextField mSelectedTF;

    private Controller() {
        mModel = new Model();
    }

    public static Controller getInstance() {
        if(theInstance == null)
            theInstance = new Controller();

        return theInstance;
    }
    public void setColor(Color newColor) {
        mModel.setColor(newColor);
    }

    public void spawnActor(double x, double y, double z) {
        mModel.spawnActor(x, y, z, spawnType.get());
    }

    public void deleteSelectedActor() {
        mModel.deleteSelectedActor();
    }

    public ObservableList<Actor> getActors() {
        return mModel.getActors();
    }

    public void setSelectedActor(Actor newVal) {
        mModel.setSelectedActor(newVal);
    }

    public ArrayList<Node> getNodes() {
        ArrayList<Node> nodes = new ArrayList<>();
        for(Actor a : getActors()) {
            nodes.add(a.getNode());
        }
        return nodes;
    }

    public Camera getCamera() {
        return mModel.getCamera();
    }

    public Actor getSelectedActor() {
        return mModel.getSelectedActor();
    }

    public Actor getActorByNode(Node node) {
        for(Actor actor : mModel.getActors())
            if(actor.getNode() == node)
                return actor;
        return null;
    }

    public void setSpecular(Color newVal) {
        if(getSelectedActor() instanceof Mesh mesh)
            mesh.setSpecular(newVal);
    }
    public void save(String filename) {
        mModel.save(filename);
    }

    public ObservableList<String> getActorTypes() {
        return mModel.getActorTypes();
    }

    public double getCameraSpeed() {
        return mModel.getCameraMovementSpeed();
    }

    public void setSpawnTypeProperty(ObjectProperty<String> valueProperty) {
        spawnType = valueProperty;
    }

    public void load(String filename) {
        mModel.load(filename);
    }

    public void handleMouseDragStart(MouseEvent e, TextField tf) {
        mDragStartX = e.getX();
        mSelectedTF = tf;
    }

    public void handleMouseDrag(MouseEvent e) {
        mSelectedTF.setText(Double.toString(Double.parseDouble(mSelectedTF.getText().replace(",", "")) + e.getX() - mDragStartX));
        mDragStartX = e.getX();
    }

    public void setFieldOfView(double newFOV) {
        mModel.setFieldOfView(newFOV);
    }

    public void newLevel() {
        mModel.newLevel();
    }

    public void setCameraMoveSpeed(double cameraSpeed) {
        mModel.setCameraMovementSpeed(cameraSpeed);
    }

    public void moveCamera(double x, double y, double z) {
        Camera camera = mModel.getCamera();

        Point3D newPos = camera.localToScene(x, y, z);

        camera.setTranslateX(newPos.getX());
        camera.setTranslateY(newPos.getY());
        camera.setTranslateZ(newPos.getZ());
    }

    public void rotateCamera(double x, double y, double z) {
        mModel.rotateCamera(x, y, z);
    }

    public void setCameraType(String type) {
        mModel.setCameraType(type);
    }

    public double getCameraFOV() {
        return mModel.getCameraFOV();
    }

    public Color getBackgroundColor() {
        return  mModel.getBackgroundColor();
    }

    public void setBackgroundColor(Color newBackgroundColor) {
        mModel.setBackgroundColor(newBackgroundColor);
    }
}
