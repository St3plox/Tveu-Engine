package com.tveu.engine.core.scene;

import com.tveu.engine.core.CameraTransform;
import com.tveu.engine.core.Transform;
import com.tveu.engine.core.component.CameraComponent;
import com.tveu.engine.core.component.VertexShapeComponent;
import com.tveu.engine.core.game_object.FreeCamera;
import com.tveu.engine.core.game_object.GameObject;
import com.tveu.engine.rendering.Camera;
import com.tveu.engine.rendering.Shader;
import com.tveu.engine.rendering.Texture;
import com.tveu.engine.rendering.VertexAttribPtr;
import com.tveu.engine.rendering.objects.RenderObjects;
import org.joml.Vector3f;

import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glBindTexture;
import static org.lwjgl.opengl.GL13.GL_TEXTURE0;
import static org.lwjgl.opengl.GL13.glActiveTexture;

//TODO: transfer some lighting shader staff
public class MainTestScene extends CameraScene {


    private Texture texture2 = new Texture("src/main/java/resources/textures/wall.jpg");

    @Override
    public void init() {

        //----------- Initializing Camera
        var ct = new CameraTransform(new Vector3f(0.0f, 0.0f, 0.0f));
        var cameraObj = new FreeCamera(ct);

        this.cameraComponent = new CameraComponent(cameraObj, new Camera());
        cameraObj.addComponent(cameraComponent);

        this.addObj(cameraObj);


        //-------------- Initializing Cube components

        Shader shader = new Shader("src/main/java/com/tveu/engine/rendering/shaders/t_vertex.glsl",
                "src/main/java/com/tveu/engine/rendering/shaders/t_fragment.glsl");

        //T vertices
        float[] vertices = {
                -0.5f, -0.5f, -0.5f, 0.0f, 0.0f,
                0.5f, -0.5f, -0.5f, 1.0f, 0.0f,
                0.5f, 0.5f, -0.5f, 1.0f, 1.0f,
                0.5f, 0.5f, -0.5f, 1.0f, 1.0f,
                -0.5f, 0.5f, -0.5f, 0.0f, 1.0f,
                -0.5f, -0.5f, -0.5f, 0.0f, 0.0f,

                -0.5f, -0.5f, 0.5f, 0.0f, 0.0f,
                0.5f, -0.5f, 0.5f, 1.0f, 0.0f,
                0.5f, 0.5f, 0.5f, 1.0f, 1.0f,
                0.5f, 0.5f, 0.5f, 1.0f, 1.0f,
                -0.5f, 0.5f, 0.5f, 0.0f, 1.0f,
                -0.5f, -0.5f, 0.5f, 0.0f, 0.0f,

                -0.5f, 0.5f, 0.5f, 1.0f, 0.0f,
                -0.5f, 0.5f, -0.5f, 1.0f, 1.0f,
                -0.5f, -0.5f, -0.5f, 0.0f, 1.0f,
                -0.5f, -0.5f, -0.5f, 0.0f, 1.0f,
                -0.5f, -0.5f, 0.5f, 0.0f, 0.0f,
                -0.5f, 0.5f, 0.5f, 1.0f, 0.0f,

                0.5f, 0.5f, 0.5f, 1.0f, 0.0f,
                0.5f, 0.5f, -0.5f, 1.0f, 1.0f,
                0.5f, -0.5f, -0.5f, 0.0f, 1.0f,
                0.5f, -0.5f, -0.5f, 0.0f, 1.0f,
                0.5f, -0.5f, 0.5f, 0.0f, 0.0f,
                0.5f, 0.5f, 0.5f, 1.0f, 0.0f,

                -0.5f, -0.5f, -0.5f, 0.0f, 1.0f,
                0.5f, -0.5f, -0.5f, 1.0f, 1.0f,
                0.5f, -0.5f, 0.5f, 1.0f, 0.0f,
                0.5f, -0.5f, 0.5f, 1.0f, 0.0f,
                -0.5f, -0.5f, 0.5f, 0.0f, 0.0f,
                -0.5f, -0.5f, -0.5f, 0.0f, 1.0f,

                -0.5f, 0.5f, -0.5f, 0.0f, 1.0f,
                0.5f, 0.5f, -0.5f, 1.0f, 1.0f,
                0.5f, 0.5f, 0.5f, 1.0f, 0.0f,
                0.5f, 0.5f, 0.5f, 1.0f, 0.0f,
                -0.5f, 0.5f, 0.5f, 0.0f, 0.0f,
                -0.5f, 0.5f, -0.5f, 0.0f, 1.0f
        };

        int[] indices = {
                // Front
                0, 1, 2,
                1, 3, 2,

                // Top
                4, 5, 6,
                5, 7, 6,

                // Ho   arizontal bar
                8, 9, 10,
                9, 11, 10,

                // Back
                12, 13, 14,
                13, 15, 14
        };

        var cubePosition = new Vector3f(-0.5f, -0.5f, -0.5f);

        var cube = new GameObject(new Transform(cubePosition));
        var cubeComponent = new VertexShapeComponent(cube, vertices, indices, shader);

        VertexAttribPtr cubeAttrib1 = new VertexAttribPtr.Builder()
                .size(3)
                .normalized(false)
                .stride(5 * Float.BYTES)
                .pointer(0)
                .build();
        VertexAttribPtr cubeAttrib2 = new VertexAttribPtr.Builder()
                .size(2)
                .normalized(false)
                .stride(5 * Float.BYTES)
                .pointer(3 * Float.BYTES)
                .build();

        cubeComponent.addVertexAttrib(cubeAttrib1);
        cubeComponent.addVertexAttrib(cubeAttrib2);

        cubeComponent.addRenderObject(RenderObjects.VAO);
        cubeComponent.addRenderObject(RenderObjects.VBO);
        cubeComponent.addRenderObject(RenderObjects.EBO);

        shader.use();
        shader.setInt("texture2", 0);

        cubeComponent.setView(cameraComponent.getView());
        cubeComponent.setProjection(cameraComponent.getProjection());

        cube.addComponent(cubeComponent);

        this.addObj(cube);

        super.init();
    }

    @Override
    public void update(float dt) {
        glActiveTexture(GL_TEXTURE0);
        glBindTexture(GL_TEXTURE_2D, texture2.getID());

        super.update(dt);
    }
}
