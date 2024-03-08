package com.tveu.engine.rendering;

import org.joml.Matrix4f;
import org.lwjgl.BufferUtils;

import java.nio.FloatBuffer;

import static org.lwjgl.opengl.GL20.*;

public abstract class AbstractShader {

    private int ID;

    public abstract void use();

    public void setBool(String name, boolean value) {
        glUniform1i(glGetUniformLocation(ID, name), Boolean.compare(value, false));
    }

    public void setInt(String name, int value) {
        glUniform1i(glGetUniformLocation(ID, name), value);
    }

    public void setFloat(String name, float value) {
        glUniform1f(glGetUniformLocation(ID, name), value);
    }
/*
    public void setVec3(String name, float v0, float v1, float v2) {
        glUniform3f(glGetUniformLocation(ID, name), v0, v1, v2);
    }

    public void setMatrix4f(String name, Matrix4f mat) {

        int matLoc = glGetUniformLocation(this.ID, name);

        FloatBuffer matrixBuffer = BufferUtils.createFloatBuffer(16);
        mat.get(matrixBuffer);
        glUniformMatrix4fv(matLoc, false, matrixBuffer);
    }*/
}
