package ru.romanovvb;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Snake {
    private BufferedImage headRight = null;
    private BufferedImage headLeft = null;
    private BufferedImage headUp = null;
    private BufferedImage headDown = null;
    private BufferedImage body = null;


    public Snake () {

        try {
            headRight = ImageIO.read(Objects.requireNonNull(Snake.class.getResourceAsStream("/images/snakehr.png")));
            body = ImageIO.read(Objects.requireNonNull(Snake.class.getResourceAsStream("/images/body.png")));
        } catch (IOException e) {
        }
        headLeft = rotate(headRight, 180);
        headDown = rotate(headRight, 90);
        headUp = rotate(headRight, 270);
    }
    public static BufferedImage rotate(BufferedImage img, int angle) {
        int width = img.getWidth();
        int height = img.getHeight();

        BufferedImage newImage = new BufferedImage(
                img.getWidth(), img.getHeight(), img.getType());

        Graphics2D g2 = newImage.createGraphics();

        g2.rotate(Math.toRadians(angle), width / 2, height / 2);
        g2.drawImage(img, null, 0, 0);

        return newImage;
    }

    public Image getHeadRight() {
        return headRight;
    }

    public Image getHeadLeft() {
        return headLeft;
    }

    public Image getHeadUp() {
        return headUp;
    }

    public Image getHeadDown() {
        return headDown;
    }

    public Image getBody() {
        return body;
    }


}
