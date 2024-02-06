package com.tveu.engine.rendering;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL20.glCompileShader;

public class Shader extends AbstractShader {
    private int ID;

    public Shader(String vertexPath, String fragmentPath) {

        String vertexShaderSrc = readGLSLFile(vertexPath);
        String fragmentShaderSrc = readGLSLFile(fragmentPath);

        //compile shaders
        int vertexShaderID = glCreateShader(GL_VERTEX_SHADER);
        glShaderSource(vertexShaderID, vertexShaderSrc);
        glCompileShader(vertexShaderID);
        compileErrorsCheck(vertexShaderID);

        int fragmentShaderID = glCreateShader(GL_FRAGMENT_SHADER);
        glShaderSource(fragmentShaderID, fragmentShaderSrc);
        glCompileShader(fragmentShaderID);
        compileErrorsCheck(fragmentShaderID);

        // shader Program
        ID = glCreateProgram();
        glAttachShader(ID, vertexShaderID);
        glAttachShader(ID, fragmentShaderID);
        glLinkProgram(ID);

        int success = glGetProgrami(ID, GL_LINK_STATUS);
        if(success == GL_FALSE){
            int len = glGetProgrami(ID, GL_INFO_LOG_LENGTH);
            System.out.println("ERROR: Shader compilation failed.");
            System.out.println(glGetShaderInfoLog(ID, len));
            assert false : "";
        }

        glDeleteShader(vertexShaderID);
        glDeleteShader(fragmentShaderID);
    }

    private String readGLSLFile(String filePath) {

        StringBuilder shaderSource = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {

            String line;
            while ((line = reader.readLine()) != null) {
                shaderSource.append(line).append("\n");
            }
        } catch (IOException e) {
            Logger logger = Logger.getAnonymousLogger();
            logger.log(Level.SEVERE, "Invalid path of the GLSL File", e);
        }
        return shaderSource.toString();
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


    @Override
    public void use() {
        glUseProgram(ID);
    }


}
