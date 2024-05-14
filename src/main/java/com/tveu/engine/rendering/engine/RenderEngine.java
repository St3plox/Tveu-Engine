package com.tveu.engine.rendering.engine;

public class RenderEngine implements Renderer{

    private final RenderEngine INSTANCE;

    private RenderBatch[] renderBatches;

    public RenderEngine() {
        INSTANCE = this;
    }

    @Override
    public void loadBathes(RenderBatch[] renderBatches) {
        this.renderBatches = renderBatches;
    }

    @Override
    public void render() {

    }

    @Override
    public void cleanUp() {

    }
}
