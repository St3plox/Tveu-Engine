package com.tveu.engine.core.scene;

import com.tveu.engine.core.component.CameraComponent;
import com.tveu.engine.core.game_object.GameObject;
import com.tveu.engine.core.component.Displayable;
import com.tveu.engine.core.scene.Scene;

public class CameraScene extends Scene {

    protected CameraComponent cameraComponent;

    public CameraScene() {
        super();
    }

    @Override
    public void addObj(GameObject go) {
        super.addObj(go);

        if (cameraComponent == null) {
             cameraComponent = go.getComponent(CameraComponent.class);
            return;
        }

        for (var comp : go.getComponents()) {

            if (comp instanceof Displayable) {

                ((Displayable) comp).setProjection(cameraComponent.getProjection());
                ((Displayable) comp).setView(cameraComponent.getView());
            }
        }
    }
}
