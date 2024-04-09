package com.tveu.engine.core.component;

import com.tveu.engine.core.Transform;
import com.tveu.engine.core.game_object.GameObject;
import com.tveu.engine.rendering.Shader;
import com.tveu.engine.rendering.VertexAttribPtr;
import com.tveu.engine.rendering.objects.RenderObjects;
import org.joml.Matrix4f;

//CubeComponent creates base cube colored in pure white
public class CubeComponent extends Component implements Displayable {


    protected VertexShapeComponent shapeComponent;

    public CubeComponent(GameObject gameObject) {
        super(gameObject);

        Shader shader = genShader();
        float[] vertices = genVertices();

        shapeComponent = new VertexShapeComponent(this.gameObject, vertices, null, shader);

        if (!(gameObject.transform instanceof Transform)) {
            throw new IllegalArgumentException("CubeComponent requires a Transform");
        }

    }

    protected float[] genVertices() {
        return new float[]{
                -0.5f, -0.5f, -0.5f,
                0.5f, -0.5f, -0.5f,
                0.5f, 0.5f, -0.5f,
                0.5f, 0.5f, -0.5f,
                -0.5f, 0.5f, -0.5f,
                -0.5f, -0.5f, -0.5f,

                -0.5f, -0.5f, 0.5f,
                0.5f, -0.5f, 0.5f,
                0.5f, 0.5f, 0.5f,
                0.5f, 0.5f, 0.5f,
                -0.5f, 0.5f, 0.5f,
                -0.5f, -0.5f, 0.5f,

                -0.5f, 0.5f, 0.5f,
                -0.5f, 0.5f, -0.5f,
                -0.5f, -0.5f, -0.5f,
                -0.5f, -0.5f, -0.5f,
                -0.5f, -0.5f, 0.5f,
                -0.5f, 0.5f, 0.5f,

                0.5f, 0.5f, 0.5f,
                0.5f, 0.5f, -0.5f,
                0.5f, -0.5f, -0.5f,
                0.5f, -0.5f, -0.5f,
                0.5f, -0.5f, 0.5f,
                0.5f, 0.5f, 0.5f,

                -0.5f, -0.5f, -0.5f,
                0.5f, -0.5f, -0.5f,
                0.5f, -0.5f, 0.5f,
                0.5f, -0.5f, 0.5f,
                -0.5f, -0.5f, 0.5f,
                -0.5f, -0.5f, -0.5f,

                -0.5f, 0.5f, -0.5f,
                0.5f, 0.5f, -0.5f,
                0.5f, 0.5f, 0.5f,
                0.5f, 0.5f, 0.5f,
                -0.5f, 0.5f, 0.5f,
                -0.5f, 0.5f, -0.5f,
        };
    }

    protected Shader genShader() {
        return new Shader("src/main/java/com/tveu/engine/rendering/shaders/base/base_cube_vertex.glsl",
                "src/main/java/com/tveu/engine/rendering/shaders/base/base_cube_fragment.glsl");
    }

    @Override
    public void init() {
        super.init();

        VertexAttribPtr cubeAttrib = new VertexAttribPtr.Builder()
                .size(3)
                .normalized(false)
                .stride(3 * Float.BYTES)
                .pointer(0)
                .build();

        shapeComponent.addVertexAttrib(cubeAttrib);

        shapeComponent.addRenderObject(RenderObjects.VAO);
        shapeComponent.addRenderObject(RenderObjects.VBO);
        shapeComponent.init();
    }


    @Override
    public void update(float dt) {
        shapeComponent.update(dt);
    }

    @Override
    public void setView(Matrix4f view) {
        shapeComponent.setView(view);
    }

    @Override
    public void setProjection(Matrix4f projection) {
        shapeComponent.setProjection(projection);
    }
}
