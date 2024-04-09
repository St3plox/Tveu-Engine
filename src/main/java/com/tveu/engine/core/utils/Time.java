package com.tveu.engine.core.utils;

import static org.lwjgl.glfw.GLFW.glfwGetTime;

public class Time {

    public static float getTime() {
        return (float) glfwGetTime();
    }

}
