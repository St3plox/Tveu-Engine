package com.tveu.engine.rendering;

import com.tveu.engine.core.CameraTransform;
import com.tveu.engine.core.Transform;
import com.tveu.engine.core.component.CameraComponent;
import com.tveu.engine.core.component.VertexShapeComponent;
import com.tveu.engine.core.game_object.FreeCamera;
import com.tveu.engine.core.game_object.GameObject;
import com.tveu.engine.core.input.KeyListener;
import com.tveu.engine.core.input.MouseListener;
import com.tveu.engine.core.scene.CameraScene;
import com.tveu.engine.core.scene.LightTestScene;
import com.tveu.engine.core.scene.Scene;
import com.tveu.engine.core.utils.Time;
import com.tveu.engine.rendering.objects.RenderObjects;
import org.joml.Vector3f;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;
import org.lwjgl.system.MemoryStack;

import java.nio.IntBuffer;
import java.util.Objects;

import static java.sql.Types.NULL;
import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11C.*;
import static org.lwjgl.system.MemoryStack.stackPush;

public class Window {

    private static long window;

    public static void createWindow(int width, int height, String title) {
        init(width, height, title);
        loop();

        glfwFreeCallbacks(window);
        glfwDestroyWindow(window);

        // Terminate GLFW and free the error callback
        glfwTerminate();
        Objects.requireNonNull(glfwSetErrorCallback(null)).free();
    }

    private static void init(int width, int height, String title) {
        // Setup an error callback. The default implementation
        // will print the error message in System.err.
        GLFWErrorCallback.createPrint(System.err).set();

        // Initialize GLFW. Most GLFW functions will not work before doing this.
        if (!glfwInit())
            throw new IllegalStateException("Unable to initialize GLFW");

        // Configure GLFW
        glfwDefaultWindowHints(); // optional, the current window hints are already the default
        glfwWindowHint(GLFW_VISIBLE, GLFW_TRUE);
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);
        glfwWindowHint(GLFW_MAXIMIZED, GLFW_FALSE);

        // the window will be resizable

        // Create the window
        window = glfwCreateWindow(width, height, title, NULL, NULL);

        if (window == NULL)
            throw new RuntimeException("Failed to create the GLFW window");

        // Setup a key callback. It will be called every time a key is pressed, repeated or released.
        glfwSetKeyCallback(window, (window, key, scancode, action, mods) -> {
            if (key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE)
                glfwSetWindowShouldClose(window, true); // We will detect this in the rendering loop
        });

        // Get the thread stack and push a new frame
        try (MemoryStack stack = stackPush()) {
            IntBuffer pWidth = stack.mallocInt(1); // int*
            IntBuffer pHeight = stack.mallocInt(1); // int*

            // Get the window size passed to glfwCreateWindow
            glfwGetWindowSize(window, pWidth, pHeight);

            // Get the resolution of the primary monitor
            GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());

            // Center the window
            assert vidmode != null;
            glfwSetWindowPos(
                    window,
                    (vidmode.width() - pWidth.get(0)) / 2,
                    (vidmode.height() - pHeight.get(0)) / 2
            );
        } // the stack frame is popped automatically

        // Make the OpenGL context current
        glfwMakeContextCurrent(window);
        // Enable v-sync
        glfwSwapInterval(1);

        // Make the window visible
        glfwShowWindow(window);
        // This line is critical for LWJGL's interoperation with GLFW's
        // OpenGL context, or any context that is managed externally.
        // LWJGL detects the context that is current in the current thread,
        // creates the GLCapabilities instance and makes the OpenGL
        // bindings available for use.
        GL.createCapabilities();
        glEnable(GL_DEPTH_TEST);

    }

    private static void loop() {

        float beginTime = Time.getTime();
        float endTime;
        float dt = -1.0f;

        // This line is critical for LWJGL's interoperation with GLFW's
        // OpenGL context, or any context that is managed externally.
        // LWJGL detects the context that is current in the current thread,
        // creates the GLCapabilities instance and makes the OpenGL
        // bindings available for use.
        GL.createCapabilities();

        // Set the clear color
        glClearColor(0.1f, 0.1f, 0.1f, 1.0f);

        var scene = new LightTestScene();
        scene.init();

        //         Scene scene = new LightTestScene();
        //        scene.init();Run the rendering loop until the user has attempted to close
        // the window or has pressed the ESCAPE key.
        while (!glfwWindowShouldClose(window)) {
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear the framebuffer
            // swap the color buffers

            glfwSetCursorPosCallback(window, MouseListener::mousePosCallback);
            glfwSetMouseButtonCallback(window, MouseListener::mouseButtonCallback);
            glfwSetScrollCallback(window, MouseListener::mouseScrollCallback);
            glfwSetKeyCallback(window, KeyListener::keyCallback);

            glfwSetInputMode(window, GLFW.GLFW_CURSOR, GLFW.GLFW_CURSOR_DISABLED);


            if (dt >= 0) {
                scene.update(dt);
            }


            endTime = Time.getTime();
            dt = endTime - beginTime;
            beginTime = endTime;

            glfwSwapBuffers(window);
            glfwPollEvents();

        }

    }

}
