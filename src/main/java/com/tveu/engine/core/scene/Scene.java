package com.tveu.engine.core.scene;

import com.tveu.engine.core.Updatable;
import com.tveu.engine.core.game_object.GameObject;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Scene implements Updatable {

    protected Map<Long, GameObject> objects;

    public Scene() {
        objects = new HashMap<>();
    }

    public void init() {
        for (var obj : objects.values()) {
            obj.init();
        }
    }

    public void update(float dt) {
        for (var obj : objects.values()) {
            obj.update(dt);
        }
    }

    public void addObj(GameObject go) {
        objects.put(go.getId(), go);
    }

    public Optional<GameObject> getObjByID(Long id) {
        return Optional.ofNullable(objects.get(id));
    }

    public void deleteObjByID(Long id) {
        objects.remove(id);
    }

    public Collection<GameObject> getObjects() {
        return objects.values();
    }

    public boolean containsObjID(Long id){
        return objects.containsKey(id);
    }

}
