package com.tveu.engine.core.scene;

import com.tveu.engine.core.CameraTransform;
import com.tveu.engine.core.Transform;
import com.tveu.engine.core.component.CameraComponent;
import com.tveu.engine.core.component.ColoredCubeComponent;
import com.tveu.engine.core.component.CubeComponent;
import com.tveu.engine.core.game_object.FreeCamera;
import com.tveu.engine.core.game_object.GameObject;
import com.tveu.engine.rendering.Camera;
import org.joml.Vector3f;

//TODO: transfer some lighting shader staff
public class MainTestScene extends CameraScene {

    @Override
    public void init() {

        //----------- Initializing Camera
        var ct = new CameraTransform(new Vector3f(0.0f, 0.0f, 0.0f));
        var cameraObj = new FreeCamera(ct);

        this.cameraComponent = new CameraComponent(cameraObj, new Camera());
        cameraObj.addComponent(cameraComponent);

        this.addObj(cameraObj);

        //----------------- Initializing White cube
        var cubePosition = new Vector3f(-0.5f, -0.5f, -0.5f);
        var cube = new GameObject(new Transform(cubePosition));
        var cubeComponent = new CubeComponent(cube);

        cube.addComponent(cubeComponent);
        this.addObj(cube);

        var coloredCubePosition = new Vector3f(-0.15f, 3.5f, -1.0f);
        var coloredCube = new GameObject(new Transform(coloredCubePosition));
        var coloredCubeComponent = new ColoredCubeComponent(coloredCube, 0.0f, 0.0f, 1.0f);

        coloredCube.addComponent(coloredCubeComponent);
        this.addObj(coloredCube);

        super.init();
    }

    @Override
    public void update(float dt) {
        super.update(dt);
    }
}
