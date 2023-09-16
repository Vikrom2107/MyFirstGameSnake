package ru.romanovvb;

import java.awt.*;
import java.net.URL;

import static ru.romanovvb.GameField.ALL_DOTS;

public class WallAndRoof {
    private Image wallImage;
    private int[] xWall = new int[ALL_DOTS];
    private int[] yWall = new int[ALL_DOTS];
    private int wallsCount;
    private Image roofImage;
    private int[] xRoof = new int[ALL_DOTS];
    private int[] yRoof = new int[ALL_DOTS];
    private int roofCount;

    public WallAndRoof() {
        URL wallUrl = this.getClass().getResource("/images/wall.png");
        wallImage = Toolkit.getDefaultToolkit().getImage(wallUrl);
        URL roofUrl = this.getClass().getResource("/images/roof.png");
        roofImage = Toolkit.getDefaultToolkit().getImage(roofUrl);
    }
    public void createWall(String level) {
        if (level.equals("Level 1")) {
            wallsCount = 0;
            roofCount = 0;
        }
        if (level.equals("Level 2")) {
            wallsCount = 18;
            roofCount = 0;

            xWall[0] = 224;
            yWall[0] = 96;
            for (int i = 1; i < 9; i++) {
                xWall[i] = xWall[0];
                yWall[i] = yWall[i-1] + 32;
            }

            xWall[9] = 96;
            yWall[9] = 224;
            for (int j = 10; j < 18; j++) {
                yWall[j] = yWall[9];
                xWall[j] = xWall[j-1] + 32;
            }
        }
        if (level.equals("Level 3")) {
            wallsCount = 25;
            roofCount = 0;

            xWall[0] = 64;
            yWall[0] = 64;
            xWall[1] = 96;
            yWall[1] = 64;
            xWall[2] = 64;
            yWall[2] = 96;
            xWall[3] = 96;
            yWall[3] = 96;
            for (int i = 4; i < 8; i++) {
                xWall[i] = xWall[i-4];
                yWall[i] = yWall[i-4] + 288;
            }
            for (int i = 8; i < 12; i++) {
                xWall[i] = xWall[i-8] + 288;
                yWall[i] = yWall[i-8];
            }
            for (int i = 12; i < 16; i++) {
                xWall[i] = xWall[i-12] + 288;
                yWall[i] = yWall[i-12] + 288;
            }
            xWall[16] = 192;
            yWall[16] = 192;
            xWall[17] = 224;
            yWall[17] = 192;
            xWall[18] = 252;
            yWall[18] = 192;
            for (int i = 19; i < 25; i++) {
                xWall[i] = xWall[i-3];
                yWall[i] = yWall[i-3] + 32;
            }
        }
        if (level.equals("Level 4")) {
            wallsCount = 14;

            xWall[0] = 224;
            yWall[0] = 128;
            for (int i = 1; i < 7; i++) {
                xWall[i] = xWall[0];
                yWall[i] = yWall[i-1] + 32;
            }
            xWall[7] = 128;
            yWall[7] = 224;
            for (int j = 8; j <= 15; j++) {
                yWall[j] = yWall[7];
                xWall[j] = xWall[j-1] + 32;
            }

            roofCount = 64;

            xRoof[0] = 0;
            yRoof[0] = 0;
            xRoof[1] = 32;
            yRoof[1] = 0;
            xRoof[2] = 64;
            yRoof[2] = 0;
            xRoof[3] = 96;
            yRoof[3] = 0;
            for (int i = 4; i < 16; i++) {
                xRoof[i] = xRoof[i-4];
                yRoof[i] = yRoof[i-4] + 32;
            }

            for (int i = 16; i < 32; i++) {
                xRoof[i] = xRoof[i-16];
                yRoof[i] = yRoof[i-16] + 352;
            }
            for (int i = 32; i < 48; i++) {
                xRoof[i] = xRoof[i-32] + 352;
                yRoof[i] = yRoof[i-32];
            }
            for (int i = 48; i < 64; i++) {
                xRoof[i] = xRoof[i-48] + 352;
                yRoof[i] = yRoof[i-48] + 352;
            }
        }
        if (level.equals("Level 5")) {
            wallsCount = 16;

            xWall[0] = 64;
            yWall[0] = 64;
            xWall[1] = 96;
            yWall[1] = 64;
            xWall[2] = 64;
            yWall[2] = 96;
            xWall[3] = 96;
            yWall[3] = 96;
            for (int i = 4; i < 8; i++) {
                xWall[i] = xWall[i-4];
                yWall[i] = yWall[i-4] + 288;
            }
            for (int i = 8; i < 12; i++) {
                xWall[i] = xWall[i-8] + 288;
                yWall[i] = yWall[i-8];
            }
            for (int i = 12; i < 16; i++) {
                xWall[i] = xWall[i-12] + 288;
                yWall[i] = yWall[i-12] + 288;
            }

            roofCount = 43;

            xRoof[0] = 224;
            yRoof[0] = 384;
            xRoof[1] = 224;
            yRoof[1] = 96;
            xRoof[2] = 192;
            yRoof[2] = 384;
            xRoof[3] = 256;
            yRoof[3] = 384;

            xRoof[4] = 224;
            yRoof[4] = 96;
            for (int i = 5; i < 13; i++) {
                xRoof[i] = xRoof[4];
                yRoof[i] = yRoof[i-1] + 32;
            }
            for (int i = 13; i < 27; i+=2) {
                xRoof[i] = xRoof[4] + 32;
                yRoof[i] = yRoof[i-2] - 32;
                xRoof[i+1] = xRoof[4] - 32;
                yRoof[i+1] = yRoof[i-2] - 32;
            }
            for (int i = 27; i < 37; i+=2) {
                xRoof[i] = xRoof[4] + 64;
                yRoof[i] = yRoof[i-2] + 32;
                xRoof[i+1] = xRoof[4] - 64;
                yRoof[i+1] = yRoof[i-2] + 32;
            }
            for (int i = 37; i < 43; i+=2) {
                xRoof[i] = xRoof[4] + 96;
                yRoof[i] = yRoof[i-2] - 32;
                xRoof[i+1] = xRoof[4] - 96;
                yRoof[i+1] = yRoof[i-2] - 32;
            }
        }
    }

    public Image getWallImage() {
        return wallImage;
    }

    public void setWallImage(Image wallImage) {
        this.wallImage = wallImage;
    }

    public int[] getxWall() {
        return xWall;
    }

    public void setxWall(int[] xWall) {
        this.xWall = xWall;
    }

    public int[] getyWall() {
        return yWall;
    }

    public void setyWall(int[] yWall) {
        this.yWall = yWall;
    }

    public int getWallsCount() {
        return wallsCount;
    }

    public void setWallsCount(int wallsCount) {
        this.wallsCount = wallsCount;
    }

    public Image getRoofImage() {
        return roofImage;
    }

    public void setRoofImage(Image roofImage) {
        this.roofImage = roofImage;
    }

    public int[] getxRoof() {
        return xRoof;
    }

    public void setxRoof(int[] xRoof) {
        this.xRoof = xRoof;
    }

    public int[] getyRoof() {
        return yRoof;
    }

    public void setyRoof(int[] yRoof) {
        this.yRoof = yRoof;
    }

    public int getRoofCount() {
        return roofCount;
    }

    public void setRoofCount(int roofCount) {
        this.roofCount = roofCount;
    }
}
