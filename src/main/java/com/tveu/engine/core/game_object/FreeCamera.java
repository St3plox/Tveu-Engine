package com.tveu.engine.core.game_object;

import com.tveu.engine.core.BaseTransform;
import com.tveu.engine.core.CameraTransform;
import com.tveu.engine.core.component.CameraComponent;
import com.tveu.engine.core.input.KeyListener;
import com.tveu.engine.core.input.MouseListener;

import static org.lwjgl.glfw.GLFW.*;

public class FreeCamera extends GameObject {


    private float speed = 7.0f;

    private float sensitivity = 0.1f;

    private float lastMouseX = MouseListener.getLastMouseX();
    private float lastMouseY = MouseListener.getLastMouseY();

    public FreeCamera() {
        super();
    }

    public FreeCamera(BaseTransform transform) {
        super(transform);
    }

    @Override
    public void init() {
        super.init();
        if (!(transform instanceof CameraTransform)) {
            throw new IllegalArgumentException("FreeCamera objects must have a CameraTransform component");
            //Kludge (костыль), needs to be deleted in future
        }

        var cameraComponent = getComponent(CameraComponent.class);
        if (cameraComponent == null) {
            throw new RuntimeException("FreeCamera object must have the CameraComponent");
        }
    }

    @Override
    public void update(float dt) {
        super.update(dt);

        float xoffset = MouseListener.getMouseX() - lastMouseX;
        float yoffset = lastMouseY - MouseListener.getMouseY(); // reversed since y-coordinates go from bottom to top

        processKeyboardInput(dt);
        processMouseInput(xoffset, yoffset);

        lastMouseX = MouseListener.getMouseX();
        lastMouseY = MouseListener.getMouseY();
    }
    private void processKeyboardInput(float dt) {
        if (KeyListener.isKeyPressed(GLFW_KEY_W))
            if (KeyListener.isKeyPressed(GLFW_KEY_W))
                ((CameraTransform) transform).moveForward(speed * dt);

        if (KeyListener.isKeyPressed(GLFW_KEY_S))
            ((CameraTransform) transform).moveBackward(speed * dt);

        if (KeyListener.isKeyPressed(GLFW_KEY_A))
            ((CameraTransform) transform).moveLeft(speed * dt);

        if (KeyListener.isKeyPressed(GLFW_KEY_D))
            ((CameraTransform) transform).moveRight(speed * dt);
    }

    private void processMouseInput(float xOffset, float yOffset) {
        ((CameraTransform) transform).lookAt2D(xOffset * sensitivity, yOffset * sensitivity);
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
        if (sensitivity < 0)
            throw new IllegalArgumentException("Sens cannot be < 0");

        this.sensitivity = sensitivity;
    }
}
