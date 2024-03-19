package com.tveu.engine.core.scene;

import com.tveu.engine.core.input.MouseListener;
import com.tveu.engine.rendering.Camera;
import com.tveu.engine.rendering.Shader;
import com.tveu.engine.rendering.Texture;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.lwjgl.BufferUtils;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.glfw.GLFW.glfwGetTime;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glVertexAttribPointer;
import static org.lwjgl.opengl.GL30.glBindVertexArray;
import static org.lwjgl.opengl.GL30.glGenVertexArrays;

public class LevelEditorScene extends Scene {

    //T vertices
    float[] vertices = {
            -0.5f, -0.5f, -0.5f, 0.0f, 0.0f,
            0.5f, -0.5f, -0.5f, 1.0f, 0.0f,
            0.5f, 0.5f, -0.5f, 1.0f, 1.0f,
            0.5f, 0.5f, -0.5f, 1.0f, 1.0f,
            -0.5f, 0.5f, -0.5f, 0.0f, 1.0f,
            -0.5f, -0.5f, -0.5f, 0.0f, 0.0f,

            -0.5f, -0.5f, 0.5f, 0.0f, 0.0f,
            0.5f, -0.5f, 0.5f, 1.0f, 0.0f,
            0.5f, 0.5f, 0.5f, 1.0f, 1.0f,
            0.5f, 0.5f, 0.5f, 1.0f, 1.0f,
            -0.5f, 0.5f, 0.5f, 0.0f, 1.0f,
            -0.5f, -0.5f, 0.5f, 0.0f, 0.0f,

            -0.5f, 0.5f, 0.5f, 1.0f, 0.0f,
            -0.5f, 0.5f, -0.5f, 1.0f, 1.0f,
            -0.5f, -0.5f, -0.5f, 0.0f, 1.0f,
            -0.5f, -0.5f, -0.5f, 0.0f, 1.0f,
            -0.5f, -0.5f, 0.5f, 0.0f, 0.0f,
            -0.5f, 0.5f, 0.5f, 1.0f, 0.0f,

            0.5f, 0.5f, 0.5f, 1.0f, 0.0f,
            0.5f, 0.5f, -0.5f, 1.0f, 1.0f,
            0.5f, -0.5f, -0.5f, 0.0f, 1.0f,
            0.5f, -0.5f, -0.5f, 0.0f, 1.0f,
            0.5f, -0.5f, 0.5f, 0.0f, 0.0f,
            0.5f, 0.5f, 0.5f, 1.0f, 0.0f,

            -0.5f, -0.5f, -0.5f, 0.0f, 1.0f,
            0.5f, -0.5f, -0.5f, 1.0f, 1.0f,
            0.5f, -0.5f, 0.5f, 1.0f, 0.0f,
            0.5f, -0.5f, 0.5f, 1.0f, 0.0f,
            -0.5f, -0.5f, 0.5f, 0.0f, 0.0f,
            -0.5f, -0.5f, -0.5f, 0.0f, 1.0f,

            -0.5f, 0.5f, -0.5f, 0.0f, 1.0f,
            0.5f, 0.5f, -0.5f, 1.0f, 1.0f,
            0.5f, 0.5f, 0.5f, 1.0f, 0.0f,
            0.5f, 0.5f, 0.5f, 1.0f, 0.0f,
            -0.5f, 0.5f, 0.5f, 0.0f, 0.0f,
            -0.5f, 0.5f, -0.5f, 0.0f, 1.0f
    };

    Vector3f[] cubePositions = {

            new Vector3f(0.0f, 0.0f, 0.0f),

            new Vector3f(2.0f, 5.0f, -15.0f),

            new Vector3f(-1.5f, -2.2f, -2.5f),

            new Vector3f(-3.8f, -2.0f, -12.3f),

            new Vector3f(2.4f, -0.4f, -3.5f),

            new Vector3f(-1.7f, 3.0f, -7.5f),

            new Vector3f(1.3f, -2.0f, -2.5f),

            new Vector3f(1.5f, 2.0f, -2.5f),

            new Vector3f(1.5f, 0.2f, -1.5f),

            new Vector3f(-1.3f, 1.0f, -1.5f)
    };

    private float lastMouseX = MouseListener.getLastMouseX();
    float lastMouseY = MouseListener.getLastMouseY();


    //
    int[] indices = {
            // Front
            0, 1, 2,
            1, 3, 2,

            // Top
            4, 5, 6,
            5, 7, 6,

            // Ho   arizontal bar
            8, 9, 10,
            9, 11, 10,

            // Back
            12, 13, 14,
            13, 15, 14
    };

    private int vaoID, vboID, eboID;

    FloatBuffer vertexBuffer = BufferUtils.createFloatBuffer(vertices.length);

    Shader shader = new Shader("src/main/java/com/tveu/engine/rendering/shaders/t_vertex.glsl",
            "src/main/java/com/tveu/engine/rendering/shaders/t_fragment.glsl");

    Texture texture1;

    Texture texture2;

    Camera camera = new Camera();


    @Override
    public void init() {

        vertexBuffer.put(vertices).flip();

        // Create VBO, upload the vertex buffer
        vaoID = glGenVertexArrays();
        vboID = glGenBuffers();
        eboID = glGenBuffers();


        // Create VAO, set up vertex attribute pointers
        glBindVertexArray(vaoID);

        glBindBuffer(GL_ARRAY_BUFFER, vboID);
        glBufferData(GL_ARRAY_BUFFER, vertexBuffer, GL_STATIC_DRAW);
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, eboID);
        IntBuffer elementBuffer = BufferUtils.createIntBuffer(indices.length);
        elementBuffer.put(indices).flip();
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, elementBuffer, GL_STATIC_DRAW);


        glVertexAttribPointer(0, 3, GL_FLOAT, false, 5 * Float.BYTES, 0);
        glEnableVertexAttribArray(0);

        glVertexAttribPointer(1, 2, GL_FLOAT, false, 5 * Float.BYTES, 3 * Float.BYTES);
        glEnableVertexAttribArray(1);

        glBindVertexArray(0);
        glBindVertexArray(1);

        glBindVertexArray(0);

        texture1 = new Texture("src/main/java/resources/textures/awesomeface.png");
        texture2 = new Texture("src/main/java/resources/textures/W-kX3RQv35Q.jpg");


        shader.use();
        shader.setInt("texture1", 0);
        shader.setInt("texture2", 1);
    }

    @Override
    public void update(float dt) {
        glActiveTexture(GL_TEXTURE1);
        glBindTexture(GL_TEXTURE_2D, texture2.getID());
        shader.use();

        Matrix4f view = camera.transform.getViewMatrix();
        Matrix4f projection = new Matrix4f().perspective((float) Math.toRadians(camera.getZoom()), (float) Camera.SCR_WIDTH / Camera.SCR_HEIGHT, 0.1f, 100f);

        // reversed since y-coordinates go from bottom to top

        lastMouseX = MouseListener.getMouseX();
        lastMouseY = MouseListener.getMouseY();
        // Update camera input

        shader.setMatrix4f("view", view);
        shader.setMatrix4f("projection", projection);

        glBindVertexArray(vaoID);

        for (int i = 0; i < 10; i++) {
            Matrix4f model = new Matrix4f().translate(cubePositions[i])
                    .rotate((float) (i * glfwGetTime()), new Vector3f(0.0f, 0.0f, 1.0f))
                    .rotate((float) (i * glfwGetTime()), new Vector3f(0.0f, 1.0f, 0.0f))
                    .rotate((float) (i * glfwGetTime()), new Vector3f(1.0f, 0.0f, 0.0f));
            shader.setMatrix4f("model", model);
            glDrawArrays(GL_TRIANGLES, 0, 36);
        }
    }


}
