package com.tveu.engine.rendering;

import org.joml.Matrix4f;

public interface Displayable {

    void setView(Matrix4f view);

    void setProjection(Matrix4f projection);
}
