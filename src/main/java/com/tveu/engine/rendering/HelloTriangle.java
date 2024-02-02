package com.tveu.engine.rendering;


import static org.lwjgl.opengl.GL15.*;

public class HelloTriangle {

    private static int buffer;

    public static void drawTriangle() {

        var vertices = new float[]{
                -0.5f, -0.5f, 0.0f,
                0.5f, -0.5f, 0.0f,
                0.0f, 0.5f, 0.0f
        };

        glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, buffer);
        glBufferData(GL_ARRAY_BUFFER, vertices, GL_STATIC_DRAW);

        String vertexShaderSource = "#version 330 core\n" +
                "layout (location = 0) in vec3 aPos;\n" +
                "\n" +
                "void main()\n" +
                "{\n" +
                "    gl_Position = vec4(aPos.x, aPos.y, aPos.z, 1.0);\n" +
                "}";

        int vertexShader;




    }
}
