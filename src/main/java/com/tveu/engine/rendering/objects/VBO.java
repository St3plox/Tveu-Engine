package com.tveu.engine.rendering.objects;

import org.lwjgl.BufferUtils;

import java.nio.FloatBuffer;

import static org.lwjgl.opengl.GL15.*;


public class VBO extends RenderObject {

    public VBO(float[] vertices) {
        super();
        bindBufferData(vertices);
    }

    protected void bindBufferData(float[] vertices) {
        FloatBuffer fb = BufferUtils.createFloatBuffer(vertices.length);
        fb.put(vertices).flip();
        glBufferData(GL_ARRAY_BUFFER, fb, GL_STATIC_DRAW);
    }

    @Override
    protected int genId() {
        return glGenBuffers();
    }

    @Override
    protected void bindId() {
        glBindBuffer(GL_ARRAY_BUFFER, id);
    }

    @Override
    public void unbindId() {
        glBindBuffer(GL_ARRAY_BUFFER, 0);
    }

    @Override
    public void delete() {
        glDeleteBuffers(id);
    }

}
