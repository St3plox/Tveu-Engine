package com.tveu.engine.core.component;

import com.tveu.engine.core.Updatable;
import com.tveu.engine.core.game_object.GameObject;

public abstract class Component implements Updatable {

    protected GameObject gameObject;

    public Component(GameObject gameObject) {
        this.gameObject = gameObject;
        init();
    }


    public void init(){
    }

    public void clean() {
    }

    public GameObject getGameObject() {
        return gameObject;
    }

    public void setGameObject(GameObject gameObject) {
        this.gameObject = gameObject;
    }
}
