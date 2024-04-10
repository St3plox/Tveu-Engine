package com.tveu.engine.core.scene;

import com.tveu.engine.core.CameraTransform;
import com.tveu.engine.core.Transform;
import com.tveu.engine.core.component.CameraComponent;
import com.tveu.engine.core.component.primitives.LightCubeComponent;
import com.tveu.engine.core.component.primitives.ReactiveCubeComponent;
import com.tveu.engine.core.game_object.FreeCamera;
import com.tveu.engine.core.game_object.GameObject;
import com.tveu.engine.rendering.Camera;
import org.joml.Vector3f;

public class MainTestScene extends SingleLightScene {

    @Override
    public void init() {

        //----------- Initializing Camera
        var ct = new CameraTransform(new Vector3f(0.0f, 0.0f, 0.0f));
        var cameraObj = new FreeCamera(ct);

        this.cameraComponent = new CameraComponent(cameraObj, new Camera());
        cameraObj.addComponent(cameraComponent);

        this.addObj(cameraObj);



        // Adding default (orange) cube
        GameObject cube = new GameObject(new Transform(new Vector3f(-0.15f, 1.5f, -1.0f)));
        var cubeComponent = new ReactiveCubeComponent(cube);
        cube.addComponent(cubeComponent);
        ((Transform)cube.transform).scale(new Vector3f(2.0f));

        this.addObj(cube);

        //Adding light
        GameObject light = new GameObject(new Transform(new Vector3f(0.15f, -2.5f, 1.0f)));
        var lightComponent = new LightCubeComponent(light);
        light.addComponent(lightComponent);
        lightComponent.setColor(new Vector3f(1.0f, 0.1f, 0.1f));
        ((Transform)light.transform).scale(new Vector3f(0.2f));

        this.addObj(light);

        super.init();
    }

    @Override
    public void update(float dt) {
        super.update(dt);
    }
}
