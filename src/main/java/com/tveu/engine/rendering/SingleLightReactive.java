package com.tveu.engine.rendering;

import org.joml.Vector3f;

public interface SingleLightReactive {

    void setLightColor(Vector3f color);
    void setLightPos(Vector3f pos);
    void setViewPos(Vector3f pos);

    void setLightAmbient(Vector3f ambient);
    void setLightDiffuse(Vector3f diffuse);
    void setLightSpecular(Vector3f specular);
}
