package com.tveu.engine.core;

import org.lwjgl.glfw.GLFW;

import static org.lwjgl.glfw.GLFW.GLFW_PRESS;
import static org.lwjgl.glfw.GLFW.GLFW_RELEASE;

public class KeyListener {
    private static final boolean[] keyPressed = new boolean[GLFW.GLFW_KEY_LAST];

    public static void keyCallback(long window, int key, int scancode, int action, int mods) {
        if (action == GLFW_PRESS) {
            keyPressed[key] = true;
        } else if (action == GLFW_RELEASE) {
            keyPressed[key] = false;
        }
    }

    public static boolean isKeyPressed(int key) {
        return keyPressed[key];
    }
}
