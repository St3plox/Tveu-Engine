package com.tveu.engine.core.scene;

import com.tveu.engine.core.component.primitives.LightCubeComponent;
import com.tveu.engine.core.game_object.GameObject;
import com.tveu.engine.rendering.SingleLightReactive;
import org.joml.Vector3f;

public class SingleLightScene extends CameraScene {

    protected Vector3f lightPosition = null;
    protected Vector3f lightColor = null;
    protected Vector3f lightAmbient = null;
    protected Vector3f lightSpecular = null;
    protected Vector3f lightDiffuse = null;

    public SingleLightScene() {
        super();
    }

    @Override
    public void addObj(GameObject go) {
        super.addObj(go);

        if (lightPosition == null || lightColor == null) {
            var comp = go.getComponent(LightCubeComponent.class);

            if (comp == null) {
                return;
            }

            lightPosition = go.transform.getPos();
            lightColor = comp.getColor();

        }

        for (var gameObject : getObjects()) {

            for (var comp : gameObject.getComponents()) {

                if (comp instanceof SingleLightReactive){
                    ((SingleLightReactive) comp).setLightColor(lightColor);
                    ((SingleLightReactive) comp).setLightPos(lightPosition);
                    ((SingleLightReactive) comp).setViewPos(cameraComponent.getGameObject().transform.getPos());
                    ((SingleLightReactive) comp).setLightAmbient(lightAmbient);
                    ((SingleLightReactive) comp).setLightSpecular(lightSpecular);
                    ((SingleLightReactive) comp).setLightDiffuse(lightDiffuse);
                }
            }
        }
    }
}
