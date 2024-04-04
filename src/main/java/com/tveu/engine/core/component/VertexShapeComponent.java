package com.tveu.engine.core.component;

import com.tveu.engine.core.game_object.GameObject;
import com.tveu.engine.rendering.Shader;
import com.tveu.engine.rendering.VertexAttribPtr;
import com.tveu.engine.rendering.objects.*;
import org.joml.Matrix4f;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.glVertexAttribPointer;
import static org.lwjgl.opengl.GL20C.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL30.glBindVertexArray;

public class VertexShapeComponent extends Component implements Displayable {
    public Shader shader;
    private final float[] vertices;

    private final int[] indices;

    private final List<VertexAttribPtr> vertexAttribs;
    private final Set<RenderObject> renderObjects = new HashSet<>();

    private int vaoID;

    private Matrix4f view, projection;

    public VertexShapeComponent(GameObject gameObject, float[] vertices, int[] indices, Shader shader) {
        super(gameObject);

        this.vertices = vertices;
        vertexAttribs = new ArrayList<>(2);
        this.shader = shader;
        this.indices = indices;
    }

    @Override
    public void init() {
        for (int i = 0; i < vertexAttribs.size(); i++) {

            var attrib = vertexAttribs.get(i);
            glVertexAttribPointer(
                    i,
                    attrib.getSize(),
                    GL_FLOAT,
                    attrib.isNormalized(),
                    attrib.getStride(),
                    attrib.getPointer()
            );
            glEnableVertexAttribArray(i);
        }
    }

    //TODO: figure out how to work with view and perspective matrices
    @Override
    public void update(float dt) {

        shader.use();

        glBindVertexArray(vaoID);
        glDrawArrays(GL_TRIANGLES, 0, vertices.length);

        Matrix4f model = new Matrix4f();
        model.translate(gameObject.transform.getPos());
        shader.setMatrix4f("model", model);

        if (projection == null || view == null) {
            throw new RuntimeException("projection or view objects cannot be null in component");
        }
        shader.setMatrix4f("projection", projection);
        shader.setMatrix4f("view", view);

    }

    @Override
    public void setView(Matrix4f view) {
        this.view = view;
    }

    @Override
    public void setProjection(Matrix4f projection) {
        this.projection = projection;
    }

    public void addVertexAttrib(VertexAttribPtr vertexAttribPtr) {
        vertexAttribs.add(vertexAttribPtr);
    }

    public void addRenderObject(RenderObjects renderObject) {

        switch (renderObject) {
            case VBO -> renderObjects.add(new VBO(vertices));
            case VAO -> {
                VAO vao = new VAO();
                this.vaoID = vao.getId();
                renderObjects.add(vao);
            }
            case EBO -> renderObjects.add(new EBO(indices));
            default -> throw new RuntimeException("Undefined render object");
        }
    }
}
