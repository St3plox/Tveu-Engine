package com.tveu.engine.rendering.objects;

import java.nio.FloatBuffer;

import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL15C.glGenBuffers;

public class VBO extends RenderObject {

    public VBO(FloatBuffer floatBuffer) {
        super();
        bindBufferData(floatBuffer);
    }

    protected void bindBufferData(FloatBuffer vertexBuffer) {
        glBufferData(GL_ARRAY_BUFFER, vertexBuffer, GL_STATIC_DRAW);
    }

    @Override
    protected int genId() {
        return glGenBuffers();
    }

    @Override
    protected void bindId() {
        glBindBuffer(GL_ARRAY_BUFFER, id);
    }

}
