package com.tveu.engine.core;

import org.joml.Quaternionf;
import org.joml.Vector3f;

public class Transform  extends BaseTransform{

    private Vector3f scale;
    private Quaternionf rotation;

    public Transform(Vector3f pos, Vector3f scale, Quaternionf rotation) {
        super(pos);
        this.scale = scale;
        this.rotation = rotation;
    }

    public Transform(Vector3f pos) {
        this(pos, new Vector3f(1.0f), new Quaternionf());
    }

    public void scale(Vector3f factors) {
        scale.mul(factors);
    }

    public void rotate(float angle, Vector3f axis) {
        Quaternionf rotationDelta = new Quaternionf().rotateAxis(angle, axis);
        rotation.mul(rotationDelta);
    }

    public Vector3f getScale() {
        return new Vector3f(scale);
    }

    public Quaternionf getRotation() {
        return new Quaternionf(rotation);
    }


    public void setScale(Vector3f scale) {
        this.scale = scale;
    }

    public void setRotation(Quaternionf rotation) {
        this.rotation = rotation;
    }
}

