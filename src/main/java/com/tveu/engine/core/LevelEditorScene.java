package com.tveu.engine.core;

import com.tveu.engine.rendering.Shader;
import com.tveu.engine.rendering.Texture;
import org.lwjgl.BufferUtils;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.glBindVertexArray;
import static org.lwjgl.opengl.GL30.glGenVertexArrays;

public class LevelEditorScene extends Scene {

    //T vertices
    float[] vertices = {
            //x,     y,    z,       r,    g,    b,          texture coords
            -0.2f, -0.7f, 0.0f,     0.0f, 0.0f, 1.0f,       0.0f, 0.0f,  // bottom | left 0
            0.2f, -0.7f, 0.0f,      0.0f, 1.0f, 0.0f,       1.6f, 0.0f,  // bottom | right 1
            0.2f,  0.4f, 0.0f,      1.0f, 0.0f, 0.0f,       1.6f, 2.5f,  // top | right 2
            -0.2f,  0.4f, 0.0f,     1.0f, 0.0f, 0.0f,       0.0f, 2.5f,  // top | left 3
            0.7f,  0.4f, 0.0f,      1.0f, 0.0f, 0.0f,       5.0f, 1.0f,  // - bottom right 4
            -0.7f,  0.4f, 0.0f,     1.0f, 0.0f, 0.0f,       0.0f, 1.0f,  // - bottom left 5
            0.7f,  0.8f, 0.0f,      1.0f, 0.0f, 1.0f,       5.0f, 0.0f,  // - top right 6
            -0.7f,  0.8f, 0.0f,     0.0f, 1.0f, 1.0f,       0.0f, 0.0f   // - top left 7- top left 7
    };


    //
    int[] indices = {
            // | of the T
            0, 1, 2,
            0, 2, 3,
            // - of the T
            4, 5, 6,
            5, 6, 7
    };
    private int vaoID, vboID, eboID;

    FloatBuffer vertexBuffer = BufferUtils.createFloatBuffer(vertices.length);

    Shader shader = new Shader("src/main/java/com/tveu/engine/rendering/shaders/t_vertex.glsl",
            "src/main/java/com/tveu/engine/rendering/shaders/t_fragment.glsl");

    Texture texture1;

    Texture texture2;

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

        glVertexAttribPointer(0, 3, GL_FLOAT, false, 8 * Float.BYTES, 0);
        glEnableVertexAttribArray(0);

        glVertexAttribPointer(1, 3, GL_FLOAT, false, 8 * Float.BYTES, 3 * Float.BYTES);
        glEnableVertexAttribArray(1);

        glVertexAttribPointer(2, 2, GL_FLOAT, false, 8 * Float.BYTES, 6 * Float.BYTES);
        glEnableVertexAttribArray(2);

        glBindVertexArray(0);
        glBindVertexArray(1);
        glBindVertexArray(2);

        glBindVertexArray(0);

        texture1 = new Texture("src/main/java/resources/textures/W-kX3RQv35Q.jpg");
        texture2 = new Texture("src/main/java/resources/textures/awesomeface.png");



        shader.use();
        shader.setInt("texture1", 0);
        shader.setInt("texture2", 1);
    }

    @Override
    public void update(float dt) {
        glActiveTexture(GL_TEXTURE0);
        glBindTexture(GL_TEXTURE_2D, texture1.getID());
        glActiveTexture(GL_TEXTURE1);
        glBindTexture(GL_TEXTURE_2D, texture2.getID());

        shader.use();

        glBindVertexArray(vaoID);
        glDrawElements(GL_TRIANGLES, indices.length, GL_UNSIGNED_INT, 0);
    }
}
