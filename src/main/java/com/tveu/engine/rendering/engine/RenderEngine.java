package com.tveu.engine.rendering.engine;

import com.tveu.engine.rendering.Cleanable;

public class RenderEngine implements Renderer, Cleanable {

    private static RenderEngine instance;

    private RenderBatch[] renderBatches;

    public RenderEngine() {
    }

    public static RenderEngine getInstance() { // #3
        if (instance == null) {        //если объект еще не создан
            instance = new RenderEngine();    //создать новый объект
        }
        return instance;
    }

    @Override
    public void loadBathes(RenderBatch[] renderBatches) {
        instance.renderBatches = renderBatches;
    }

    @Override
    public void render(float dt) {
        for (RenderBatch renderBatch : instance.renderBatches) {
            renderBatch.update(dt);
        }
    }

    @Override
    public void clean() {
        for (RenderBatch renderBatch : instance.renderBatches) {
            renderBatch.clean();
        }
    }
}
