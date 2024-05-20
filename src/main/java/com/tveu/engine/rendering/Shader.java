package com.tveu.engine.rendering;

import com.tveu.engine.core.utils.FileManager;
import org.joml.*;
import org.lwjgl.BufferUtils;
import org.lwjgl.system.MemoryStack;

import java.nio.FloatBuffer;

import static org.lwjgl.opengl.GL20.*;

public class Shader extends AbstractShader {
    private final int ID;

    public Shader(String vertexPath, String fragmentPath) {

        String vertexShaderSrc = FileManager.readFileToString(vertexPath);
        String fragmentShaderSrc = FileManager.readFileToString(fragmentPath);

        //compile shaders
        int vertexShaderID = compileShader(GL_VERTEX_SHADER, vertexShaderSrc);
        int fragmentShaderID = compileShader(GL_FRAGMENT_SHADER, fragmentShaderSrc);

        // shader Program
        ID = glCreateProgram();
        glAttachShader(ID, vertexShaderID);
        glAttachShader(ID, fragmentShaderID);
        glLinkProgram(ID);

        int success = glGetProgrami(ID, GL_LINK_STATUS);
        if (success == GL_FALSE) {
            int len = glGetProgrami(ID, GL_INFO_LOG_LENGTH);
            System.out.println("ERROR: Shader compilation failed.");
            System.out.println(glGetShaderInfoLog(ID, len));
            assert false : "";
        }

        glDeleteShader(vertexShaderID);
        glDeleteShader(fragmentShaderID);
    }

    public int getID() {
        return ID;
    }

    private int compileShader(int shaderType, String shaderSrc) {

        int shaderID = glCreateShader(shaderType);
        glShaderSource(shaderID, shaderSrc);
        glCompileShader(shaderID);
        compileErrorsCheck(shaderID);

        return shaderID;
    }

    private void compileErrorsCheck(int shaderID) {
        int success = glGetShaderi(shaderID, GL_COMPILE_STATUS);
        if (success == GL_FALSE) {
            int len = glGetShaderi(shaderID, GL_INFO_LOG_LENGTH);
            System.out.println("ERROR: Shader compilation failed.");
            System.out.println(glGetShaderInfoLog(shaderID, len));
            assert false : "";
        }
    }

    public void setBool(String name, boolean value) {
        setInt(name, value ? 1 : 0);
    }

    public void setInt(String name, int value) {
        glUniform1i(glGetUniformLocation(ID, name), value);
    }

    public void setFloat(String name, float value) {
        glUniform1f(glGetUniformLocation(ID, name), value);
    }

    public void setVec2(String name, Vector2fc value) {
        setVec2(name, value.x(), value.y());
    }

    public void setVec2(String name, float x, float y) {
        glUniform2f(glGetUniformLocation(ID, name), x, y);
    }

    public void setVec3(String name, Vector3fc value) {
        setVec3(name, value.x(), value.y(), value.z());
    }

    public void setVec3(String name, float x, float y, float z) {
        glUniform3f(glGetUniformLocation(ID, name), x, y, z);
    }

    public void setVec4(String name, Vector4fc value) {
        setVec4(name, value.x(), value.y(), value.z(), value.w());
    }

    public void setVec4(String name, float x, float y, float z, float w) {
        glUniform4f(glGetUniformLocation(ID, name), x, y, z, w);
    }

    public void setMat3(String name, Matrix3fc matrix) {
        try(MemoryStack stack = MemoryStack.stackPush()) {
            glUniformMatrix3fv(glGetUniformLocation(ID, name), false, matrix.get(stack.mallocFloat(9)));
        }
    }

    public void setMatrix4f(String name, Matrix4fc matrix) {
        try(MemoryStack stack = MemoryStack.stackPush()) {
            glUniformMatrix4fv(glGetUniformLocation(ID, name), false, matrix.get(stack.mallocFloat(16)));
        }
    }


    @Override
    public void use() {
        glUseProgram(ID);
    }

}
