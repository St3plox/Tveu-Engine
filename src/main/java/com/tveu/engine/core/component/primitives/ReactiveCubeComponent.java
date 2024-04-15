package com.tveu.engine.core.component.primitives;

import com.tveu.engine.core.component.CubeComponent;
import com.tveu.engine.core.game_object.GameObject;
import com.tveu.engine.rendering.Shader;
import com.tveu.engine.rendering.SingleLightReactive;
import com.tveu.engine.rendering.VertexAttribPtr;
import com.tveu.engine.rendering.materials.Material;
import com.tveu.engine.rendering.objects.RenderObjects;
import org.joml.Vector3f;

public class ReactiveCubeComponent extends CubeComponent implements SingleLightReactive {

    protected Vector3f lightPosition, lightColor, viewPos, ambientLight, diffuseLight, specularLight;
    protected Material material;

    private Vector3f color;

    public ReactiveCubeComponent(GameObject gameObject) {
        super(gameObject);

        color = new Vector3f(1.0f, 0.5f, 0.31f);
        material = new Material.Builder().build();
    }

    @Override
    public void init() {

        VertexAttribPtr positionAttrib = new VertexAttribPtr.Builder()
                .size(3)
                .normalized(false)
                .stride(6 * Float.BYTES)
                .pointer(0)
                .build();
        VertexAttribPtr normalAttrib = new VertexAttribPtr.Builder()
                .size(3)
                .normalized(false)
                .stride(6 * Float.BYTES)
                .pointer(3 * Float.BYTES)
                .build();

        shapeComponent.addVertexAttrib(positionAttrib);
        shapeComponent.addVertexAttrib(normalAttrib);


        shapeComponent.addRenderObject(RenderObjects.VAO);
        shapeComponent.addRenderObject(RenderObjects.VBO);
        shapeComponent.init();
    }

    @Override
    public void update(float dt) {
        super.update(dt);

        shapeComponent.shader.setVec3("viewPos", color.x, color.y, color.z);

        //light properties
        shapeComponent.shader.setVec3("light.position", lightPosition.x, lightPosition.y, lightPosition.z);
        shapeComponent.shader.setVec3("light.ambient", ambientLight);
        shapeComponent.shader.setVec3("light.diffuse", diffuseLight);
        shapeComponent.shader.setVec3("light.specular", specularLight);

        // material properties
        shapeComponent.shader.setVec3("material.ambient", material.getAmbient());
        shapeComponent.shader.setVec3("material.diffuse", material.getDiffuse());
        shapeComponent.shader.setVec3("material.specular", material.getSpecular());
        shapeComponent.shader.setFloat("material.shininess", material.getShininess());

    }

    @Override
    protected Shader genShader() {
        return new Shader("assets/shaders/material/cube_vertex.glsl",
                "assets/shaders/material/cube_fragment.glsl");
    }

    @Override
    protected float[] genVertices() {
        return new float[]{
                -0.5f, -0.5f, -0.5f, 0.0f, 0.0f, -1.0f,
                0.5f, -0.5f, -0.5f, 0.0f, 0.0f, -1.0f,
                0.5f, 0.5f, -0.5f, 0.0f, 0.0f, -1.0f,
                0.5f, 0.5f, -0.5f, 0.0f, 0.0f, -1.0f,
                -0.5f, 0.5f, -0.5f, 0.0f, 0.0f, -1.0f,
                -0.5f, -0.5f, -0.5f, 0.0f, 0.0f, -1.0f,

                -0.5f, -0.5f, 0.5f, 0.0f, 0.0f, 1.0f,
                0.5f, -0.5f, 0.5f, 0.0f, 0.0f, 1.0f,
                0.5f, 0.5f, 0.5f, 0.0f, 0.0f, 1.0f,
                0.5f, 0.5f, 0.5f, 0.0f, 0.0f, 1.0f,
                -0.5f, 0.5f, 0.5f, 0.0f, 0.0f, 1.0f,
                -0.5f, -0.5f, 0.5f, 0.0f, 0.0f, 1.0f,

                -0.5f, 0.5f, 0.5f, -1.0f, 0.0f, 0.0f,
                -0.5f, 0.5f, -0.5f, -1.0f, 0.0f, 0.0f,
                -0.5f, -0.5f, -0.5f, -1.0f, 0.0f, 0.0f,
                -0.5f, -0.5f, -0.5f, -1.0f, 0.0f, 0.0f,
                -0.5f, -0.5f, 0.5f, -1.0f, 0.0f, 0.0f,
                -0.5f, 0.5f, 0.5f, -1.0f, 0.0f, 0.0f,

                0.5f, 0.5f, 0.5f, 1.0f, 0.0f, 0.0f,
                0.5f, 0.5f, -0.5f, 1.0f, 0.0f, 0.0f,
                0.5f, -0.5f, -0.5f, 1.0f, 0.0f, 0.0f,
                0.5f, -0.5f, -0.5f, 1.0f, 0.0f, 0.0f,
                0.5f, -0.5f, 0.5f, 1.0f, 0.0f, 0.0f,
                0.5f, 0.5f, 0.5f, 1.0f, 0.0f, 0.0f,

                -0.5f, -0.5f, -0.5f, 0.0f, -1.0f, 0.0f,
                0.5f, -0.5f, -0.5f, 0.0f, -1.0f, 0.0f,
                0.5f, -0.5f, 0.5f, 0.0f, -1.0f, 0.0f,
                0.5f, -0.5f, 0.5f, 0.0f, -1.0f, 0.0f,
                -0.5f, -0.5f, 0.5f, 0.0f, -1.0f, 0.0f,
                -0.5f, -0.5f, -0.5f, 0.0f, -1.0f, 0.0f,

                -0.5f, 0.5f, -0.5f, 0.0f, 1.0f, 0.0f,
                0.5f, 0.5f, -0.5f, 0.0f, 1.0f, 0.0f,
                0.5f, 0.5f, 0.5f, 0.0f, 1.0f, 0.0f,
                0.5f, 0.5f, 0.5f, 0.0f, 1.0f, 0.0f,
                -0.5f, 0.5f, 0.5f, 0.0f, 1.0f, 0.0f,
                -0.5f, 0.5f, -0.5f, 0.0f, 1.0f, 0.0f
        };
    }

    @Override
    public void setLightColor(Vector3f color) {
        lightColor = color;
    }

    @Override
    public void setLightPos(Vector3f pos) {
        lightPosition = pos;
    }

    @Override
    public void setViewPos(Vector3f pos) {
        this.viewPos = pos;
    }

    @Override
    public void setLightAmbient(Vector3f ambient) {
        this.ambientLight = ambient;
    }

    @Override
    public void setLightDiffuse(Vector3f diffuse) {
        this.diffuseLight = diffuse;
    }

    @Override
    public void setLightSpecular(Vector3f specular) {
        this.specularLight = specular;
    }

    public Vector3f getColor() {
        return color;
    }

    public void setColor(Vector3f color) {
        this.color = color;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }
}