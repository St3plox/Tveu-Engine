package com.tveu.engine.core.scene;

import com.tveu.engine.core.CameraTransform;
import com.tveu.engine.core.Transform;
import com.tveu.engine.core.component.CameraComponent;
import com.tveu.engine.core.component.primitives.LightCubeComponent;
import com.tveu.engine.core.component.primitives.ReactiveCubeComponent;
import com.tveu.engine.core.game_object.FreeCamera;
import com.tveu.engine.core.game_object.GameObject;
import com.tveu.engine.rendering.Camera;
import com.tveu.engine.rendering.materials.Material;
import org.joml.Vector3f;

public class MainTestScene extends SingleLightScene {

    @Override
    public void init() {
        //--------------- Adding light
        GameObject light = new GameObject(new Transform(new Vector3f(1.2f, 1.0f, 2.0f)));
        var lightComponent = new LightCubeComponent(light);
        light.addComponent(lightComponent);
        ((Transform) light.transform).scale(new Vector3f(0.2f));

        //----------- Initializing Camera
        var ct = new CameraTransform(new Vector3f(0.0f, 0.0f, 0.0f));
        var cameraObj = new FreeCamera(ct);

        this.cameraComponent = new CameraComponent(cameraObj, new Camera());
        cameraObj.addComponent(cameraComponent);

        this.addObj(cameraObj);


    /*    // Adding top plane
        GameObject cube = new GameObject(new Transform(new Vector3f(-0.15f, 1.5f, -1.0f)));
        var cubeComponent = new ReactiveCubeComponent(cube);
        cubeComponent.setMaterial(Material.RED_PLASTIC);
        cube.addComponent(cubeComponent);
        ((Transform) cube.transform).scale(new Vector3f(10.0f, 0.1f, 10.0f));

        // Adding bottom plane
        GameObject cube1 = new GameObject(new Transform(new Vector3f(-0.15f, -1.5f, -1.0f)));
        var cubeComponent1 = new ReactiveCubeComponent(cube1);
        cubeComponent1.setMaterial(Material.BLACK_RUBBER);
        cube1.addComponent(cubeComponent1);
        ((Transform) cube1.transform).scale(new Vector3f(10.0f, 0.1f, 10.0f));*/


        GameObject emerald = new GameObject(new Transform(new Vector3f(0.0f, 0.0f, 0.0f)));
        var emeraldComp = new ReactiveCubeComponent(emerald);
        emeraldComp.setMaterial(Material.RED_RUBBER);
        emerald.addComponent(emeraldComp);

/*        GameObject suction = new GameObject(new Transform(new Vector3f(1.55f, 0.13f, -1.5f)));
        var suctionComp = new ReactiveCubeComponent(suction);
        emeraldComp.setMaterial(Material.COPPER);
        emerald.addComponent(suctionComp);*/

     /*   this.addObj(cube1);
        this.addObj(cube);*/
        this.addObj(emerald);
//        this.addObj(suction);


        this.addObj(light);


        super.init();
    }

    @Override
    public void update(float dt) {
        super.update(dt);
    }
}
