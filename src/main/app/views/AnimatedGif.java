package views;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;

import java.awt.image.BufferedImage;

public class AnimatedGif extends ImgAnimation {

    public AnimatedGif(String filename, double durationMs) {

        GifDecoder d = new GifDecoder();
        d.read(filename);

        Image[] sequence = new Image[d.getFrameCount()];
        for (int i = 0; i < d.getFrameCount(); i++) {
            WritableImage wimg = null;
            BufferedImage bimg = d.getFrame(i);
            sequence[i] = SwingFXUtils.toFXImage(bimg, wimg);
        }

        super.init(sequence, durationMs);
    }

    public void playOnce() {
        super.play();
        super.setOnFinished(e -> {
            super.stop();
        });
    }
}