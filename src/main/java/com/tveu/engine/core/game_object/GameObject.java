package com.tveu.engine.core.game_object;

import com.tveu.engine.core.Updatable;
import com.tveu.engine.core.component.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;


public class GameObject implements Updatable {

    private static final AtomicLong goIdCounter = new AtomicLong(0);
    private final long id;

    private String name;
    protected Map<Class<? extends Component>, Component> components;

    public GameObject() {
        id = goIdCounter.incrementAndGet();
        components = new HashMap<>();
        name = "";
    }

    public <T extends Component> T getComponent(Class<T> type) {
        return type.cast(components.get(type));
    }

    public void addComponent(Component component) {
        components.put(component.getClass(), component);
    }

    public void removeComponent(Component component) {
        components.remove(component.getClass(), component);
    }

    @Override
    public void update(float dt) {
        // Perform update logic for the GameObject
        for (Component component : components.values()) {
            component.update(dt);
        }
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

