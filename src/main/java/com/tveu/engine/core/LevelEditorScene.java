package com.tveu.engine.core;

import com.tveu.engine.rendering.Shader;
import org.lwjgl.BufferUtils;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.GL_FALSE;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL30.glBindVertexArray;
import static org.lwjgl.opengl.GL30.glGenVertexArrays;

public class LevelEditorScene extends Scene {

    //T vertices
    float[] vertices = {
            // x, y, z,       r, g, b
            -0.2f, -0.7f, 0.0f,   0.0f, 0.0f, 1.0f, // bottom | left 0
            0.2f, -0.7f, 0.0f,    0.0f, 1.0f, 0.0f, // bottom | right 1
            0.2f, 0.4f, 0.0f,     1.0f, 0.0f, 0.0f, // top | right 2
            -0.2f, 0.4f, 0.0f,    1.0f, 0.0f, 0.0f, // top | left 3
            0.7f, 0.4f, 0.0f,     1.0f, 0.0f, 0.0f, // - bottom right 4
            -0.7f, 0.4f, 0.0f,    1.0f, 0.0f, 0.0f, // - bottom left 5
            0.7f, 0.8f, 0.0f,     1.0f, 0.0f, 1.0f, // - top right 6
            -0.7f, 0.8f, 0.0f,    0.0f, 1.0f, 1.0f  // - top left 7
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

    private String vertexShaderSrc = "#version 330 core\n" +
            "\n" +
            "layout (location = 0) in vec3 aPos;   // Position\n" +
            "layout (location = 1) in vec3 aColor; // Color\n" +
            "\n" +
            "out vec3 interpolatedColor; // Output interpolated color to the fragment shader\n" +
            "\n" +
            "void main()\n" +
            "{\n" +
            "    gl_Position = vec4(aPos, 1.0);\n" +
            "    interpolatedColor = aColor; // Pass the color to the fragment shader\n" +
            "}";

    private String fragmentShaderSrc = "#version 330 core\n" +
            "\n" +
            "out vec4 FragColor; // Output color to the framebuffer\n" +
            "\n" +
            "in vec3 interpolatedColor; // Input interpolated color from the vertex shader\n" +
            "\n" +
            "void main()\n" +
            "{\n" +
            "    FragColor = vec4(interpolatedColor, 1.0); // Set the fragment color to the interpolated color\n" +
            "}\n";

    private int vaoID, vboID, eboID;
//    private int vertexShaderID, fragmentShaderID, shaderProgram;

    FloatBuffer vertexBuffer = BufferUtils.createFloatBuffer(vertices.length);

    Shader shader = new Shader("src/main/java/com/tveu/engine/rendering/shaders/t_vertex.glsl",
            "src/main/java/com/tveu/engine/rendering/shaders/t_fragment.glsl");

    @Override
    public void init() {
        // Create a float buffer of vertices

      /*  // Create shader program
        vertexShaderID = glCreateShader(GL_VERTEX_SHADER);
        glShaderSource(vertexShaderID, vertexShaderSrc);
        glCompileShader(vertexShaderID);

        int success = glGetShaderi(vertexShaderID, GL_COMPILE_STATUS);
        if (success == GL_FALSE) {
            int len = glGetShaderi(vertexShaderID, GL_INFO_LOG_LENGTH);
            System.out.println("ERROR: Vertex shader compilation failed.");
            System.out.println(glGetShaderInfoLog(vertexShaderID, len));
            assert false : "";
        }

        fragmentShaderID = glCreateShader(GL_FRAGMENT_SHADER);
        glShaderSource(fragmentShaderID, fragmentShaderSrc);
        glCompileShader(fragmentShaderID);

        success = glGetShaderi(fragmentShaderID, GL_COMPILE_STATUS);
        if (success == GL_FALSE) {
            int len = glGetShaderi(fragmentShaderID, GL_INFO_LOG_LENGTH);
            System.out.println("ERROR: Fragment shader compilation failed.");
            System.out.println(glGetShaderInfoLog(fragmentShaderID, len));
            assert false : "";
        }

        shaderProgram = glCreateProgram();
        glAttachShader(shaderProgram, vertexShaderID);
        glAttachShader(shaderProgram, fragmentShaderID);
        glLinkProgram(shaderProgram);

        success = glGetProgrami(shaderProgram, GL_LINK_STATUS);
        if (success == GL_FALSE) {
            int len = glGetProgrami(shaderProgram, GL_INFO_LOG_LENGTH);
            System.out.println("ERROR: Shader program linking failed.");
            System.out.println(glGetProgramInfoLog(shaderProgram, len));
            assert false : "";
        }

        glDeleteShader(vertexShaderID);
        glDeleteShader(fragmentShaderID);*/


        vertexBuffer.put(vertices).flip();

        // Create VBO, upload the vertex buffer
        vboID = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, vboID);
        glBufferData(GL_ARRAY_BUFFER, vertexBuffer, GL_STATIC_DRAW);

        // Create VAO, set up vertex attribute pointers
        vaoID = glGenVertexArrays();
        glBindVertexArray(vaoID);
        glBindBuffer(GL_ARRAY_BUFFER, vboID);

        glVertexAttribPointer(0, 3, GL_FLOAT, false, 6 * Float.BYTES, 0);;
        glEnableVertexAttribArray(0);

        glVertexAttribPointer(1, 3, GL_FLOAT, false, 6 * Float.BYTES, 3*Float.BYTES);
        glEnableVertexAttribArray(1);

        eboID = glGenBuffers();
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, eboID);
        IntBuffer elementBuffer = BufferUtils.createIntBuffer(indices.length);
        elementBuffer.put(indices).flip();
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, elementBuffer, GL_STATIC_DRAW);

        glBindVertexArray(0);
        glBindVertexArray(1);
    }

    @Override
    public void update(float dt) {
        shader.use();
        glBindVertexArray(vaoID);
        glDrawElements(GL_TRIANGLES, indices.length, GL_UNSIGNED_INT, 0);
        glBindVertexArray(0);
        glUseProgram(0);
    }
}
