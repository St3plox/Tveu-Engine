package com.tveu.engine.core;

public abstract class Scene implements Updatable{
    public Scene() {
    }

    public abstract void init();

    public abstract void update(float dt);
}
