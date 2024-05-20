package com.tveu.engine.rendering;

public abstract class AbstractShader {

    private int ID;

    public abstract void use();

    public void delete() {
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
