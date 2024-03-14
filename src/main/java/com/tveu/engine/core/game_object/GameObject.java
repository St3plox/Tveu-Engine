package com.tveu.engine.core.game_object;

import com.tveu.engine.core.BaseTransform;
import com.tveu.engine.core.Transform;
import com.tveu.engine.core.Updatable;
import com.tveu.engine.core.component.Component;
import org.joml.Vector3f;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;


public class GameObject implements Updatable {

    protected final BaseTransform transform;

    protected Map<Class<? extends Component>, Component> components;

    private static final AtomicLong goIdCounter = new AtomicLong(0);
    private final long id;
    private String name;

    public GameObject() {
        this(new Transform(new Vector3f(0.0f, 0.0f, 0.0f)));
    }

    public GameObject(Transform transform) {

        this.transform = transform;

        id = goIdCounter.incrementAndGet();
        components = new HashMap<>();
        name = "";
    }

    public void init(){
        for (var c : components.values()){
            c.init();
        }
    }

    public void clean(){
        for (var c : components.values()){
            c.clean();
        }
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
