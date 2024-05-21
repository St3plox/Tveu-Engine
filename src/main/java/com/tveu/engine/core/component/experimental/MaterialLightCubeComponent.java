package com.tveu.engine.core.component.experimental;

import com.tveu.engine.core.Transform;
import com.tveu.engine.core.component.Component;
import com.tveu.engine.rendering.LightProperties;
import com.tveu.engine.rendering.primitives.LightCube;
import org.joml.Vector3f;

public class MaterialLightCubeComponent extends Component implements RenderableComponent<LightCube> {

    protected final LightCube lightCube;

    public MaterialLightCubeComponent() {
        super();

        LightProperties lightProperties = new LightProperties(
                gameObject.transform.getPos(),
                new Vector3f(0.0f, 0.0f, 0.0f),
                new Vector3f(0.0f, 0.0f, 0.0f),
                new Vector3f(0.0f, 0.0f, 0.0f)
        );

        this.lightCube = new LightCube();
        lightCube.setLight(lightProperties);
    }

    public MaterialLightCubeComponent(LightProperties lightProperties) {
        super();

        this.lightCube = new LightCube();
        lightCube.setLight(lightProperties);
    }


    @Override
    public void update(float dt) {
        updateModel(lightCube.getModel());
    }

    @Override
    public LightCube getRenderableObj() {
        return lightCube;
    }
}
