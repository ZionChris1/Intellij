package edu.miracosta.cs112.ncho.capstone.model;

import javafx.scene.*;
import javafx.scene.paint.Color;

import java.io.*;

public class LightSource extends Actor implements Serializable {
    private LightBase light;
    private String mLightType;
    public LightSource(double x, double y, double z, String type, Color color) {
        mLightType = type;

        //Create new light based on type provided
        switch (type) {
            case "Point Light" -> {
                light = new PointLight();
            }
            case "Directional Light" -> {
                light = new DirectionalLight();
            }
            case "Spot Light" -> {
                light = new SpotLight();
            }
            case "Ambient Light" -> {
                light = new AmbientLight();
            }
        }


        if(color != null)
            light.setColor(color);

        //Add transforms to light
        light.getTransforms().addAll(mLocation, mRotationX, mRotationY, mRotationZ, mScale);

        mLocation.setX(x);
        mLocation.setY(y);
        mLocation.setZ(z);
        mName = type;
    }

    public void setColor(Color newColor) {
        light.setColor(newColor);
    }

    public Color getColor() {
        return light.getColor();
    }

    public Node getNode() {
        return light;
    }

    public String toString() {
        return mName;
    }

    //The following are used by java's serialization mechanism
    private void writeObject(ObjectOutputStream out) throws IOException {
        out.writeObject(mLightType);
        out.writeDouble(light.getColor().getRed());
        out.writeDouble(light.getColor().getGreen());
        out.writeDouble(light.getColor().getBlue());
    }
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        //Get light type from file
        mLightType = (String) in.readObject();

        //Create light based on type given
        switch (mLightType) {
            case "Point Light" -> {
                light = new PointLight();
            }
            case "Directional Light" -> {
                light = new DirectionalLight();
            }
            case "Spot Light" -> {
                light = new SpotLight();
            }
            case "Ambient Light" -> {
                light = new AmbientLight();
            }
        }
        //Load color and connect it and transforms to light
        light.setColor(Color.color(in.readDouble(), in.readDouble(), in.readDouble()));
        light.getTransforms().addAll(mLocation, mRotationX, mRotationY, mRotationZ, mScale);
    }
    private void readObjectNoData() throws ObjectStreamException {
        throw new StreamCorruptedException("File corrupted.");
    }
}
