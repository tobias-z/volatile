package com.tobiasz.common.transcode;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.apache.batik.anim.dom.SVGDOMImplementation;
import org.apache.batik.transcoder.TranscoderException;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.TranscodingHints;
import org.apache.batik.transcoder.image.ImageTranscoder;
import org.apache.batik.util.SVGConstants;
import org.apache.commons.io.FileUtils;

public class SvgTranscoder {

    public BufferedImage rasterize(File svgFile) throws IOException {

        final BufferedImage[] imagePointer = new BufferedImage[1];

        File cssFile = File.createTempFile("batik-default-override-", ".css");
        FileUtils.writeStringToFile(cssFile, """
            svg {
                shape-rendering: geometricPrecision;
                text-rendering:  geometricPrecision;
                color-rendering: optimizeQuality;
                image-rendering: optimizeQuality;
            }""");

        TranscodingHints transcoderHints = new TranscodingHints();
        transcoderHints.put(ImageTranscoder.KEY_XML_PARSER_VALIDATING, Boolean.FALSE);
        transcoderHints.put(ImageTranscoder.KEY_DOM_IMPLEMENTATION, SVGDOMImplementation.getDOMImplementation());
        transcoderHints.put(ImageTranscoder.KEY_DOCUMENT_ELEMENT_NAMESPACE_URI, SVGConstants.SVG_NAMESPACE_URI);
        transcoderHints.put(ImageTranscoder.KEY_DOCUMENT_ELEMENT, "svg");
        transcoderHints.put(ImageTranscoder.KEY_USER_STYLESHEET_URI, cssFile.toURI().toString());

        try {
            TranscoderInput input = new TranscoderInput(new FileInputStream(svgFile));
            ImageTranscoder imageTranscoder = getImageTranscoder(imagePointer);
            imageTranscoder.setTranscodingHints(transcoderHints);
            imageTranscoder.transcode(input, null);
            return imagePointer[0];
        } catch (TranscoderException ex) {
            ex.printStackTrace();
            throw new IOException("Couldn't convert " + svgFile);
        } finally {
            cssFile.delete();
        }
    }

    private ImageTranscoder getImageTranscoder(BufferedImage[] imagePointer) {
        return new ImageTranscoder() {

            @Override
            public BufferedImage createImage(int w, int h) {
                return new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
            }

            @Override
            public void writeImage(BufferedImage image, TranscoderOutput out) {
                imagePointer[0] = image;
            }
        };
    }

}
