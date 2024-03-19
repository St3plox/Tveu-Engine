package com.tveu.engine.core;

import com.tveu.engine.core.component.CameraComponent;
import com.tveu.engine.core.component.Component;
import com.tveu.engine.core.game_object.GameObject;
import com.tveu.engine.rendering.Displayable;

import java.util.ArrayList;
import java.util.List;

public class CameraScene extends Scene {

    private List<Displayable> cameraComponents;

    private CameraComponent cameraComponent;

    public CameraScene() {
        super();
        cameraComponents = new ArrayList<>();
    }

    @Override
    public void addObj(GameObject go) {
        super.addObj(go);

        var cameraComponent = go.getComponent(CameraComponent.class);
        if (cameraComponent != null) {
            this.cameraComponent = cameraComponent;
        }

        assert cameraComponent != null;
        for (var comp : go.getComponents()) {
            if (comp instanceof Displayable) {
                ((Displayable) comp).setProjection(cameraComponent.getProjection());
                ((Displayable) comp).setView(cameraComponent.getView());
            }
        }
    }

    @Override
    public void update(float dt) {
        super.update(dt);
    }
}
