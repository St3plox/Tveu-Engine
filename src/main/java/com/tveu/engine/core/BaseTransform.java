package com.tveu.engine.core;

import org.joml.Vector3f;

public abstract class BaseTransform {

    protected Vector3f pos;

    public BaseTransform(Vector3f pos) {
        this.pos = pos;
    }

    public void translate(Vector3f axis){
        pos.add(axis);
    }

    public Vector3f getPos() {
        return pos;
    }

    public void setPos(Vector3f pos) {
        this.pos = pos;
    }
}
