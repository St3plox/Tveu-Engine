package com.tveu.engine.core;

import org.joml.Matrix4f;
import org.joml.Vector3f;

public class CameraTransform extends BaseTransform {
    private final Vector3f front, up, right, worldUp;

    private float yaw, pitch;

    public CameraTransform(Vector3f pos) {
        super(pos);

        up = new Vector3f(0.0f, 1.0f, 0.0f);
        worldUp = new Vector3f(up);

        yaw = -90.0f;
        pitch = 0.0f;

        front = new Vector3f(0.0f, 0.0f, -1.0f);
        right = new Vector3f();

        updateVectors();
    }

    public void moveForward(float scalar){
        pos.add(new Vector3f(front).mul(scalar));
    }

    public void moveBackward(float scalar){
        pos.sub(new Vector3f(front).mul(scalar));
    }

    public void moveLeft(float scalar){
        pos.sub(new Vector3f(right).mul(scalar));
    }

    public void moveRight(float scalar){
        pos.add(new Vector3f(right).mul(scalar));
    }

    public Matrix4f getViewMatrix() {
        return new Matrix4f().lookAt(pos, new Vector3f(pos).add(front), up);
    }


    //Function that is typically used for mouse input of a camera
    public void lookAt2D(float xOffset, float yOffset) {
        yaw += xOffset;
        pitch += yOffset;

        // Clamp pitch within a reasonable range
        if (pitch > 89.0f) {
            pitch = 89.0f;
        }
        if (pitch < -89.0f) {
            pitch = -89.0f;
        }

        updateVectors();
    }


    private void updateVectors() {
        front.x = (float) (Math.cos(Math.toRadians(yaw)) * Math.cos(Math.toRadians(pitch)));
        front.y = (float) Math.sin(Math.toRadians(pitch));
        front.z = (float) (Math.sin(Math.toRadians(yaw)) * Math.cos(Math.toRadians(pitch)));
        front.normalize();

        right.set(front).cross(worldUp).normalize();
        up.set(right).cross(front).normalize();
    }

    public float getYaw() {
        return yaw;
    }

    public float getPitch() {
        return pitch;
    }
}
