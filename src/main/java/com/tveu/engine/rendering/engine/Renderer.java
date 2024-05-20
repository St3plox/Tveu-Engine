package com.tveu.engine.rendering.engine;

public interface Renderer {

    void loadBathes(RenderBatch[] renderBatches);

    void render(float dt);
}
