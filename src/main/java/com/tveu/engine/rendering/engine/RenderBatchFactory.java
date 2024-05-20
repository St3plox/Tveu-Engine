package com.tveu.engine.rendering.engine;

import com.tveu.engine.rendering.Shader;
import com.tveu.engine.rendering.ShaderUniform;
import com.tveu.engine.rendering.VertexAttribPtr;
import com.tveu.engine.rendering.objects.RenderObject;

import java.util.HashMap;
import java.util.List;

public class RenderBatchFactory {

    private final RenderBatch renderBatch;

    public RenderBatchFactory() {
        renderBatch = new RenderBatch();
    }

    public RenderBatchFactory vertices(float[] vertices) {
        renderBatch.setVertices(vertices);
        return this;
    }

    public RenderBatchFactory indices(int[] indices) {
        renderBatch.setIndices(indices);
        return this;
    }

    public RenderBatchFactory shader(Shader shader) {
        renderBatch.setShader(shader);
        return this;
    }

    public RenderBatchFactory shaderUniforms(List<ShaderUniform<?>> shaderUniforms) {
        renderBatch.setShaderUniforms(shaderUniforms);
        return this;
    }

    public RenderBatchFactory vertexAttribs(VertexAttribPtr[] vertexAttribs) {
        renderBatch.setVertexAttribs(vertexAttribs);
        return this;
    }

    public RenderBatchFactory renderObjects(HashMap<Class<? extends RenderObject>, RenderObject> renderObjects) {
        renderBatch.setRenderObjects(renderObjects);
        return this;
    }


    public RenderBatch build() {
        return renderBatch;
    }
}
