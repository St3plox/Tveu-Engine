package com.tveu.engine.rendering.engine;

import com.tveu.engine.core.component.CameraComponent;
import com.tveu.engine.core.component.experimental.RenderableComponent;
import com.tveu.engine.rendering.LightProperties;
import com.tveu.engine.rendering.primitives.AbstractPrimitive;
import com.tveu.engine.rendering.primitives.Cube;
import org.joml.Matrix4f;
import org.joml.Vector3f;

import java.util.LinkedList;
import java.util.List;

public class SingleLightSceneRenderer {

    private RenderEngine renderEngine;

    private List<AbstractPrimitive> renderPrimitives;
    private LightProperties lightProperties;

    private Matrix4f view, projection;
    private Vector3f cameraPos;


    public SingleLightSceneRenderer() {
        this.renderPrimitives = new LinkedList<>();
        this.renderEngine = RenderEngine.getInstance();
    }

    //TODO: Remove imports from core
    public void addRenderPrimitive(RenderableComponent<?> renderableComponent) {

        var renderableObj = renderableComponent.getRenderableObj();

        if (renderableObj instanceof AbstractPrimitive) {
            renderPrimitives.add((AbstractPrimitive) renderableObj);
            return;
        }

        //TODO: Replace with some sort of dto
        if (renderableObj instanceof CameraComponent) {
            view = ((CameraComponent) renderableObj).getView();
            projection = ((CameraComponent) renderableObj).getProjection();
            cameraPos = ((CameraComponent) renderableObj).getGameObject().transform.getPos();
            return;
        }

        if (renderableObj instanceof LightProperties) {
            lightProperties = (LightProperties) renderableObj;
            return;
        }

        throw new IllegalArgumentException("Unsupported renderable type: " + renderableObj.getClass().getSimpleName());
    }


    public void loadBathes() {


        renderPrimitives.forEach(rp -> {
            if (lightProperties == null) {
                throw new NullPointerException("LightProperties is null");
            }

            rp.setLight(lightProperties);
            rp.setProjection(projection);
            rp.setView(view);

            if (cameraPos == null) {
                throw new NullPointerException("CameraPos is null");
            }

            if (rp instanceof Cube) {
                ((Cube) rp).setViewPos(cameraPos);
            }
        });

        var renderBatches = new RenderBatch[renderPrimitives.size()];

        int i = 0;
        for (var rp : renderPrimitives) {
            renderBatches[i] = rp.genRenderBatch();
            i++;
        }

        renderEngine.loadBathes(renderBatches);
    }

    public void render(float dt) {
        renderEngine.render(dt);
    }
}
