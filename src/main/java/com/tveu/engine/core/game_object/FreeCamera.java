package com.tveu.engine.core.game_object;

import com.tveu.engine.core.CameraTransform;
import com.tveu.engine.core.component.CameraComponent;
import com.tveu.engine.core.input.KeyListener;

import static org.lwjgl.glfw.GLFW.*;

public class FreeCamera extends GameObject {


    private float speed = 7.0f;

    private float sensitivity = 0.1f;

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



    //TODO: Make Camera get changes formn CameraTransform
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
