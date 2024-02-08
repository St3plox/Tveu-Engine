package com.tveu.engine.utils;

import java.nio.ByteBuffer;

public class ImageDTO {

    private final ByteBuffer imageByteBuffer;

    private final int width;

    private final int height;

    public ImageDTO(ByteBuffer imageByteBuffer, int width, int height) {
        this.imageByteBuffer = imageByteBuffer;
        this.width = width;
        this.height = height;
    }

    public ByteBuffer getImageByteBuffer() {
        return imageByteBuffer;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
