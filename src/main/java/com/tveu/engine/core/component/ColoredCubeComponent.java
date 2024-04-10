package com.tveu.engine.core.component;

import com.tveu.engine.core.game_object.GameObject;
import com.tveu.engine.rendering.Shader;
import org.joml.Vector3f;

public class ColoredCubeComponent extends CubeComponent {

    private final float r, g, b;

    public ColoredCubeComponent(GameObject gameObject, float r, float g, float b) {
        super(gameObject);

        this.r = r;
        this.g = g;
        this.b = b;
    }

    @Override
    public void update(float dt) {
        super.update(dt);
        shapeComponent.shader.setVec3("objectColor", new Vector3f(r, g, b));
    }

    @Override
    protected Shader genShader() {
        return new Shader("shaders/base/base_colored_cube_vertex.glsl",
                "assets/shaders/base/base_colored_cube_fragment.glsl");
    }

}
