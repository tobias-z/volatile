package com.tobiasz.common.transcode;

import static java.util.Objects.isNull;

import java.awt.image.BufferedImage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;

public class BufferedImageTranscoder {

    public Image convertToFxImage(BufferedImage image) {
        if (isNull(image)) {
            return null;
        }

        WritableImage writableImage = new WritableImage(image.getWidth(), image.getHeight());
        PixelWriter pixelWriter = writableImage.getPixelWriter();
        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                pixelWriter.setArgb(x, y, image.getRGB(x, y));
            }
        }

        return new ImageView(writableImage).getImage();
    }

}
