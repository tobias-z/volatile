package com.tobiasz.common.util;

import static java.util.Objects.isNull;

import com.tobiasz.common.transcode.BufferedImageTranscoder;
import com.tobiasz.common.transcode.SvgTranscoder;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import javafx.scene.image.Image;

public class ResourceUtil {

    private static final SvgTranscoder svgTranscoder = new SvgTranscoder();
    private static final BufferedImageTranscoder bufferedImageTranscoder = new BufferedImageTranscoder();

    public static Image getSvgIcon(String iconName) {
        try {
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            URL url = classLoader.getResource(String.format("icons/%s.svg", iconName));
            if (isNull(url)) {
                return null;
            }

            BufferedImage bufferedImage = svgTranscoder.rasterize(new File(url.toURI()));
            return bufferedImageTranscoder.convertToFxImage(bufferedImage);
        } catch (IOException | URISyntaxException e) {
            return null;
        }
    }

}
