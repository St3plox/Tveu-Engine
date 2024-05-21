package com.tveu.engine.core.component.experimental;

import com.tveu.engine.core.Transform;
import com.tveu.engine.core.component.Component;
import com.tveu.engine.rendering.LightProperties;
import com.tveu.engine.rendering.materials.Material;
import com.tveu.engine.rendering.primitives.Cube;
import org.joml.Vector3f;

public class MaterialCubeComponent extends Component implements RenderableComponent<Cube> {

    protected final Cube cube;

    public MaterialCubeComponent() {
        super();

        Material material = Material.GOLD;
        this.cube = new Cube(material);
    }

    public MaterialCubeComponent(Material material) {
        super();

        this.cube = new Cube(material);
    }


    @Override
    public void update(float dt) {
        updateModel(cube.getModel());
    }

    @Override
    public Cube getRenderableObj() {
        return cube;
    }
}
