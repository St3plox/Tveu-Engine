package com.tveu.engine.rendering;

public class VertexAttribPtr {
    private int size = 3;

    private int stride = 0;
    private int pointer = 0;
    private boolean normalized = false;

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getStride() {
        return stride;
    }

    public void setStride(int stride) {
        this.stride = stride;
    }

    public int getPointer() {
        return pointer;
    }

    public void setPointer(int pointer) {
        this.pointer = pointer;
    }

    public boolean isNormalized() {
        return normalized;
    }

    public void setNormalized(boolean normalized) {
        this.normalized = normalized;
    }
    public static class Builder {
        private int size = 3;
        private int stride = 0;

        private int pointer = 0;

        private boolean normalized = false;

        public Builder size(int size) {
            this.size = size;
            return this;
        }

        public Builder stride(int stride) {
            this.stride = stride;
            return this;
        }

        public Builder pointer(int pointer) {
            this.pointer = pointer;
            return this;
        }
        public Builder normalized(boolean normalized) {
            this.normalized = normalized;
            return this;
        }

        public VertexAttribPtr build() {
            VertexAttribPtr vertexAttribPtr = new VertexAttribPtr();
            vertexAttribPtr.size = this.size;
            vertexAttribPtr.stride = this.stride;
            vertexAttribPtr.pointer = this.pointer;
            vertexAttribPtr.normalized = this.normalized;
            return vertexAttribPtr;
        }

    }
}
