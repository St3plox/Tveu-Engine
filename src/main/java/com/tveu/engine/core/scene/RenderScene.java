package com.tveu.engine.core.scene;

import com.tveu.engine.core.component.CameraComponent;
import com.tveu.engine.core.component.experimental.RenderableComponent;
import com.tveu.engine.core.game_object.GameObject;
import com.tveu.engine.rendering.engine.SingleLightSceneRenderer;

public class RenderScene extends Scene {

    protected SingleLightSceneRenderer sceneRenderer;

    public RenderScene() {
        super();

        sceneRenderer = new SingleLightSceneRenderer();
    }

    @Override
    public void addObj(GameObject go) {

        go.getComponents().forEach(c -> {
            if (c instanceof RenderableComponent<?>){
                sceneRenderer.addRenderPrimitive((RenderableComponent<?>) c);
            }
        });

        super.addObj(go);
    }

    @Override
    public void init() {
        super.init();
        sceneRenderer.loadBathes();
    }

    @Override
    public void update(float dt) {
        super.update(dt);
        sceneRenderer.render(dt);
    }


}
