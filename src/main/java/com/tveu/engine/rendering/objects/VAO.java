package com.tveu.engine.rendering.objects;

import static org.lwjgl.opengl.GL30.glBindVertexArray;
import static org.lwjgl.opengl.GL30.glGenVertexArrays;

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
    public void update(float dt) {
        glBindVertexArray(id);
    }
}
