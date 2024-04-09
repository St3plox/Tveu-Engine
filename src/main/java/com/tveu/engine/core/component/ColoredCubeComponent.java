package com.tveu.engine.core.component;

import com.tveu.engine.core.game_object.GameObject;
import com.tveu.engine.rendering.Shader;
import com.tveu.engine.rendering.VertexAttribPtr;
import com.tveu.engine.rendering.objects.RenderObjects;

import static org.lwjgl.opengl.GL20.glGetUniformLocation;
import static org.lwjgl.opengl.GL20.glUniform3f;

public class ColoredCubeComponent extends CubeComponent {

    private final float r, g, b;

    public ColoredCubeComponent(GameObject gameObject, float r, float g, float b) {
        super(gameObject);

        this.r = r;
        this.g = g;
        this.b = b;
    }

    @Override
    public void init() {
        VertexAttribPtr vertexAttrib = new VertexAttribPtr.Builder()
                .size(3)
                .normalized(false)
                .stride(3 * Float.BYTES)
                .pointer(0)
                .build();

        shapeComponent.addVertexAttrib(vertexAttrib);

        shapeComponent.addRenderObject(RenderObjects.VAO);
        shapeComponent.addRenderObject(RenderObjects.VBO);

        shapeComponent.init();
    }

    @Override
    public void update(float dt) {
        glUniform3f(glGetUniformLocation(shader.getID(), "objectColor"), r, g, b);
        super.update(dt);
    }

    @Override
    protected Shader genShader() {
        return new Shader("src/main/java/com/tveu/engine/rendering/shaders/base/base_colored_cube_vertex.glsl",
                "src/main/java/com/tveu/engine/rendering/shaders/base/base_colored_cube_fragment.glsl");
    }

}
