package com.tveu.engine.rendering;

import com.tveu.engine.core.input.KeyListener;
import org.joml.Matrix4f;
import org.joml.Vector3f;

import static org.lwjgl.glfw.GLFW.*;

public class Camera {

    public final static int SCR_WIDTH = 800;
    public final static int SCR_HEIGHT = 600;

    private final Vector3f pos, front, up = new Vector3f(0.0f, 1.0f, 0.0f), right, worldUp;

    private float speed = 7.5f;
    private float pitch;
    private float sensitivity = 0.1f;
    private float yaw;

    private float zoom = 90f;

    public Camera(Vector3f pos, Vector3f up, float yaw, float pitch) {
        this.pos = pos;
        this.worldUp = up;
        this.yaw = yaw;
        this.pitch = pitch;
        this.front = new Vector3f(0.0f, 0.0f, -1.0f);
        this.right = new Vector3f();

        updateVectors();
    }

    public Camera() {
        this(new Vector3f(0.0f, 0.0f, 0.0f), new Vector3f(0.0f, 1.0f, 0.0f), -90.0f, 0.0f);
    }

    public Matrix4f getViewMatrix() {
        return new Matrix4f().lookAt(pos, new Vector3f(pos).add(front), up);
    }

    private void updateVectors() {
        front.x = (float) (Math.cos(Math.toRadians(yaw)) * Math.cos(Math.toRadians(pitch)));
        front.y = (float) Math.sin(Math.toRadians(pitch));
        front.z = (float) (Math.sin(Math.toRadians(yaw)) * Math.cos(Math.toRadians(pitch)));
        front.normalize();

        right.set(front).cross(worldUp).normalize();
        up.set(right).cross(front).normalize();
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
            pos.add(new Vector3f(front).mul(speed * dt));

        if (KeyListener.isKeyPressed(GLFW_KEY_S))
            pos.sub(new Vector3f(front).mul(speed * dt));

        if (KeyListener.isKeyPressed(GLFW_KEY_A))
            pos.sub(new Vector3f(right).mul(speed * dt));

        if (KeyListener.isKeyPressed(GLFW_KEY_D))
            pos.add(new Vector3f(right).mul(speed * dt));
    }

    public void processMouseInput(float xOffset, float yOffset) {
        xOffset *= sensitivity;
        yOffset *= sensitivity;

        yaw += xOffset;
        pitch += yOffset;

        // Clamp pitch within a reasonable range
        if (pitch > 89.0f) {
            pitch = 89.0f;
        }
        if (pitch < -89.0f) {
            pitch = -89.0f;
        }

        updateVectors(); // Recalculate front, right, and up vectors
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
