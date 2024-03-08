package com.tveu.engine.core;

import com.tveu.engine.core.input.MouseListener;
import com.tveu.engine.rendering.Camera;
import com.tveu.engine.rendering.Shader;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.lwjgl.BufferUtils;

import java.nio.FloatBuffer;

import static org.lwjgl.opengl.GL15C.*;
import static org.lwjgl.opengl.GL20C.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL20C.glVertexAttribPointer;
import static org.lwjgl.opengl.GL30C.glBindVertexArray;
import static org.lwjgl.opengl.GL30C.glGenVertexArrays;

public class LightTestScene extends Scene {

    float[] vertices = {
            -0.5f, -0.5f, -0.5f, 0.0f, 0.0f, -1.0f,
            0.5f, -0.5f, -0.5f, 0.0f, 0.0f, -1.0f,
            0.5f, 0.5f, -0.5f, 0.0f, 0.0f, -1.0f,
            0.5f, 0.5f, -0.5f, 0.0f, 0.0f, -1.0f,
            -0.5f, 0.5f, -0.5f, 0.0f, 0.0f, -1.0f,
            -0.5f, -0.5f, -0.5f, 0.0f, 0.0f, -1.0f,

            -0.5f, -0.5f, 0.5f, 0.0f, 0.0f, 1.0f,
            0.5f, -0.5f, 0.5f, 0.0f, 0.0f, 1.0f,
            0.5f, 0.5f, 0.5f, 0.0f, 0.0f, 1.0f,
            0.5f, 0.5f, 0.5f, 0.0f, 0.0f, 1.0f,
            -0.5f, 0.5f, 0.5f, 0.0f, 0.0f, 1.0f,
            -0.5f, -0.5f, 0.5f, 0.0f, 0.0f, 1.0f,

            -0.5f, 0.5f, 0.5f, -1.0f, 0.0f, 0.0f,
            -0.5f, 0.5f, -0.5f, -1.0f, 0.0f, 0.0f,
            -0.5f, -0.5f, -0.5f, -1.0f, 0.0f, 0.0f,
            -0.5f, -0.5f, -0.5f, -1.0f, 0.0f, 0.0f,
            -0.5f, -0.5f, 0.5f, -1.0f, 0.0f, 0.0f,
            -0.5f, 0.5f, 0.5f, -1.0f, 0.0f, 0.0f,

            0.5f, 0.5f, 0.5f, 1.0f, 0.0f, 0.0f,
            0.5f, 0.5f, -0.5f, 1.0f, 0.0f, 0.0f,
            0.5f, -0.5f, -0.5f, 1.0f, 0.0f, 0.0f,
            0.5f, -0.5f, -0.5f, 1.0f, 0.0f, 0.0f,
            0.5f, -0.5f, 0.5f, 1.0f, 0.0f, 0.0f,
            0.5f, 0.5f, 0.5f, 1.0f, 0.0f, 0.0f,

            -0.5f, -0.5f, -0.5f, 0.0f, -1.0f, 0.0f,
            0.5f, -0.5f, -0.5f, 0.0f, -1.0f, 0.0f,
            0.5f, -0.5f, 0.5f, 0.0f, -1.0f, 0.0f,
            0.5f, -0.5f, 0.5f, 0.0f, -1.0f, 0.0f,
            -0.5f, -0.5f, 0.5f, 0.0f, -1.0f, 0.0f,
            -0.5f, -0.5f, -0.5f, 0.0f, -1.0f, 0.0f,

            -0.5f, 0.5f, -0.5f, 0.0f, 1.0f, 0.0f,
            0.5f, 0.5f, -0.5f, 0.0f, 1.0f, 0.0f,
            0.5f, 0.5f, 0.5f, 0.0f, 1.0f, 0.0f,
            0.5f, 0.5f, 0.5f, 0.0f, 1.0f, 0.0f,
            -0.5f, 0.5f, 0.5f, 0.0f, 1.0f, 0.0f,
            -0.5f, 0.5f, -0.5f, 0.0f, 1.0f, 0.0f
    };

    private Shader lightCubeShader = new Shader(
            "src/main/java/com/tveu/engine/rendering/shaders/light_cube_vertex.glsl",
            "src/main/java/com/tveu/engine/rendering/shaders/light_cube_fragment.glsl"
    );
    private Shader lightingShader = new Shader(
            "src/main/java/com/tveu/engine/rendering/shaders/colors_vertex.glsl",
            "src/main/java/com/tveu/engine/rendering/shaders/colors_fragment.glsl"
    );

    private Vector3f lightPos = new Vector3f(1.2f, 1.0f, 2.0f);

    private Camera camera = new Camera();

    private int vbo, cubeVao, lightCubeVao;

    private float lastMouseX = MouseListener.getLastMouseX();
    private float lastMouseY = MouseListener.getLastMouseY();

    @Override
    public void init() {
        cubeVao = glGenVertexArrays();
        vbo = glGenBuffers();

        FloatBuffer verticesBuffer = BufferUtils.createFloatBuffer(vertices.length);
        verticesBuffer.put(vertices).flip();

        glBindBuffer(GL_ARRAY_BUFFER, vbo);
        glBufferData(GL_ARRAY_BUFFER, verticesBuffer, GL_STATIC_DRAW);

        // Bind the cube VAO
        glBindVertexArray(cubeVao);

        // Define vertex attribute pointers
        glVertexAttribPointer(0, 3, GL_FLOAT, false, 6 * Float.BYTES, 0);
        glEnableVertexAttribArray(0);

        glVertexAttribPointer(1, 3, GL_FLOAT, false, 6 * Float.BYTES, 3 * Float.BYTES);
        glEnableVertexAttribArray(1);

        // Create VAO for light cube
        lightCubeVao = glGenVertexArrays();
        glBindVertexArray(lightCubeVao);

        // Bind VBO to the light cube VAO
        glBindBuffer(GL_ARRAY_BUFFER, vbo);
        glVertexAttribPointer(0, 3, GL_FLOAT, false, 6 * Float.BYTES, 0);
        glEnableVertexAttribArray(0);
    }


    @Override
    public void update(float dt) {

        lightingShader.use();
        lightingShader.setVec3("objectColor", 1.0f, 0.5f, 0.31f);
        lightingShader.setVec3("lightColor", 1.0f, 1.0f, 1.0f);
        lightingShader.setVec3("lightPos", lightPos);
        lightingShader.setVec3("viewPos", camera.getPos());


        Matrix4f view = camera.getViewMatrix();
        Matrix4f projection = new Matrix4f().perspective((float) Math.toRadians(camera.getZoom()), (float) Camera.SCR_WIDTH / Camera.SCR_HEIGHT, 0.1f, 100f);
        lightingShader.setMatrix4f("projection", projection);
        lightingShader.setMatrix4f("view", view);

        Matrix4f model = new Matrix4f();
        lightingShader.setMatrix4f("model", model);

        glBindVertexArray(cubeVao);
        glDrawArrays(GL_TRIANGLES, 0, 36);

        lightCubeShader.use();
        lightCubeShader.setMatrix4f("projection", projection);
        lightCubeShader.setMatrix4f("view", view);
        model = new Matrix4f();
        model.translate(lightPos);
        model.scale(new Vector3f(0.2f));  // Scale the light cube
        lightCubeShader.setMatrix4f("model", model);  // Use lightCubeShader here

        // Draw light cube
        glBindVertexArray(lightCubeVao);
        glDrawArrays(GL_TRIANGLES, 0, 36);


        glBindVertexArray(cubeVao);
        glDrawArrays(GL_TRIANGLES, 0, 36);

        float xoffset = MouseListener.getMouseX() - lastMouseX;
        float yoffset = lastMouseY - MouseListener.getMouseY(); // reversed since y-coordinates go from bottom to top

        lastMouseX = MouseListener.getMouseX();
        lastMouseY = MouseListener.getMouseY();
        // Update camera input
        camera.processKeyboardInput(dt);
        camera.processMouseInput(xoffset, yoffset);

    }
}
