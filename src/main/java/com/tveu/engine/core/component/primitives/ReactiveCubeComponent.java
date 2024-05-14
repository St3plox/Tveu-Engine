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

    protected Vector3f lightPosition;
    protected Vector3f viewPos;
    protected Vector3f ambientLight;
    protected Vector3f diffuseLight;
    protected Vector3f specularLight;
    protected Material material;

    public ReactiveCubeComponent(GameObject gameObject) {
        super(gameObject);

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

        shapeComponent.updateShader();

        shapeComponent.shader.setVec3("viewPos", viewPos.x, viewPos.y, viewPos.z);

        //light properties
        shapeComponent.shader.setVec3("light.position", lightPosition);
        shapeComponent.shader.setVec3("light.ambient", ambientLight);
        shapeComponent.shader.setVec3("light.diffuse", diffuseLight);
        shapeComponent.shader.setVec3("light.specular", specularLight);

        // material properties
        shapeComponent.shader.setVec3("material.ambient", material.getAmbient());
        shapeComponent.shader.setVec3("material.diffuse", material.getDiffuse());
        shapeComponent.shader.setVec3("material.specular", material.getSpecular());
        shapeComponent.shader.setFloat("material.shininess", material.getShininess());

        shapeComponent.drawShape();
    }

    @Override
    protected Shader genShader() {
        return new Shader("assets/shaders/material/cube_vertex.glsl",
                "assets/shaders/material/cube_fragment.glsl");
    }

    @Override
    protected float[] genVertices() {
        return new float[]{
                -0.5f, -0.5f, -0.5f,  0.0f,  0.0f, -1.0f,
                0.5f, -0.5f, -0.5f,  0.0f,  0.0f, -1.0f,
                0.5f,  0.5f, -0.5f,  0.0f,  0.0f, -1.0f,
                0.5f,  0.5f, -0.5f,  0.0f,  0.0f, -1.0f,
                -0.5f,  0.5f, -0.5f,  0.0f,  0.0f, -1.0f,
                -0.5f, -0.5f, -0.5f,  0.0f,  0.0f, -1.0f,

                -0.5f, -0.5f,  0.5f,  0.0f,  0.0f,  1.0f,
                0.5f, -0.5f,  0.5f,  0.0f,  0.0f,  1.0f,
                0.5f,  0.5f,  0.5f,  0.0f,  0.0f,  1.0f,
                0.5f,  0.5f,  0.5f,  0.0f,  0.0f,  1.0f,
                -0.5f,  0.5f,  0.5f,  0.0f,  0.0f,  1.0f,
                -0.5f, -0.5f,  0.5f,  0.0f,  0.0f,  1.0f,

                -0.5f,  0.5f,  0.5f, -1.0f,  0.0f,  0.0f,
                -0.5f,  0.5f, -0.5f, -1.0f,  0.0f,  0.0f,
                -0.5f, -0.5f, -0.5f, -1.0f,  0.0f,  0.0f,
                -0.5f, -0.5f, -0.5f, -1.0f,  0.0f,  0.0f,
                -0.5f, -0.5f,  0.5f, -1.0f,  0.0f,  0.0f,
                -0.5f,  0.5f,  0.5f, -1.0f,  0.0f,  0.0f,

                0.5f,  0.5f,  0.5f,  1.0f,  0.0f,  0.0f,
                0.5f,  0.5f, -0.5f,  1.0f,  0.0f,  0.0f,
                0.5f, -0.5f, -0.5f,  1.0f,  0.0f,  0.0f,
                0.5f, -0.5f, -0.5f,  1.0f,  0.0f,  0.0f,
                0.5f, -0.5f,  0.5f,  1.0f,  0.0f,  0.0f,
                0.5f,  0.5f,  0.5f,  1.0f,  0.0f,  0.0f,

                -0.5f, -0.5f, -0.5f,  0.0f, -1.0f,  0.0f,
                0.5f, -0.5f, -0.5f,  0.0f, -1.0f,  0.0f,
                0.5f, -0.5f,  0.5f,  0.0f, -1.0f,  0.0f,
                0.5f, -0.5f,  0.5f,  0.0f, -1.0f,  0.0f,
                -0.5f, -0.5f,  0.5f,  0.0f, -1.0f,  0.0f,
                -0.5f, -0.5f, -0.5f,  0.0f, -1.0f,  0.0f,

                -0.5f,  0.5f, -0.5f,  0.0f,  1.0f,  0.0f,
                0.5f,  0.5f, -0.5f,  0.0f,  1.0f,  0.0f,
                0.5f,  0.5f,  0.5f,  0.0f,  1.0f,  0.0f,
                0.5f,  0.5f,  0.5f,  0.0f,  1.0f,  0.0f,
                -0.5f,  0.5f,  0.5f,  0.0f,  1.0f,  0.0f,
                -0.5f,  0.5f, -0.5f,  0.0f,  1.0f,  0.0f
        };
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

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }
}
