package ru.romanovvb;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class Background extends JPanel {
    private Image background;

    public Background(String pathFile) {

        URL backgroundUrl = this.getClass().getResource(pathFile);
        background = Toolkit.getDefaultToolkit().getImage(backgroundUrl);

        Dimension size = new Dimension(500, 500);
        setPreferredSize(size);
        setMinimumSize(size);
        setMaximumSize(size);
        setSize(size);
        setLayout(null);
        setVisible(true);
    }

    public void paintComponent(Graphics g) {
        g.drawImage(background, 0, 0, null);
    }
}

