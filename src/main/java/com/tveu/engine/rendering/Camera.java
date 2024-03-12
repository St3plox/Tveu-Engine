package com.tveu.engine.rendering;

import com.tveu.engine.core.CameraTransform;
import com.tveu.engine.core.input.KeyListener;
import org.joml.Vector3f;

import static org.lwjgl.glfw.GLFW.*;

public class Camera {

    public final CameraTransform transform;

    public final static int SCR_WIDTH = 800;
    public final static int SCR_HEIGHT = 600;

    private float speed = 7.5f;
    private float sensitivity = 0.1f;
    private float zoom = 90f;

    public Camera(CameraTransform transform) {
        this.transform = transform;
    }

    public Camera() {
        this(new CameraTransform(new Vector3f(0.0f, 0.0f, 0.0f)));
    }

    void ProcessMouseScroll(float yoffset) {
        zoom -= (float) yoffset;
        if (zoom < 1.0f)
            zoom = 1.0f;
        if (zoom > 120f)
            zoom = 120f;
    }


    public void processKeyboardInput(float dt) {
        if (KeyListener.isKeyPressed(GLFW_KEY_W))
            transform.moveForward(speed * dt);

        if (KeyListener.isKeyPressed(GLFW_KEY_S))
            transform.moveBackward(speed * dt);

        if (KeyListener.isKeyPressed(GLFW_KEY_A))
            transform.moveLeft(speed * dt);

        if (KeyListener.isKeyPressed(GLFW_KEY_D))
            transform.moveRight(speed * dt);
    }

    public void processMouseInput(float xOffset, float yOffset) {
        transform.lookAt2D(xOffset * sensitivity, yOffset * sensitivity);
    }


    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public float getSensitivity() {
        return sensitivity;
    }

    public void setSensitivity(float sensitivity) {
        this.sensitivity = sensitivity;
    }

    public float getZoom() {
        return zoom;
    }
}
