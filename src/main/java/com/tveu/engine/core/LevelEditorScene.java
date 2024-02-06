package com.tveu.engine.core;

import org.lwjgl.BufferUtils;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.GL_FALSE;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.glBindVertexArray;
import static org.lwjgl.opengl.GL30.glGenVertexArrays;

public class LevelEditorScene extends Scene {

    //T vertices
    float[] vertices = {
            -0.2f, -0.7f, 0.0f,  // bottom | left 0
            0.2f, -0.7f, 0.0f,  //bottom | right 1
            0.2f, 0.4f, 0.0f,  // top | right 2
            -0.2f, 0.4f, 0.0f, // top | left 3
            0.7f, 0.4f, 0.0f, // - bottom right 4
            -0.7f, 0.4f, 0.0f, // - bottom left 5
            0.7f, 0.8f, 0.0f, // - top right 6
            -0.7f, 0.8f, 0.0f, // - top left 7
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
            "layout (location = 0) in vec3 aPos;\n" +
            "out vec4 vertexColor; // Output color attribute\n" +
            "\n" +
            "void main()\n" +
            "{\n" +
            "    gl_Position = vec4(aPos.x, aPos.y, aPos.z, 1.0);\n" +
            "    \n" +
            "    // Example: Assign color based on vertex position\n" +
            "    vertexColor = vec4(abs(aPos.x), abs(aPos.y), abs(aPos.z), 1.0);\n" +
            "}\n";

    private String fragmentShaderSrc = "#version 330 core\n" +
            "\n" +
            "in vec4 vertexColor; // Input color attribute from the vertex shader\n" +
            "out vec4 FragColorOut;\n" +
            "\n" +
            "void main()\n" +
            "{\n" +
            "    // Use the x-coordinate of the fragment position for the gradient\n" +
            "    float gradient = gl_FragCoord.x / 800.0; // Assuming a window width of 800 pixels\n" +
            "\n" +
            "    // Interpolate colors along the gradient\n" +
            "    vec3 gradientColor = vec3(1.0, gradient, 1.0);\n" +
            "\n" +
            "    // Combine the gradient color with the input color\n" +
            "    FragColorOut = vec4(gradientColor, 1.0) * vertexColor;\n" +
            "}\n";

    private int vaoID, vboID, eboID;
    private int vertexShaderID, fragmentShaderID, shaderProgram;

    FloatBuffer vertexBuffer = BufferUtils.createFloatBuffer(vertices.length);

    @Override
    public void init() {
        // Create a float buffer of vertices
        vertexBuffer.put(vertices).flip();

        // Create VBO, upload the vertex buffer
        vboID = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, vboID);
        glBufferData(GL_ARRAY_BUFFER, vertexBuffer, GL_STATIC_DRAW);

        // Create shader program
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
        glDeleteShader(fragmentShaderID);

        // Create VAO, set up vertex attribute pointers
        vaoID = glGenVertexArrays();
        glBindVertexArray(vaoID);
        glBindBuffer(GL_ARRAY_BUFFER, vboID);
        glVertexAttribPointer(0, 3, GL_FLOAT, false, 3 * Float.BYTES, 0);;

        eboID = glGenBuffers();
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, eboID);
        IntBuffer elementBuffer = BufferUtils.createIntBuffer(indices.length);
        elementBuffer.put(indices).flip();
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, elementBuffer, GL_STATIC_DRAW);

        glEnableVertexAttribArray(0);
        glBindVertexArray(0);
    }

    @Override
    public void update(float dt) {
        glUseProgram(shaderProgram);
        glBindVertexArray(vaoID);
        glDrawElements(GL_TRIANGLES, indices.length, GL_UNSIGNED_INT, 0);
        glBindVertexArray(0);
        glUseProgram(0);
    }
}
