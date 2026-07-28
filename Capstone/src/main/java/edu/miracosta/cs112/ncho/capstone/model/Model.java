package edu.miracosta.cs112.ncho.capstone.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Point3D;
import javafx.scene.Camera;
import javafx.scene.ParallelCamera;
import javafx.scene.PerspectiveCamera;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.transform.Rotate;

import java.io.*;

public class Model {
    private static final String[] DEFAULT_ACTOR_TYPES = {"Sphere", "Cube", "Cylinder", "Point Light", "Spot Light", "Directional Light", "Ambient Light"};
    public static final Color DEFAULT_BACKGROUND_COLOR = Color.rgb(64, 64, 64);
    public static final double DEFAULT_FAR_CLIP = 100000, DEFAULT_FIELD_OF_VIEW = 30;
    private ObservableList<Actor> mActors;
    private PhongMaterial mCurrentMaterial;
    private Color mCurrentColor, mBackgroundColor;
    private Actor mSelectedActor;
    private ObservableList<String> mActorTypes;
    private Camera mCamera;
    private Rotate mCameraX, mCameraY, mCameraZ;
    private double mCameraMovementSpeed, mFOV;
    public Model() {
        mActors = FXCollections.observableArrayList();
        mCurrentMaterial = new PhongMaterial();
        mActorTypes = FXCollections.observableArrayList();
        mActorTypes.addAll(DEFAULT_ACTOR_TYPES);
        mCameraX = new Rotate();
        mCameraY = new Rotate();
        mCameraZ = new Rotate();

        mCameraX.setAxis(new Point3D(0, 1, 0));
        mCameraY.setAxis(new Point3D(1, 0, 0));
        mCameraZ.setAxis(new Point3D(0, 0, 1));
        newLevel();
    }

    public Actor getSelectedActor() {
        return mSelectedActor;
    }

    public void setColor(Color newColor) {
        if(mSelectedActor != null)
            mSelectedActor.setColor(newColor);
        else {
            mCurrentColor = newColor;
            mCurrentMaterial.setDiffuseColor(newColor);
        }
    }

    public void setSelectedActor(Actor selected) {
        mSelectedActor = selected;
    }

    public ObservableList<Actor> getActors() {
        return mActors;
    }

    public void spawnActor(double x, double y, double z, String type) {
        if(type.contains("Light")) {
            mActors.add(new LightSource(x, y, z, type, mCurrentColor));
        } else {
            mActors.add(new Mesh(x, y, z, type, mCurrentColor));
        }
        mSelectedActor = mActors.get(mActors.size() - 1);
    }

    public void deleteSelectedActor() {
        mActors.remove(mSelectedActor);
        mSelectedActor = null;
    }

    public Camera getCamera() {
        return mCamera;
    }

    public void save(String filename) {
        try {
            ObjectOutputStream outputFile = new ObjectOutputStream(new FileOutputStream(filename));

            Actor[] temp = new Actor[mActors.size()];
            mActors.toArray(temp);
            outputFile.writeObject(temp);
            outputFile.writeBoolean(mCamera instanceof PerspectiveCamera);
            outputFile.writeDouble(mCamera.getTranslateX());
            outputFile.writeDouble(mCamera.getTranslateY());
            outputFile.writeDouble(mCamera.getTranslateZ());
            outputFile.writeDouble(mCameraX.getAngle());
            outputFile.writeDouble(mCameraY.getAngle());
            outputFile.writeDouble(mCameraZ.getAngle());
            outputFile.writeDouble(mCamera.getRotate());
            outputFile.writeDouble(mCamera.getScaleX());
            outputFile.writeDouble(mCamera.getScaleY());
            outputFile.writeDouble(mCamera.getScaleZ());
            outputFile.writeDouble(mCamera.getFarClip());
            outputFile.writeDouble(mCamera.getNearClip());
            outputFile.writeDouble(mBackgroundColor.getRed());
            outputFile.writeDouble(mBackgroundColor.getGreen());
            outputFile.writeDouble(mBackgroundColor.getBlue());

            outputFile.close();
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    public void load(String filename) {
        try {
            ObjectInputStream inputFile = new ObjectInputStream(new FileInputStream(filename));

            Actor[] temp = (Actor[]) inputFile.readObject();

            mActors.addAll(temp);

            mActors.toArray(temp);
            if(inputFile.readBoolean()) {
                mCamera = new PerspectiveCamera(true);
            } else {
                mCamera = new ParallelCamera();
            }

            mCamera.getTransforms().addAll(mCameraX, mCameraY, mCameraZ);

            mCamera.setTranslateX(inputFile.readDouble());
            mCamera.setTranslateY(inputFile.readDouble());
            mCamera.setTranslateZ(inputFile.readDouble());
            mCameraX.setAngle(inputFile.readDouble());
            mCameraY.setAngle(inputFile.readDouble());
            mCameraZ.setAngle(inputFile.readDouble());
            mCamera.setRotate(inputFile.readDouble());
            mCamera.setScaleX(inputFile.readDouble());
            mCamera.setScaleY(inputFile.readDouble());
            mCamera.setScaleZ(inputFile.readDouble());
            mCamera.setFarClip(inputFile.readDouble());
            mCamera.setNearClip(inputFile.readDouble());
            mBackgroundColor = Color.color(inputFile.readDouble(), inputFile.readDouble(), inputFile.readDouble());
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    public ObservableList<String> getActorTypes() {
        return mActorTypes;
    }

    public void newLevel() {
        mActors.clear();
        mCamera = new PerspectiveCamera(true);
        mCamera.getTransforms().addAll(mCameraX, mCameraY, mCameraZ);
        mCameraX.setAngle(0);
        mCameraY.setAngle(0);
        mCameraZ.setAngle(0);
        mCamera.setFarClip(DEFAULT_FAR_CLIP);
        mCameraMovementSpeed = 10;
        mBackgroundColor = DEFAULT_BACKGROUND_COLOR;
        mSelectedActor = null;
        mFOV = DEFAULT_FIELD_OF_VIEW;
    }

    public void setFieldOfView(double newFOV) {
        mFOV = newFOV;
        if(mCamera instanceof PerspectiveCamera perspectiveCamera)
            perspectiveCamera.setFieldOfView(mFOV);
    }

    public void setCameraType(String type) {
        Camera oldCamera = mCamera;
        if("Perspective".equals(type)) {
            mCamera = new PerspectiveCamera(true);
            ((PerspectiveCamera)mCamera).setFieldOfView(mFOV);
        }
        else
            mCamera = new ParallelCamera();

        mCamera.getTransforms().addAll(mCameraX, mCameraY, mCameraZ);
        mCamera.setTranslateX(oldCamera.getTranslateX());
        mCamera.setTranslateY(oldCamera.getTranslateY());
        mCamera.setTranslateZ(oldCamera.getTranslateZ());
    }

    public double getCameraMovementSpeed() {
        return mCameraMovementSpeed;
    }

    public void setCameraMovementSpeed(double newCameraMovementSpeed) {
        mCameraMovementSpeed = newCameraMovementSpeed;
    }

    public void rotateCamera(double x, double y, double z) {
        mCameraX.setAngle(mCameraX.getAngle() + x);
        mCameraY.setAngle(mCameraY.getAngle() + y);
        mCameraZ.setAngle(mCameraZ.getAngle() + z);
    }

    public double getCameraFOV() {
        return mFOV;
    }

    public Color getBackgroundColor() {
        return mBackgroundColor;
    }

    public void setBackgroundColor(Color mBackgroundColor) {
        this.mBackgroundColor = mBackgroundColor;
    }
}
