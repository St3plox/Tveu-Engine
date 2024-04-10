package com.tveu.engine.core.component.primitives;

import com.tveu.engine.core.component.CubeComponent;
import com.tveu.engine.core.game_object.GameObject;
import com.tveu.engine.rendering.Shader;
import org.joml.Vector3f;


public class LightCubeComponent extends CubeComponent {

    protected Vector3f color;

    public LightCubeComponent(GameObject gameObject) {
        super(gameObject);

        color = new Vector3f(1.0f, 1.0f, 1.0f);
    }


    @Override
    protected Shader genShader() {
        return new Shader("assets/shaders/light_cube_vertex.glsl",
                "assets/shaders/light_cube_fragment.glsl");
    }

    public Vector3f getColor() {
        return color;
    }

    public void setColor(Vector3f color) {
        this.color = color;
    }

}
