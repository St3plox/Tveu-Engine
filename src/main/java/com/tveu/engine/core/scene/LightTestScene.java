package com.tveu.engine.core.scene;

import com.tveu.engine.core.CameraTransform;
import com.tveu.engine.core.component.CameraComponent;
import com.tveu.engine.core.game_object.FreeCamera;
import com.tveu.engine.rendering.Camera;
import com.tveu.engine.rendering.Shader;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.lwjgl.BufferUtils;

import java.nio.FloatBuffer;

import static org.lwjgl.opengl.GL15C.*;
import static org.lwjgl.opengl.GL20C.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL20C.glVertexAttribPointer;
import static org.lwjgl.opengl.GL30C.glBindVertexArray;
import static org.lwjgl.opengl.GL30C.glGenVertexArrays;

public class LightTestScene extends Scene {

    float[] vertices = {
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

    private Shader lightCubeShader = new Shader("assets/shaders/light_cube_vertex.glsl",
            "assets/shaders/light_cube_fragment.glsl");
    private Shader lightingShader = new Shader("assets/shaders/colors_vertex.glsl",
            "assets/shaders/colors_fragment.glsl");

    private Vector3f lightPos = new Vector3f(1.2f, 1.0f, 2.0f);


    private int vbo, cubeVao, lightCubeVao;

    private FreeCamera camera;

    @Override
    public void init() {

        var ct = new CameraTransform(new Vector3f(0.0f, 0.0f, 0.0f));
        camera = new FreeCamera(ct);
        camera.addComponent(new CameraComponent(camera, new Camera()));
        objects.put(camera.getId(), camera);

        super.init();

        cubeVao = glGenVertexArrays();
        vbo = glGenBuffers();

        FloatBuffer verticesBuffer = BufferUtils.createFloatBuffer(vertices.length);
        verticesBuffer.put(vertices).flip();

        glBindBuffer(GL_ARRAY_BUFFER, vbo);
        glBufferData(GL_ARRAY_BUFFER, verticesBuffer, GL_STATIC_DRAW);

        // Bind the cube VAO
        glBindVertexArray(cubeVao);

        // Define vertex attribute pointers
        glVertexAttribPointer(0, 3, GL_FLOAT, false, 6 * Float.BYTES, 0);
        glEnableVertexAttribArray(0);

        glVertexAttribPointer(1, 3, GL_FLOAT, false, 6 * Float.BYTES, 3 * Float.BYTES);
        glEnableVertexAttribArray(1);

        // Create VAO for light cube
        lightCubeVao = glGenVertexArrays();
        glBindVertexArray(lightCubeVao);

        // Bind VBO to the light cube VAO
        glBindBuffer(GL_ARRAY_BUFFER, vbo);
        glVertexAttribPointer(0, 3, GL_FLOAT, false, 6 * Float.BYTES, 0);
        glEnableVertexAttribArray(0);
    }


    @Override
    public void update(float dt) {
        super.update(dt);

        lightingShader.use();
        lightingShader.setVec3("objectColor", 1.0f, 0.5f, 0.31f);
        lightingShader.setVec3("lightColor", 1.0f, 1.0f, 1.0f);
        lightingShader.setVec3("lightPos", lightPos);


        var cameraComponent = camera.getComponent(CameraComponent.class);

        Matrix4f view = cameraComponent.getView();
        Matrix4f projection = cameraComponent.getProjection();
        lightingShader.setMatrix4f("projection", projection);
        lightingShader.setMatrix4f("view", view);

        Matrix4f model = new Matrix4f();
        lightingShader.setMatrix4f("model", model);

        glBindVertexArray(cubeVao);
        glDrawArrays(GL_TRIANGLES, 0, 36);

        lightCubeShader.use();
        lightCubeShader.setMatrix4f("projection", projection);
        lightCubeShader.setMatrix4f("view", view);
        model = new Matrix4f();
        model.translate(lightPos);
        model.scale(new Vector3f(0.2f));  // Scale the light cube
        lightCubeShader.setMatrix4f("model", model);  // Use lightCubeShader here

        // Draw light cube
        glBindVertexArray(lightCubeVao);
        glDrawArrays(GL_TRIANGLES, 0, 36);
    }
}
