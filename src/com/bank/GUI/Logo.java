package com.bank.GUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Logo extends JPanel {

    private BufferedImage image;

    public Logo() {

        try {
            image = ImageIO.read(new File("src\\com\\bank\\GUI\\Assets\\leumi.600.jpg"));
        } catch (IOException ex) {
            // handle exception...
        }

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), null);

    }
}
