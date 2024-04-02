package com.tveu.engine.rendering.objects;

import org.lwjgl.BufferUtils;

import java.nio.IntBuffer;

import static org.lwjgl.opengl.GL15.*;

public class EBO extends RenderObject {


    public EBO(int[] indices) {
        super();
        bindBufferData(indices);
    }

    @Override
    protected int genId() {
        return glGenBuffers();
    }

    @Override
    protected void bindId() {
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, id);
    }

    protected void bindBufferData(int[] indices) {
        IntBuffer elementBuffer = BufferUtils.createIntBuffer(indices.length);
        elementBuffer.put(indices).flip();
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, elementBuffer, GL_STATIC_DRAW);
    }
}
