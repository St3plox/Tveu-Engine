package com.tveu.engine.rendering.primitives;

import com.tveu.engine.rendering.Shader;
import com.tveu.engine.rendering.ShaderUniform;
import com.tveu.engine.rendering.VertexAttribPtr;
import com.tveu.engine.rendering.materials.Material;
import com.tveu.engine.rendering.objects.RenderObject;
import com.tveu.engine.rendering.objects.VAO;
import com.tveu.engine.rendering.objects.VBO;
import org.joml.Vector3f;

import java.util.HashMap;
import java.util.List;

import static com.tveu.engine.rendering.primitives.PrimitivesConstants.*;

public class Cube extends AbstractPrimitive {



    protected Material material;


    protected Vector3f viewPos;

    private final Shader shader = new Shader(CUBE_VERTEX_SHADER_PATH,
            CUBE_FRAGMENT_SHADER_PATH);

    public Cube() {
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
                        .stride(6 * Float.BYTES)
                        .pointer(0)
                        .build(),

                new VertexAttribPtr.Builder()
                        .size(3)
                        .normalized(false)
                        .stride(6 * Float.BYTES)
                        .pointer(3 * Float.BYTES)
                        .build(),
        };
    }

    @Override
    List<ShaderUniform<?>> genShaderUniforms() {

        return List.of(new ShaderUniform<?>[]{
                new ShaderUniform<>("viewPos", viewPos),

                new ShaderUniform<>("light.position", light.pos()),
                new ShaderUniform<>("light.ambient", light.ambient()),
                new ShaderUniform<>("light.diffuse", light.diffuse()),
                new ShaderUniform<>("light.specular", light.specular()),

                new ShaderUniform<>("material.ambient", material.getAmbient()),
                new ShaderUniform<>("material.diffuse", material.getDiffuse()),
                new ShaderUniform<>("material.specular", material.getSpecular()),
                new ShaderUniform<>("material.shininess", material.getShininess()),
        });
    }

    @Override
    float[] vertices() {
        return MAT_CUBE_VERTICES;
    }

    @Override
    Shader genShader() {
        return new Shader(CUBE_VERTEX_SHADER_PATH, CUBE_FRAGMENT_SHADER_PATH);
    }

}
