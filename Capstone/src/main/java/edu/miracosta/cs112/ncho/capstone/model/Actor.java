package edu.miracosta.cs112.ncho.capstone.model;

import javafx.geometry.Point3D;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Scale;
import javafx.scene.transform.Translate;

import java.io.*;

public abstract class Actor implements Serializable {
    protected String mName;
    protected Translate mLocation;
    protected Rotate mRotationX, mRotationY, mRotationZ;
    protected Scale mScale;

    public Actor() {
        mName = "";
        mLocation = new Translate(0, 0, 0);
        mRotationX = new Rotate();
        mRotationY = new Rotate();
        mRotationZ = new Rotate();

        //Setup each rotate on its own axis
        mRotationX.setAxis(new Point3D(1, 0, 0));
        mRotationY.setAxis(new Point3D(0, 1, 0));
        mRotationZ.setAxis(new Point3D(0, 0, 1));

        mScale = new Scale();
    }

    //Sets texture color for objects and illumination color for lights
    public abstract void setColor(Color newColor);
    public abstract Color getColor();

    public abstract Node getNode();

    public void setScaleX(double newScale) {
        mScale.setX(newScale);
    }

    public void setScaleY(double newScale) {
        mScale.setY(newScale);
    }

    public void setScaleZ(double newScale) {
        mScale.setZ(newScale);
    }

    public String toString() {
        return mName;
    }

    //The following are used by java's serialization mechanism
    private void writeObject(ObjectOutputStream out) throws IOException {
        //Write fields to output stream
        out.writeObject(mName);
        out.writeDouble(mLocation.getX());
        out.writeDouble(mLocation.getY());
        out.writeDouble(mLocation.getZ());
        out.writeDouble(mRotationX.getAngle());
        out.writeDouble(mRotationY.getAngle());
        out.writeDouble(mRotationZ.getAngle());
        out.writeDouble(mScale.getX());
        out.writeDouble(mScale.getY());
        out.writeDouble(mScale.getZ());
    }
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        //Read fields in from file
        mName = (String) in.readObject();
        mLocation = new Translate();
        mLocation.setX(in.readDouble());
        mLocation.setY(in.readDouble());
        mLocation.setZ(in.readDouble());

        mRotationX = new Rotate(in.readDouble());
        mRotationY = new Rotate(in.readDouble());
        mRotationZ = new Rotate(in.readDouble());
        mRotationX.setAxis(new Point3D(1, 0, 0));
        mRotationY.setAxis(new Point3D(0, 1, 0));
        mRotationZ.setAxis(new Point3D(0, 0, 1));

        mScale = new Scale();
        mScale.setX(in.readDouble());
        mScale.setY(in.readDouble());
        mScale.setZ(in.readDouble());
    }
    private void readObjectNoData() throws ObjectStreamException {
        throw new StreamCorruptedException("File corrupted.");
    }

    public Translate getLocation() {
        return mLocation;
    }

    public Rotate getRotateX() {
        return mRotationX;
    }

    public Rotate getRotateY() {
        return mRotationY;
    }

    public Rotate getRotateZ() {
        return mRotationZ;
    }

    public Scale getScale() {
        return mScale;
    }
}
