package com.tveu.engine.core.component.primitives;

import com.tveu.engine.core.component.CubeComponent;
import com.tveu.engine.core.game_object.GameObject;
import com.tveu.engine.rendering.Shader;
import com.tveu.engine.rendering.SingleLightReactive;
import com.tveu.engine.rendering.VertexAttribPtr;
import com.tveu.engine.rendering.objects.RenderObjects;
import org.joml.Vector3f;

public class ReactiveCubeComponent extends CubeComponent implements SingleLightReactive {


    protected Vector3f lightPosition, lightColor;

    private Vector3f color;

    public ReactiveCubeComponent(GameObject gameObject) {
        super(gameObject);

        color = new Vector3f(1.0f, 0.5f, 0.31f);
    }

    @Override
    public void init() {

        VertexAttribPtr vertexAttribPtr = new VertexAttribPtr.Builder()
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

        shapeComponent.addVertexAttrib(vertexAttribPtr);
        shapeComponent.addVertexAttrib(normalAttrib);


        shapeComponent.addRenderObject(RenderObjects.VAO);
        shapeComponent.addRenderObject(RenderObjects.VBO);
        shapeComponent.init();
    }

    @Override
    public void update(float dt) {
        super.update(dt);

        shapeComponent.shader.setVec3("objectColor", color.x, color.y, color.z);
        shapeComponent.shader.setVec3("lightColor", lightColor.x, lightColor.y, lightColor.z);
        shapeComponent.shader.setVec3("lightPos", lightPosition.x, lightPosition.y, lightPosition.z);
    }

    @Override
    protected Shader genShader() {
        return new Shader("assets/shaders/colors_vertex.glsl",
                "assets/shaders/colors_fragment.glsl");
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

    public Vector3f getColor() {
        return color;
    }

    public void setColor(Vector3f color) {
        this.color = color;
    }
}
