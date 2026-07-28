package edu.miracosta.cs112.ncho.capstone.model;

import javafx.beans.property.DoubleProperty;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.*;

import java.io.*;

public class Mesh extends Actor {
    private Shape3D mShape;
    private PhongMaterial mMaterial;
    private String mType;

    public Mesh(double x, double y, double z, String type, Color diffuse) {
        mMaterial = new PhongMaterial();

        //Setup shape based on type provided
        switch (type) {
            case "Sphere" -> {
                mShape = new Sphere();
            }
            case "Cube" -> {
                mShape = new Box();
            }
            case "Cylinder" -> {
                mShape = new Cylinder();
            }
        }

        mShape.setMaterial(mMaterial);
        //Add transforms to shape
        mShape.getTransforms().addAll(mLocation, mRotationX, mRotationY, mRotationZ, mScale);


        mLocation.setX(x);
        mLocation.setY(y);
        mLocation.setZ(z);
        mScale.setX(10);
        mScale.setY(10);
        mScale.setZ(10);

        //If color is not null set material color
        if(diffuse != null)
            mMaterial.setDiffuseColor(diffuse);

        //set Shine color
        mMaterial.setSpecularColor(Color.WHITE);
        mName = type;
        mType = type;
    }

    public void setColor(Color newColor) {
        mMaterial.setDiffuseColor(newColor);
    }

    public Color getColor() {
        return mMaterial.getDiffuseColor();
    }

    public Color getSpecular() {
        return mMaterial.getSpecularColor();
    }

    public Node getNode() {
        return mShape;
    }

    @Override
    public void setScaleX(double newScale) {
        super.setScaleX(newScale);
    }

    @Override
    public void setScaleY(double newScale) {
        super.setScaleY(newScale);
    }

    @Override
    public void setScaleZ(double newScale) {
        super.setScaleZ(newScale);
    }

    public String toString() {
        return mName;
    }

    public void setSpecular(Color newVal) {
        mMaterial.setSpecularColor(newVal);
    }

    public DoubleProperty getSpecularPower() {
        return mMaterial.specularPowerProperty();
    }

    //The following are used by java's serialization mechanism
    private void writeObject(ObjectOutputStream out) throws IOException {
        //Write object type and material to mesh
        out.writeObject(mName);
        out.writeObject(mType);
        out.writeDouble(mMaterial.getDiffuseColor().getRed());
        out.writeDouble(mMaterial.getDiffuseColor().getGreen());
        out.writeDouble(mMaterial.getDiffuseColor().getBlue());
        out.writeDouble(mMaterial.getSpecularColor().getRed());
        out.writeDouble(mMaterial.getSpecularColor().getGreen());
        out.writeDouble(mMaterial.getSpecularColor().getBlue());
        out.writeDouble(mMaterial.getSpecularPower());
    }
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        //Read name, type, and material from file
        mName = (String) in.readObject();
        mType = (String) in.readObject();
        mMaterial = new PhongMaterial();
        mMaterial.setDiffuseColor(Color.color(in.readDouble(), in.readDouble(), in.readDouble()));
        mMaterial.setSpecularColor(Color.color(in.readDouble(), in.readDouble(), in.readDouble()));
        mMaterial.setSpecularPower(in.readDouble());

        //Create new shape based on type
        switch (mType) {
            case "Sphere" -> {
                mShape = new Sphere();
            }
            case "Cube" -> {
                mShape = new Box();
            }
            case "Cylinder" -> {
                mShape = new Cylinder();
            }
        }

        //Add transforms and material to shape
        mShape.setMaterial(mMaterial);
        mShape.getTransforms().addAll(mLocation, mRotationX, mRotationY, mRotationZ, mScale);
    }

    private void readObjectNoData() throws ObjectStreamException {
        throw new StreamCorruptedException("File corrupted.");
    }
}
