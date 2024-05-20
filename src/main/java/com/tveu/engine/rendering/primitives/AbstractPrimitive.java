package com.tveu.engine.rendering.primitives;

import com.tveu.engine.rendering.LightProperties;
import com.tveu.engine.rendering.Shader;
import com.tveu.engine.rendering.ShaderUniform;
import com.tveu.engine.rendering.VertexAttribPtr;
import com.tveu.engine.rendering.engine.RenderBatch;
import com.tveu.engine.rendering.engine.RenderBatchFactory;
import com.tveu.engine.rendering.objects.RenderObject;
import org.joml.Matrix4f;

import java.util.HashMap;
import java.util.List;

public abstract class AbstractPrimitive {

    protected RenderBatch renderBatch;

    protected LightProperties light;


    protected final Shader shader = genShader();

    private Matrix4f model, view, projection;

    public AbstractPrimitive() {
        model = new Matrix4f();
    }

    public RenderBatch genRenderBatch() {

        List<ShaderUniform<?>> shaderUniforms = genShaderUniforms();
        VertexAttribPtr[] vertexAttribs = genVertexAttribs();
        HashMap<Class<? extends RenderObject>, RenderObject> renderObjects = genRenderObjects();

        shaderUniforms.add(new ShaderUniform<>("model", model));
        shaderUniforms.add(new ShaderUniform<>("view", view));
        shaderUniforms.add(new ShaderUniform<>("projection", projection));

        var factory = new RenderBatchFactory();
        renderBatch = factory
                .vertices(vertices())
                .shader(shader)
                .vertexAttribs(vertexAttribs)
                .renderObjects(renderObjects)
                .shaderUniforms(shaderUniforms)
                .build();

        return renderBatch;
    }

    abstract HashMap<Class<? extends RenderObject>, RenderObject> genRenderObjects();

    abstract VertexAttribPtr[] genVertexAttribs();

    abstract List<ShaderUniform<?>> genShaderUniforms();

    abstract float[] vertices();

    abstract Shader genShader();

    //==============================================================================

    public LightProperties getLight() {
        return light;
    }

    public void setLight(LightProperties lightProperties) {
        this.light = lightProperties;
    }


    public Matrix4f getModel() {
        return model;
    }

    public void setModel(Matrix4f model) {
        this.model = model;
    }

    public Matrix4f getView() {
        return view;
    }

    public void setView(Matrix4f view) {
        this.view = view;
    }

    public Matrix4f getProjection() {
        return projection;
    }

    public void setProjection(Matrix4f projection) {
        this.projection = projection;
    }

    public RenderBatch getRenderBatch() {
        return renderBatch;
    }
}
