package com.tveu.engine.rendering;

import org.joml.Vector3f;

public record LightProperties(Vector3f pos, Vector3f ambient, Vector3f diffuse, Vector3f specular) {

}
