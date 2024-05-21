package com.tveu.engine.core.scene;

import com.tveu.engine.core.CameraTransform;
import com.tveu.engine.core.Transform;
import com.tveu.engine.core.component.CameraComponent;
import com.tveu.engine.core.component.experimental.MaterialCubeComponent;
import com.tveu.engine.core.component.primitives.LightCubeComponent;
import com.tveu.engine.core.game_object.GameObject;
import com.tveu.engine.rendering.Camera;
import com.tveu.engine.rendering.materials.Material;
import org.joml.Vector3f;

public class RenderTestScene extends RenderScene {

    @Override
    public void init() {

        //-------------Camera-------------
        var ct = new CameraTransform(new Vector3f(0.0f, 0.0f, 0.0f));
        GameObject camera = new GameObject(ct);
        CameraComponent cameraComponent = new CameraComponent(camera, new Camera(ct));
        camera.addComponent(cameraComponent);
        this.addObj(camera);

        //-------------Test cube-------------
        GameObject emerald = new GameObject(new Transform(new Vector3f(1.0f, 0.0f, -1.0f)));
        var emeraldComp = new MaterialCubeComponent(Material.EMERALD);
        emerald.addComponent(emeraldComp);
        this.addObj(emerald);

        //-------------light-------------
        GameObject light = new GameObject(new Transform(new Vector3f(1.2f, 1.0f, 2.0f)));
        var lightComp = new LightCubeComponent(light);
        light.addComponent(lightComp);
        this.addObj(light);

        super.init();
    }

    @Override
    public void update(float dt) {
        super.update(dt);
    }
}
