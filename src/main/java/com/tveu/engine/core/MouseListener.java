package com.tveu.engine.core;

import org.lwjgl.glfw.GLFW;

import static org.lwjgl.glfw.GLFW.GLFW_PRESS;
import static org.lwjgl.glfw.GLFW.GLFW_RELEASE;

public class MouseListener {


    private static final boolean[] mousePressed = new boolean[GLFW.GLFW_MOUSE_BUTTON_LAST];
    static float mouseX = 0.0f;
    static float mouseY = 0.0f;
    static float mouseScrollX = 0.0f;
    static float mouseScrollY = 0.0f;


    public static void mouseButtonCallback(long window, int key, int action, int mods) {
        if (action == GLFW_PRESS) {
            mousePressed[key] = true;
        } else if (action == GLFW_RELEASE) {
            mousePressed[key] = false;
        }

    }

    public static void mousePosCallback(long window, double xpos, double ypos) {
        mouseX = (float) xpos;
        mouseY = (float) ypos;
    }

    public static void mouseScrollCallback(long window, double xoffset, double yoffset) {
        mouseScrollX = (float) xoffset;
        mouseScrollY = (float) yoffset;
    }
}
