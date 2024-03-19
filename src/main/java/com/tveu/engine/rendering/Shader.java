package com.tveu.engine.rendering;

import com.tveu.engine.core.utils.FileManager;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.lwjgl.BufferUtils;

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

    public void setMatrix4f(String name, Matrix4f mat) {

        int matLoc = glGetUniformLocation(this.ID, name);

        FloatBuffer matrixBuffer = BufferUtils.createFloatBuffer(16);
        mat.get(matrixBuffer);
        glUniformMatrix4fv(matLoc, false, matrixBuffer);
    }


    public void setVec3(String name, float v0, float v1, float v2) {
        glUniform3f(glGetUniformLocation(ID, name), v0, v1, v2);
    }
    public void setVec3(String name, Vector3f vec3f) {
        glUniform3f(glGetUniformLocation(ID, name), vec3f.x, vec3f.y, vec3f.z);
    }

    @Override
    public void use() {
        glUseProgram(ID);
    }

}
