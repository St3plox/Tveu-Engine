package com.tveu.engine.rendering.objects;


import static org.lwjgl.opengl.GL30.*;

public class VAO extends RenderObject {

    public VAO() {
        super();
    }

    @Override
    protected int genId() {
        return glGenVertexArrays();
    }

    @Override
    protected void bindId() {
        glBindVertexArray(id);
    }

    @Override
    public void unbindId() {
        glBindVertexArray(0);
    }

    @Override
    public void update(float dt) {
        glBindVertexArray(id);
    }

    @Override
    public void delete() {
        glDeleteVertexArrays(id);
    }
}
