package com.tveu.engine.core.component;

import com.tveu.engine.core.CameraTransform;
import com.tveu.engine.core.game_object.GameObject;
import com.tveu.engine.rendering.Camera;
import org.joml.Matrix4f;

public class CameraComponent extends Component {
    protected Camera camera;
    private Matrix4f view;
    private Matrix4f projection;

    public CameraComponent(GameObject gameObject, Camera camera) {
        super(gameObject);
        this.camera = camera;
    }

    @Override
    public void init() {
        super.init();

        camera.transform = (CameraTransform) gameObject.transform;

        view = ((CameraTransform) gameObject.transform).getViewMatrix();
        projection = new Matrix4f().perspective((float) Math.toRadians(camera.getZoom()), (float) Camera.SCR_WIDTH / Camera.SCR_HEIGHT, 0.1f, 100f);
    }

    @Override
    public void update(float dt) {

        view = camera.transform.getViewMatrix();
        projection = new Matrix4f().perspective((float) Math.toRadians(camera.getZoom()), (float) Camera.SCR_WIDTH / Camera.SCR_HEIGHT, 0.1f, 100f);
    }

    public Matrix4f getView() {
        return new Matrix4f(view);
    }

    public Matrix4f getProjection() {
        return new Matrix4f(projection);
    }
}
