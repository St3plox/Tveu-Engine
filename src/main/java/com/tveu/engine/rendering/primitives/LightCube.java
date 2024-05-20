package com.tveu.engine.rendering.primitives;

import com.tveu.engine.rendering.Shader;
import com.tveu.engine.rendering.ShaderUniform;
import com.tveu.engine.rendering.VertexAttribPtr;
import com.tveu.engine.rendering.objects.RenderObject;
import com.tveu.engine.rendering.objects.VAO;
import com.tveu.engine.rendering.objects.VBO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.tveu.engine.rendering.primitives.PrimitivesConstants.*;

public class LightCube extends AbstractPrimitive {

    public LightCube() {
        super();
    }

    @Override
    HashMap<Class<? extends RenderObject>, RenderObject> genRenderObjects() {

        var renderObjects = new HashMap<Class<? extends RenderObject>, RenderObject>();
        renderObjects.put(VAO.class, new VAO());
        renderObjects.put(VBO.class, new VBO(vertices()));

        return renderObjects;
    }

    @Override
    VertexAttribPtr[] genVertexAttribs() {

        return new VertexAttribPtr[]{

                new VertexAttribPtr.Builder()
                        .size(3)
                        .normalized(false)
                        .stride(3 * Float.BYTES)
                        .pointer(0)
                        .build(),
        };
    }

    @Override
    List<ShaderUniform<?>> genShaderUniforms() {
        return new ArrayList<>();
    }

    @Override
    float[] vertices() {
        return BASE_CUBE_VERTICES;
    }

    @Override
    Shader genShader() {
        return new Shader(LIGHT_CUBE_VERTEX_SHADER_PATH, LIGHT_CUBE_FRAGMENT_SHADER_PATH);
    }
}
