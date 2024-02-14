package com.tveu.engine.core.input;

import org.lwjgl.glfw.GLFW;

import static org.lwjgl.glfw.GLFW.GLFW_PRESS;
import static org.lwjgl.glfw.GLFW.GLFW_RELEASE;

public class MouseListener {


    private static final boolean[] mousePressed = new boolean[GLFW.GLFW_MOUSE_BUTTON_LAST];
    private static float mouseX = 0.0f;
    private static float mouseY = 0.0f;
    private static float mouseScrollX = 0.0f;
    private static float mouseScrollY = 0.0f;

    private static float lastMouseX = 0;
    private static float lastMouseY = 0;

    private static boolean firstMouse = true;


    public static void mouseButtonCallback(long window, int key, int action, int mods) {
        if (action == GLFW_PRESS) {
            mousePressed[key] = true;
        } else if (action == GLFW_RELEASE) {
            mousePressed[key] = false;
        }

    }

    public static void mousePosCallback(long window, double xposIn, double yposIn) {
        float xpos = (float) xposIn;
        float ypos = (float) yposIn;

        if (firstMouse) {
            lastMouseX = xpos;
            lastMouseY = ypos;
            firstMouse = false;
        }

        mouseX = xpos;
        mouseY = ypos;
    }

    public static void mouseScrollCallback(long window, double xoffset, double yoffset) {
        mouseScrollX = (float) xoffset;
        mouseScrollY = (float) yoffset;
    }

    public static float getMouseX() {
        return mouseX;
    }

    public static float getMouseY() {
        return mouseY;
    }

    public static float getMouseScrollX() {
        return mouseScrollX;
    }

    public static float getMouseScrollY() {
        return mouseScrollY;
    }

    public static float getLastMouseX() {
        return lastMouseX;
    }

    public static float getLastMouseY() {
        return lastMouseY;
    }
}
