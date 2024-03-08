package com.tveu.engine.core.component;

import com.tveu.engine.core.Updatable;
import com.tveu.engine.core.game_object.GameObject;

public abstract class Component implements Updatable {

    protected GameObject gameObject; // It's better to make it protected instead of public

    public Component(GameObject gameObject) {
        this.gameObject = gameObject;
    }

    // Abstract method to be implemented by subclasses for specific functionality
    public abstract void initialize();

    public void clean() {
    }

    public GameObject getGameObject() {
        return gameObject;
    }

    public void setGameObject(GameObject gameObject) {
        this.gameObject = gameObject;
    }
}
