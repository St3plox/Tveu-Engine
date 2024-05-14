package com.tveu.engine.rendering.materials;

import org.joml.Vector3f;

public class Material {

    private Vector3f ambient, diffuse, specular;
    private float shininess;

    private Material(Builder builder) {
        this.ambient = builder.ambient;
        this.diffuse = builder.diffuse;
        this.specular = builder.specular;
        this.shininess = builder.shininess;
    }

    public Vector3f getAmbient() {
        return ambient;
    }

    public Vector3f getDiffuse() {
        return diffuse;
    }

    public Vector3f getSpecular() {
        return specular;
    }

    public float getShininess() {
        return shininess;
    }

    public void setAmbient(Vector3f ambient) {
        this.ambient = ambient;
    }

    public void setDiffuse(Vector3f diffuse) {
        this.diffuse = diffuse;
    }

    public void setSpecular(Vector3f specular) {
        this.specular = specular;
    }

    public void setShininess(float shininess) {
        this.shininess = shininess * 128;
    }

    public static class Builder {
        private Vector3f ambient = new Vector3f();
        private Vector3f diffuse = new Vector3f();
        private Vector3f specular = new Vector3f();
        private float shininess;

        public Builder() {
        }

        public Builder ambient(float x, float y, float z) {
            this.ambient.set(x, y, z);
            return this;
        }

        public Builder diffuse(float x, float y, float z) {
            this.diffuse.set(x, y, z);
            return this;
        }

        public Builder specular(float x, float y, float z) {
            this.specular.set(x, y, z);
            return this;
        }

        public Builder shininess(float shininess) {
            this.shininess = shininess * 128;
            return this;
        }

        public Material build() {
            return new Material(this);
        }
    }

    public static final Material EMERALD = new Material.Builder()
            .ambient(0.0215f, 0.1745f, 0.0215f)
            .diffuse(0.07568f, 0.61424f, 0.07568f)
            .specular(0.633f, 0.727811f, 0.633f)
            .shininess(0.6f)
            .build();

    public static final Material JADE = new Material.Builder()
            .ambient(0.135f, 0.2225f, 0.1575f)
            .diffuse(0.54f, 0.89f, 0.63f)
            .specular(0.316228f, 0.316228f, 0.316228f)
            .shininess(0.1f)
            .build();
    public static final Material OBSIDIAN = new Material.Builder()
            .ambient(0.05375f, 0.05f, 0.06625f)
            .diffuse(0.18275f, 0.17f, 0.22525f)
            .specular(0.332741f, 0.328634f, 0.346435f)
            .shininess(0.3f)
            .build();

    public static final Material PEARL = new Material.Builder()
            .ambient(0.25f, 0.20725f, 0.20725f)
            .diffuse(1.0f, 0.829f, 0.829f)
            .specular(0.296648f, 0.296648f, 0.296648f)
            .shininess(0.088f)
            .build();

    public static final Material RUBY = new Material.Builder()
            .ambient(0.1745f, 0.01175f, 0.01175f)
            .diffuse(0.61424f, 0.04136f, 0.04136f)
            .specular(0.727811f, 0.626959f, 0.626959f)
            .shininess(0.6f)
            .build();

    public static final Material BRONZE = new Material.Builder()
            .ambient(0.2125f, 0.1275f, 0.054f)
            .diffuse(0.714f, 0.4284f, 0.18144f)
            .specular(0.393548f, 0.271906f, 0.166721f)
            .shininess(0.2f)
            .build();

    public static final Material CHROME = new Material.Builder()
            .ambient(0.25f, 0.25f, 0.25f)
            .diffuse(0.4f, 0.4f, 0.4f)
            .specular(0.774597f, 0.774597f, 0.774597f)
            .shininess(0.6f)
            .build();

    public static final Material COPPER = new Material.Builder()
            .ambient(0.19125f, 0.0735f, 0.0225f)
            .diffuse(0.7038f, 0.27048f, 0.0828f)
            .specular(0.256777f, 0.137622f, 0.086014f)
            .shininess(0.1f)
            .build();

    public static final Material GOLD = new Material.Builder()
            .ambient(0.24725f, 0.1995f, 0.0745f)
            .diffuse(0.75164f, 0.60648f, 0.22648f)
            .specular(0.628281f, 0.555802f, 0.366065f)
            .shininess(0.4f)
            .build();

    public static final Material SILVER = new Material.Builder()
            .ambient(0.19225f, 0.19225f, 0.19225f)
            .diffuse(0.50754f, 0.50754f, 0.50754f)
            .specular(0.508273f, 0.508273f, 0.508273f)
            .shininess(0.4f)
            .build();
    public static final Material BLACK_PLASTIC = new Material.Builder()
            .ambient(0.0f, 0.0f, 0.0f)
            .diffuse(0.01f, 0.01f, 0.01f)
            .specular(0.50f, 0.50f, 0.50f)
            .shininess(0.25f)
            .build();

    public static final Material CYAN_PLASTIC = new Material.Builder()
            .ambient(0.0f, 0.1f, 0.06f)
            .diffuse(0.0f, 0.50980392f, 0.50980392f)
            .specular(0.50196078f, 0.50196078f, 0.50196078f)
            .shininess(0.25f)
            .build();

    public static final Material GREEN_PLASTIC = new Material.Builder()
            .ambient(0.0f, 0.0f, 0.0f)
            .diffuse(0.1f, 0.35f, 0.1f)
            .specular(0.45f, 0.55f, 0.45f)
            .shininess(0.25f)
            .build();

    public static final Material RED_PLASTIC = new Material.Builder()
            .ambient(0.0f, 0.0f, 0.0f)
            .diffuse(0.5f, 0.0f, 0.0f)
            .specular(0.7f, 0.6f, 0.6f)
            .shininess(0.25f)
            .build();

    public static final Material WHITE_PLASTIC = new Material.Builder()
            .ambient(0.0f, 0.0f, 0.0f)
            .diffuse(0.55f, 0.55f, 0.55f)
            .specular(0.70f, 0.70f, 0.70f)
            .shininess(0.25f)
            .build();

    public static final Material YELLOW_PLASTIC = new Material.Builder()
            .ambient(0.0f, 0.0f, 0.0f)
            .diffuse(0.5f, 0.5f, 0.0f)
            .specular(0.60f, 0.60f, 0.50f)
            .shininess(0.25f)
            .build();

    public static final Material BLACK_RUBBER = new Material.Builder()
            .ambient(0.02f, 0.02f, 0.02f)
            .diffuse(0.01f, 0.01f, 0.01f)
            .specular(0.4f, 0.4f, 0.4f)
            .shininess(0.078125f)
            .build();

    public static final Material CYAN_RUBBER = new Material.Builder()
            .ambient(0.0f, 0.05f, 0.05f)
            .diffuse(0.4f, 0.5f, 0.5f)
            .specular(0.04f, 0.7f, 0.7f)
            .shininess(0.078125f)
            .build();

    public static final Material GREEN_RUBBER = new Material.Builder()
            .ambient(0.0f, 0.05f, 0.0f)
            .diffuse(0.4f, 0.5f, 0.4f)
            .specular(0.04f, 0.7f, 0.04f)
            .shininess(0.078125f)
            .build();

    public static final Material RED_RUBBER = new Material.Builder()
            .ambient(0.05f, 0.0f, 0.0f)
            .diffuse(0.5f, 0.4f, 0.4f)
            .specular(0.7f, 0.04f, 0.04f)
            .shininess(0.078125f)
            .build();

    public static final Material WHITE_RUBBER = new Material.Builder()
            .ambient(0.05f, 0.05f, 0.05f)
            .diffuse(0.5f, 0.5f, 0.5f)
            .specular(0.7f, 0.7f, 0.7f)
            .shininess(0.078125f)
            .build();

    public static final Material YELLOW_RUBBER = new Material.Builder()
            .ambient(0.05f, 0.05f, 0.0f)
            .diffuse(0.5f, 0.5f, 0.4f)
            .specular(0.7f, 0.7f, 0.04f)
            .shininess(0.078125f)
            .build();

}
