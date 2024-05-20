package com.tveu.engine.rendering.objects;

import com.tveu.engine.core.Updatable;

public abstract class RenderObject implements Updatable {

    protected final int id;

    public RenderObject() {
        this.id = genId();
        bindId();
    }

    protected abstract int genId();

    protected abstract void bindId();

    public abstract void unbindId();

    @Override
    public void update(float dt) {
    }

    public int getId() {
        return id;
    }

    public abstract void delete();
}
