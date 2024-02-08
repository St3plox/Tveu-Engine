package com.tveu.engine.utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.ByteBuffer;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.util.Base64.getEncoder;

public class FileManager {

    public static String readFileToString(String filePath) {

        StringBuilder shaderSource = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {

            String line;
            while ((line = reader.readLine()) != null) {
                shaderSource.append(line).append("\n");
            }
        } catch (IOException e) {
            Logger logger = Logger.getAnonymousLogger();
            logger.log(Level.SEVERE, "Invalid path of the GLSL File", e);
        }
        return shaderSource.toString();
    }

    public static ImageDTO readImageFile(String imagePath) throws IOException {

        BufferedImage image = ImageIO.read(new File(imagePath));
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        ImageIO.write(image, "png", byteArrayOutputStream);
        byte[] bytes = byteArrayOutputStream.toByteArray();
        ByteBuffer byteBuffer = ByteBuffer.wrap(bytes);

        return new ImageDTO(
                byteBuffer,
                image.getWidth(),
                image.getHeight()
        );
    }


}
