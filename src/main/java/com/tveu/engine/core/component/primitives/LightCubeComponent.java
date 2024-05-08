package com.tveu.engine.core.component.primitives;

import com.tveu.engine.core.component.CubeComponent;
import com.tveu.engine.core.game_object.GameObject;
import com.tveu.engine.rendering.Shader;
import org.joml.Vector3f;


public class LightCubeComponent extends CubeComponent {

    protected Vector3f color, ambient, diffuse, specular;

    public LightCubeComponent(GameObject gameObject) {
        super(gameObject);

        color = new Vector3f(1.0f, 1.0f, 1.0f);
        initMaterial();
    }




    @Override
    protected Shader genShader() {
        return new Shader("assets/shaders/light_cube_vertex.glsl",
                "assets/shaders/light_cube_fragment.glsl");
    }

    private void initMaterial(){
        this.diffuse = new Vector3f(color).mul(new Vector3f(0.5f));
        this.ambient = new Vector3f(diffuse).mul(new Vector3f(0.2f));
        this.specular = new Vector3f(1.0f, 1.0f, 1.0f);
    }

    public Vector3f getColor() {
        return color;
    }

    public void setColor(Vector3f color) {
        this.color = color;
    }

    public Vector3f getAmbient() {
        return ambient;
    }

    public void setAmbient(Vector3f ambient) {
        this.ambient = ambient;
    }

    public Vector3f getDiffuse() {
        return diffuse;
    }

    public void setDiffuse(Vector3f diffuse) {
        this.diffuse = diffuse;
    }

    public Vector3f getSpecular() {
        return specular;
    }

    public void setSpecular(Vector3f specular) {
        this.specular = specular;
    }
}
