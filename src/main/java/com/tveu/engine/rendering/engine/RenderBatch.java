package com.tveu.engine.rendering.engine;

import com.tveu.engine.core.Updatable;
import com.tveu.engine.rendering.Cleanable;
import com.tveu.engine.rendering.Shader;
import com.tveu.engine.rendering.ShaderUniform;
import com.tveu.engine.rendering.VertexAttribPtr;
import com.tveu.engine.rendering.objects.RenderObject;
import com.tveu.engine.rendering.objects.VAO;
import com.tveu.engine.rendering.objects.VBO;
import org.joml.Matrix4f;
import org.joml.Vector3f;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.lwjgl.opengl.GL11.GL_FLOAT;
import static org.lwjgl.opengl.GL11C.GL_TRIANGLES;
import static org.lwjgl.opengl.GL11C.glDrawArrays;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glVertexAttribPointer;
import static org.lwjgl.opengl.GL30.glBindVertexArray;

public class RenderBatch implements Updatable, Cleanable {

    private static int TRIANGLES_ARRAY_LENGTH = 36;

    private float[] vertices;

    private int[] indices;

    private Shader shader;

    private List<ShaderUniform<?>> shaderUniforms;

    private VertexAttribPtr[] vertexAttribs;

    private HashMap<Class<? extends RenderObject>, RenderObject> renderObjects;


    public RenderBatch() {
        vertices = new float[36];
        indices = new int[36];

        shaderUniforms = new ArrayList<>();
        vertexAttribs = new VertexAttribPtr[2];

        shader = new Shader("assets/shaders/light_cube_vertex.glsl", "assets/shaders/light_cube_fragment.glsl");

        renderObjects = new HashMap<>();
    }

    public void init() {
        for (int i = 0; i < vertexAttribs.length; i++) {

            var attrib = vertexAttribs[i];
            glVertexAttribPointer(
                    i,
                    attrib.getSize(),
                    GL_FLOAT,
                    attrib.isNormalized(),
                    attrib.getStride(),
                    attrib.getPointer()
            );
            glEnableVertexAttribArray(i);
            glBindVertexArray(i);
        }

        //TODO: Add ex validation, remove code duplication
        var vbo = renderObjects.get(VBO.class);
        vbo.unbindId();

        var vao = renderObjects.get(VAO.class);
        vao.unbindId();
    }


    @Override
    public void update(float dt) {
        shader.use();

        for (var su : shaderUniforms) {
            setUniform(su);
        }

        var vao = renderObjects.get(VAO.class);
        vao.update(dt);
        glDrawArrays(GL_TRIANGLES, 0, TRIANGLES_ARRAY_LENGTH);
    }

    @Override
    public void clean() {
        renderObjects.forEach((k, v) -> {
            v.delete();
        });
        shader.delete();
    }

    protected void setUniform(ShaderUniform<?> su) {
        if (su.value().getClass().equals(Vector3f.class)) {
            shader.setVec3(su.name(), (Vector3f) su.value());
        } else if (su.value().getClass().equals(Matrix4f.class)) {
            shader.setMatrix4f(su.name(), (Matrix4f) su.value());
        } else if (su.value().getClass().equals(Float.class)) {
            shader.setFloat(su.name(), (Float) su.value());
        } else if (su.value().getClass().equals(Boolean.class)) {
            shader.setBool(su.name(), (Boolean) su.value());
        } else if (su.value().getClass().equals(Integer.class)) {
            shader.setInt(su.name(), (Integer) su.value());
        } else {
            throw new IllegalStateException("Unexpected value: " + su.value());
        }
    }

    //=======================================================================================

    public float[] getVertices() {
        return vertices;
    }

    public void setVertices(float[] vertices) {
        this.vertices = vertices;
    }

    public int[] getIndices() {
        return indices;
    }

    public void setIndices(int[] indices) {
        this.indices = indices;
    }

    public Shader getShader() {
        return shader;
    }

    public void setShader(Shader shader) {
        this.shader = shader;
    }

    public List<ShaderUniform<?>> getShaderUniforms() {
        return shaderUniforms;
    }

    public void setShaderUniforms(List<ShaderUniform<?>> shaderUniforms) {
        this.shaderUniforms = shaderUniforms;
    }

    public VertexAttribPtr[] getVertexAttribs() {
        return vertexAttribs;
    }

    public void setVertexAttribs(VertexAttribPtr[] vertexAttribs) {
        this.vertexAttribs = vertexAttribs;
    }

    public HashMap<Class<? extends RenderObject>, RenderObject> getRenderObjects() {
        return renderObjects;
    }

    public void setRenderObjects(HashMap<Class<? extends RenderObject>, RenderObject> renderObjects) {
        this.renderObjects = renderObjects;
    }

}
