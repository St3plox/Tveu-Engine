package com.tveu.engine.rendering;

import static org.lwjgl.opengl.GL20.*;

public abstract class AbstractShader {

    private int ID;

    public abstract void use();

    void setBool(String name, boolean value) {
        glUniform1i(glGetUniformLocation(ID, name), Boolean.compare(value, false));
    }

    void setInt(String name, int value) {
        glUniform1i(glGetUniformLocation(ID, name), value);
    }

    void setFloat(String name, float value) {
        glUniform1f(glGetUniformLocation(ID, name), value);
    }
}
