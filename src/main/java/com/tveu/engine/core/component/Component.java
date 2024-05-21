package com.tveu.engine.core.component;

import com.tveu.engine.core.Transform;
import com.tveu.engine.core.Updatable;
import com.tveu.engine.core.game_object.GameObject;
import org.joml.Matrix4f;

public abstract class Component implements Updatable {

    protected GameObject gameObject;

    public Component(GameObject gameObject) {
        this.gameObject = gameObject;
    }

    public Component() {
    }

    public void init(){
    }

    public void clean() {
    }

    protected void updateModel(Matrix4f model){
        model.translate(gameObject.transform.getPos());
        model.scale(((Transform) gameObject.transform).getScale());
        model.rotate(((Transform) gameObject.transform).getRotation());
    }

    public GameObject getGameObject() {
        return gameObject;
    }

    public void setGameObject(GameObject gameObject) {
        this.gameObject = gameObject;
    }
}
