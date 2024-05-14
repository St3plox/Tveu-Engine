package com.tveu.engine.rendering;

import com.tveu.engine.core.input.KeyListener;
import com.tveu.engine.core.input.MouseListener;
import com.tveu.engine.core.scene.MainTestScene;
import com.tveu.engine.core.scene.Scene;
import com.tveu.engine.core.utils.Time;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;

import static org.lwjgl.opengl.GL11C.*;
import static org.lwjgl.opengl.GL11C.GL_DEPTH_BUFFER_BIT;

public class CustomWindow {

    private static long windowHandle;
    private static int width;
    private static int height;
    private static String title;
    private static Scene scene;

    public static void createWindow(int width, int height, String title) {
        CustomWindow.width = width;
        CustomWindow.height = height;
        CustomWindow.title = title;

        // Initialize GLFW
        if (!GLFW.glfwInit()) {
            throw new IllegalStateException("Unable to initialize GLFW");
        }

        // Set error callback
        GLFWErrorCallback.createPrint(System.err).set();

        // Configure GLFW
        GLFW.glfwDefaultWindowHints();
        GLFW.glfwWindowHint(GLFW.GLFW_VISIBLE, GLFW.GLFW_FALSE);
        GLFW.glfwWindowHint(GLFW.GLFW_RESIZABLE, GLFW.GLFW_TRUE);

        // Create the window
        windowHandle = GLFW.glfwCreateWindow(width, height, title, 0, 0);
        if (windowHandle == 0) {
            throw new IllegalStateException("Failed to create GLFW window");
        }

        // Set callbacks
        GLFW.glfwSetCursorPosCallback(windowHandle, MouseListener::mousePosCallback);
        GLFW.glfwSetMouseButtonCallback(windowHandle, MouseListener::mouseButtonCallback);
        GLFW.glfwSetScrollCallback(windowHandle, MouseListener::mouseScrollCallback);
        GLFW.glfwSetKeyCallback(windowHandle, KeyListener::keyCallback);

        // Make the OpenGL context current
        GLFW.glfwMakeContextCurrent(windowHandle);

        // Enable v-sync
        GLFW.glfwSwapInterval(1);

        // Make the window visible
        GLFW.glfwShowWindow(windowHandle);

        // Set input mode
        GLFW.glfwSetInputMode(windowHandle, GLFW.GLFW_CURSOR, GLFW.GLFW_CURSOR_DISABLED);

        // Initialize OpenGL
        GL.createCapabilities();

        // Configure OpenGL
        GL11.glClearColor(0.0f, 0.0f, 0.0f, 0.0f); // Set clear color to black

        scene = new MainTestScene();
        scene.init();
    }

    public static void update(float dt) {
        glClearColor(0.1f, 0.1f, 0.1f, 1.0f);
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

        if (dt >= 0) {
            scene.update(dt);
        }

        GLFW.glfwSwapBuffers(windowHandle);
        GLFW.glfwPollEvents();
    }

    public static boolean shouldClose() {
        return GLFW.glfwWindowShouldClose(windowHandle);
    }

    public static void destroy() {
        GLFW.glfwDestroyWindow(windowHandle);
        GLFW.glfwTerminate();
    }

    public static int getWidth() {
        return width;
    }

    public static int getHeight() {
        return height;
    }

    public static String getTitle() {
        return title;
    }

    public static long getWindowHandle() {
        return windowHandle;
    }
}
